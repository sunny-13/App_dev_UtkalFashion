package com.appdev.utkalfashion.Models;

public class Category {
    String categoryName, message;
    int resId;

    public Category(String categoryName, String message, int resId) {
        this.categoryName = categoryName;
        this.message = message;
        this.resId = resId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getMessage() {
        return message;
    }

    public int getResId() {
        return resId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}

