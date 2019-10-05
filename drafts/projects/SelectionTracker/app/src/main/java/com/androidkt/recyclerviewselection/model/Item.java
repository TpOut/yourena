package com.androidkt.recyclerviewselection.model;

/**
 * Created by brijesh on 26/3/18.
 */

public class Item {
    private int itemId;
    private float itemPrice;
    private String itemName;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public static Long transItemToKey(Item item){
        return (long)item.getItemId();
    }
}
