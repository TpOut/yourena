package com.androidkt.recyclerviewselection.adapter;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * Created by brijesh on 27/3/18.
 */

public interface ViewHolderWithDetails<K> {
    ItemDetailsLookup.ItemDetails<K> getItemDetails();
}
