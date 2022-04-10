package com.appdev.utkalfashion.Models;

public class Order {

    String item_name, item_price, item_ordered_date, item_address;
    int item_resId;

    public Order(){}

    public Order(String item_name, String item_price, String item_ordered_date,
                 String item_address, int item_resId){
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_resId = item_resId;
        this.item_ordered_date = item_ordered_date;
        this.item_address = item_address;
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

    public String getItem_address() {
        return item_address;
    }

    public void setItem_address(String item_address) {
        this.item_address = item_address;
    }

    public String getItem_ordered_date(){
        return item_ordered_date;
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

    public void setItem_ordered_date(String item_ordered_date){this.item_ordered_date = item_ordered_date;}
}
