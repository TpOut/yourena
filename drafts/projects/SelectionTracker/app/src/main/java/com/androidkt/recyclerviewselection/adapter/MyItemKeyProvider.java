package com.androidkt.recyclerviewselection.adapter;

import com.androidkt.recyclerviewselection.model.Item;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;

/**
 * Created by brijesh on 27/3/18.
 */

public class MyItemKeyProvider extends ItemKeyProvider<Long> {
    private final List<Item> itemList;
    private       List<Long> mIdList;

    public MyItemKeyProvider(int scope, List<Item> itemList) {
        super(scope);
        this.itemList = itemList;
    }

    @Nullable
    @Override
    public Long getKey(int position) {
        return (long) itemList.get(position).getItemId();
    }

    @Override
    public int getPosition(@NonNull Long key) {
        if (null == mIdList) {
            mIdList = new ArrayList<>();
            for (Item i : itemList) {
                mIdList.add((long) i.getItemId());
            }
        }
        return mIdList.indexOf(key);
    }
}
