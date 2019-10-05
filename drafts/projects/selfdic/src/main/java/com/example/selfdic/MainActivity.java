package com.example.selfdic;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.ev_query)
    EditText evQuery;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;

    private HistoryAdapter historyAdapter;
    private List<ItemBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LogUtil.level = LogUtil.DEBUG;

        initData();
        initView();
    }

    private void initData() {
        beanList = new ArrayList<>();
        LitePal.getDatabase();
        List<ItemBean> queryItems = DataSupport.findAll(ItemBean.class);
        Collections.reverse(queryItems);
        beanList.addAll(queryItems);
        LogUtil.d(TAG, "beanlist is : " + beanList);
    }

    private void initView() {
        evQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null == s) {
                    return;
                }
                String currentQuery = s.toString();
                LogUtil.d(TAG, "---afterTextChanged---currentQuery : " + currentQuery);
                int length = beanList.size();
                for (int i = 0; i < length; i++) {
                    if (beanList.get(i).getQuerySrc().equals(currentQuery)) {
                        rvHistory.scrollToPosition(i);
                        btnConfirm.setEnabled(false);
                        return;
                    }
                }
                btnConfirm.setEnabled(true);
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvHistory.setLayoutManager(layoutManager);
        historyAdapter = new HistoryAdapter(R.layout.item_history, beanList);
        rvHistory.setAdapter(historyAdapter);
    }

    @OnClick({R.id.iv_clear, R.id.btn_confirm})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                evQuery.setText("");
                break;
            case R.id.btn_confirm:
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://fanyi-api.baidu.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                BaiduTransApi baiduTransApi = retrofit.create(BaiduTransApi.class);

                String queryStr = evQuery.getText().toString();
                String from = "en";
                String to = "zh";
                String appId = "20161013000030130";
                String salt = String.valueOf(new Random().nextInt());
                String key = "KAF_utFypGQqkp9Ugfz_";

                String sign = MD5Util.getMD5(appId + queryStr + salt + key).toLowerCase();

                Call<BaiduTransResultBean> call = baiduTransApi.queryResult(
                        queryStr,
                        from,
                        to,
                        appId,
                        salt,
                        sign
                );
                call.enqueue(new Callback<BaiduTransResultBean>() {
                    @Override
                    public void onResponse(Call<BaiduTransResultBean> call, Response<BaiduTransResultBean> response) {
                        if (response.isSuccessful()) {
                            BaiduTransResultBean baiduTransResultBean = response.body();
                            if (null != baiduTransResultBean) {
                                BaiduTransResultBean.Trans_result trans_result = baiduTransResultBean.getTrans_result().get(0);
                                if (trans_result.getSrc().equals(trans_result.getDst())) {
                                    Toast.makeText(view.getContext(), "没有翻译结果", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                btnConfirm.setEnabled(false);
                                String src = trans_result.getSrc();
                                String dst = trans_result.getDst();
                                ItemBean itemBean = new ItemBean(src, dst);
                                LogUtil.d(TAG, "" + itemBean);
                                itemBean.save();

                                beanList.add(0, itemBean);
                                LogUtil.d(TAG, "" + beanList);
                                historyAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaiduTransResultBean> call, Throwable t) {

                    }
                });
                break;
        }
    }


    private class HistoryAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {

        public HistoryAdapter(@LayoutRes int layoutResId, @Nullable List<ItemBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ItemBean item) {
            helper.setText(R.id.item_query, "查询：" + item.getQuerySrc());
            helper.setText(R.id.item_result, "结果：" + item.getQueryResult());
        }
    }
}
