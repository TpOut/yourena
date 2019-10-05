package com.yourena.tpout.jizhi.act.main;

import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yourena.tpout.jizhi.R;
import com.yourena.tpout.jizhi.app.BookBean;
import com.yourena.tpout.jizhi.app.util.LogUtil;
import com.yourena.tpout.jizhi.app.util.ResUtil;
import com.yourena.tpout.jizhi.app.util.ScreenUtil;

import java.util.List;

/**
 * Created by 41675 on 2018/2/24.
 */

public class MainActAdapter extends BaseQuickAdapter<BookBean, BaseViewHolder> {

    private String TAG = MainActAdapter.class.getSimpleName();

    public MainActAdapter(@Nullable List<BookBean> data) {
        super(R.layout.item_act_main, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookBean item) {
        ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
        layoutParams.height = (int) ((ScreenUtil.getScreenWidth() - 3 * ResUtil.getDimen(R.dimen.space_normal_act_layout)) / 2);
        helper.itemView.setLayoutParams(layoutParams);
        LogUtil.d(TAG, "---convert---width : " + layoutParams.width + " | height : " + layoutParams.height);

        helper.setBackgroundColor(R.id.v, item.getColor());
        helper.setText(R.id.tv, item.getName());
    }
}
