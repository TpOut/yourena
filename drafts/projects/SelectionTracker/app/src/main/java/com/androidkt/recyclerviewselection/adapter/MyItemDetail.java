package com.androidkt.recyclerviewselection.adapter;

import com.androidkt.recyclerviewselection.model.Item;

import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * Created by brijesh on 27/3/18.
 */

public class MyItemDetail extends ItemDetailsLookup.ItemDetails<Long> {
    private final int adapterPosition;
    private final Item selectionKey;

    public MyItemDetail(int adapterPosition, Item selectionKey) {
        this.adapterPosition = adapterPosition;
        this.selectionKey = selectionKey;
    }

    @Override
    public int getPosition() {
        return adapterPosition;
    }

    @Nullable
    @Override
    public Long getSelectionKey() {
        return Item.transItemToKey(selectionKey);
    }
}
