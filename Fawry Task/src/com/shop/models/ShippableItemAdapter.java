package com.shop.models;
import com.shop.interfaces.*;

public class ShippableItemAdapter implements IShippableItem {
    private Item item;

    public ShippableItemAdapter(Item item) {
        this.item = item;
    }

    @Override
    public String getName() {
        return item.getItemName();
    }

    @Override
    public double getWeight() {
        return item.getItemWeight();
    }
}
