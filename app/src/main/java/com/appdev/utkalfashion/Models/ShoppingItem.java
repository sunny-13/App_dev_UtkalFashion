package com.appdev.utkalfashion.Models;

public class ShoppingItem {

    String item_name, item_price, item_delivery;
    int item_resId;

    public ShoppingItem(){}

    public ShoppingItem(String item_name, String item_price, String item_delivery, int item_resId){
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_resId = item_resId;
        this.item_delivery = item_delivery;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public int getItem_resId() {
        return item_resId;
    }

    public String getItem_delivery(){
        return item_delivery;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public void setItem_resId(int item_resId) {
        this.item_resId = item_resId;
    }

    public void setItem_delivery(String item_delivery){this.item_delivery = item_delivery;}
}
