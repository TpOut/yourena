package com.yourena.tpout.jizhi.frag;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yourena.tpout.jizhi.R;
import com.yourena.tpout.jizhi.app.BookBean;
import com.yourena.tpout.jizhi.app.base.BaseFrag;
import com.yourena.tpout.jizhi.app.util.DateUtil;
import com.yourena.tpout.jizhi.app.util.LogUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 41675 on 2018/2/24.
 */

public class BookFrag extends BaseFrag {

    public interface OnSelectListener {
        void onSelectListener(String bookName);
    }

    private OnSelectListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSelectListener) {
            mListener = (OnSelectListener) context;
        }
    }

    private List<BookBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_book, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mList = DataSupport.findAll(BookBean.class);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new BookFragAdapter(mList));
        rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (null != mListener) {
                    mListener.onSelectListener(mList.get(position).getName());
                }
                dismiss();
            }
        });

        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookBean bookBean = new BookBean(((EditText) view.findViewById(R.id.ev)).getText
                        ().toString(), BookBean.getRandomColor(), DateUtil.getCurDateOfString(DateUtil
                        .FORMAT_YMDHM), "");
                boolean save = bookBean.save();
                LogUtil.d(TAG, "bookBean :" + bookBean + " | save : " + save);
                if (null != mListener) {
                    mListener.onSelectListener(bookBean.getName());
                }
                dismiss();
            }
        });
    }

}
