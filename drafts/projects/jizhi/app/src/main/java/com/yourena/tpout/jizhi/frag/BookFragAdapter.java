package com.yourena.tpout.jizhi.frag;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yourena.tpout.jizhi.R;
import com.yourena.tpout.jizhi.app.BookBean;

import java.util.List;

/**
 * Created by 41675 on 2018/2/24.
 */

public class BookFragAdapter extends BaseQuickAdapter<BookBean, BaseViewHolder> {

    public BookFragAdapter(@Nullable List<BookBean> data) {
        super(R.layout.item_frag_book, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookBean item) {
        helper.setText(R.id.tv, item.getName());
    }
}
