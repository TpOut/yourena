package com.androidkt.recyclerviewselection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidkt.recyclerviewselection.R;
import com.androidkt.recyclerviewselection.model.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import static com.androidkt.recyclerviewselection.model.Item.transItemToKey;

/**
 * Created by brijesh on 26/3/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemListViewHolder> {

    private List<Item> itemList;
    private SelectionTracker<Long> selectionTracker;

    public MyAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    public SelectionTracker getSelectionTracker() {
        return selectionTracker;
    }

    public void setSelectionTracker(SelectionTracker<Long> selectionTracker) {
        this.selectionTracker = selectionTracker;
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.bind(item, selectionTracker.isSelected(transItemToKey(item)));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemListViewHolder extends RecyclerView.ViewHolder implements ViewHolderWithDetails<Long> {
        TextView itemId, itemName, itemPrice;

        public ItemListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemId = itemView.findViewById(R.id.itemId);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
        }

        public final void bind(Item item, boolean isActive) {
            itemView.setActivated(isActive);
            itemPrice.setText(item.getItemPrice() + "$");
            itemName.setText(item.getItemName());
            itemId.setText(item.getItemId() + "");

        }

        @Override
        public ItemDetailsLookup.ItemDetails<Long> getItemDetails() {
            return new MyItemDetail(getAdapterPosition(), itemList.get(getAdapterPosition()));
        }
    }
}
