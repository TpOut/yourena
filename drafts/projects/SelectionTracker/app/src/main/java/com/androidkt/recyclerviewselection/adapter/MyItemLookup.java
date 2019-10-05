package com.androidkt.recyclerviewselection.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * Created by brijesh on 27/3/18.
 */

public class MyItemLookup extends ItemDetailsLookup<Long> {

    private final RecyclerView recyclerView;

    public MyItemLookup(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent e) {
        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if (view != null) {
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            if (viewHolder instanceof MyAdapter.ItemListViewHolder) {
                return ((MyAdapter.ItemListViewHolder) viewHolder).getItemDetails();
            }
        }

        return null;
    }
}
