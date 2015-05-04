package com.thinknew.shoppinglist.data;

import com.orm.SugarRecord;

import java.io.Serializable;

import com.thinknew.shoppinglist.R;


public class Item extends SugarRecord<Item> implements Serializable {

    public enum ItemType {
        PRODUCE(0, R.drawable.fruit), DAIRY(1, R.drawable.dairy), BAKERY(2, R.drawable.bread),
        CANDY(3, R.drawable.candy), HOUSEHOLD(4, R.drawable.cleaners), DRINKS(5, R.drawable.drinks);

        private int value;
        private int iconId;

        private ItemType(int value, int iconId) {
            this.value = value;
            this.iconId = iconId;
        }

        public static ItemType fromInt(int value) {
            for (ItemType p : ItemType.values()) {
                if (p.value == value) {
                    return p;
                }
            }
            return PRODUCE;
        }

        public int getIconId() {
            return iconId;
        }

        public int getValue() {
            return value;
        }
    }

    private String name;
    private boolean purchased;
    private double priceEstimate;
    private int quantity;
    private ItemType type;

    public Item(){

    }

    public Item(String name, ItemType type, double priceEstimate, int quantity) {
        purchased = false;
        this.name = name;
        this.priceEstimate = priceEstimate;
        this.quantity = quantity;
        this.type = type;

    }

    public void setPurchased(boolean value) {
        purchased = value;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {

        return name;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public double getPriceEstimate() {
        return priceEstimate;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceEstimate(double priceEstimate) {
        this.priceEstimate = priceEstimate;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
