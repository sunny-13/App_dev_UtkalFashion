package com.appdev.utkalfashion;

import com.appdev.utkalfashion.Models.Category;
import com.appdev.utkalfashion.Models.ShoppingItem;

import java.util.ArrayList;

public class DataUtils {
    public static ArrayList<Category> categoryList;
    public static ArrayList<ShoppingItem> shoppingItemsList;

    public static ArrayList<Category> getCategoryList(){
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Lacquer Work",
                "Explore the collection of different Lacquer Work",
                R.drawable.shoes));
        categoryList.add(new Category("Odisha Sarees",
                "Explore the collection of Sambalpuri Sarees.",
                R.drawable.clothing));
        categoryList.add(new Category("Patachitra",
                "Explore the collection of various Patachitra.",
                R.drawable.books));
        categoryList.add(new Category("Paintings",
                "Explore the collection of Paintings.",
                R.drawable.mobile1));

        return categoryList;
    }

    public static ArrayList<ShoppingItem> getClothingItemsList(){
        shoppingItemsList = new ArrayList<>();
        shoppingItemsList.add(new ShoppingItem("Saree1", "Rs. 399", "2 days", R.drawable.clothing));
        shoppingItemsList.add(new ShoppingItem("Saree2", "Rs. 599", "2 days", R.drawable.clothing));
        shoppingItemsList.add(new ShoppingItem("Saree3", "Rs. 499", "2 days", R.drawable.clothing));
        shoppingItemsList.add(new ShoppingItem("Saree4", "Rs. 959", "2 days", R.drawable.clothing));
        shoppingItemsList.add(new ShoppingItem("Saree5", "Rs. 349", "2 days", R.drawable.clothing));
        shoppingItemsList.add(new ShoppingItem("Saree6", "Rs. 799", "2 days", R.drawable.clothing));
        shoppingItemsList.add(new ShoppingItem("Saree7", "Rs. 499", "2 days", R.drawable.clothing));
        shoppingItemsList.add(new ShoppingItem("Saree8", "Rs. 699", "2 days", R.drawable.clothing));
        shoppingItemsList.add(new ShoppingItem("Saree9", "Rs. 799", "2 days", R.drawable.clothing));
        shoppingItemsList.add(new ShoppingItem("Saree10", "Rs. 289", "2 days", R.drawable.clothing));
        return shoppingItemsList;
    }

    public static ArrayList<ShoppingItem> getShoesItemsList(){
        shoppingItemsList = new ArrayList<>();
        shoppingItemsList.add(new ShoppingItem("Lacquer Work1", "Rs. 399", "2 days", R.drawable.shoes));
        shoppingItemsList.add(new ShoppingItem("Lacquer Work2", "Rs. 599", "2 days", R.drawable.shoes));
        shoppingItemsList.add(new ShoppingItem("Lacquer Work3", "Rs. 499", "2 days", R.drawable.shoes));
        shoppingItemsList.add(new ShoppingItem("Lacquer Work4", "Rs. 959", "2 days", R.drawable.shoes));
        shoppingItemsList.add(new ShoppingItem("Lacquer Work5", "Rs. 349", "2 days", R.drawable.shoes));
        shoppingItemsList.add(new ShoppingItem("Lacquer Work6", "Rs. 799", "2 days", R.drawable.shoes));
        shoppingItemsList.add(new ShoppingItem("Lacquer Work7", "Rs. 499", "2 days", R.drawable.shoes));
        shoppingItemsList.add(new ShoppingItem("Lacquer Work8", "Rs. 699", "2 days", R.drawable.shoes));
        shoppingItemsList.add(new ShoppingItem("Lacquer Work9", "Rs. 799", "2 days", R.drawable.shoes));
        shoppingItemsList.add(new ShoppingItem("Lacquer Work10", "Rs. 289", "2 days", R.drawable.shoes));
        return shoppingItemsList;
    }

    public static ArrayList<ShoppingItem> getBooksItemsList(){
        shoppingItemsList = new ArrayList<>();
        shoppingItemsList.add(new ShoppingItem("Patachitra1", "Rs. 399", "2 days", R.drawable.books));
        shoppingItemsList.add(new ShoppingItem("Patachitra2", "Rs. 599", "2 days", R.drawable.books));
        shoppingItemsList.add(new ShoppingItem("Patachitra3", "Rs. 499", "2 days", R.drawable.books));
        shoppingItemsList.add(new ShoppingItem("Patachitra4", "Rs. 959", "2 days", R.drawable.books));
        shoppingItemsList.add(new ShoppingItem("Patachitra5", "Rs. 349", "2 days", R.drawable.books));
        shoppingItemsList.add(new ShoppingItem("Patachitra6", "Rs. 799", "2 days", R.drawable.books));
        shoppingItemsList.add(new ShoppingItem("Patachitra7", "Rs. 499", "2 days", R.drawable.books));
        shoppingItemsList.add(new ShoppingItem("Patachitra8", "Rs. 699", "2 days", R.drawable.books));
        shoppingItemsList.add(new ShoppingItem("Patachitra9", "Rs. 799", "2 days", R.drawable.books));
        shoppingItemsList.add(new ShoppingItem("Patachitra10", "Rs. 289", "2 days", R.drawable.books));
        return shoppingItemsList;
    }

    public static ArrayList<ShoppingItem> getMobileItemsList(){
        shoppingItemsList = new ArrayList<>();
        shoppingItemsList.add(new ShoppingItem("Painting1", "Rs. 39999", "2 days", R.drawable.mobile1));
        shoppingItemsList.add(new ShoppingItem("Paintings2", "Rs. 22339", "2 days", R.drawable.mobile1));
        shoppingItemsList.add(new ShoppingItem("Paintings3", "Rs. 29999", "2 days", R.drawable.mobile1));
        shoppingItemsList.add(new ShoppingItem("Paintings4", "Rs. 19999", "2 days", R.drawable.mobile1));
        shoppingItemsList.add(new ShoppingItem("Paintings5", "Rs. 12999", "2 days", R.drawable.mobile1));
        shoppingItemsList.add(new ShoppingItem("Paintings6", "Rs. 29999", "2 days", R.drawable.mobile1));
        shoppingItemsList.add(new ShoppingItem("Paintings7", "Rs. 49999", "2 days", R.drawable.mobile1));
        shoppingItemsList.add(new ShoppingItem("Paintings8", "Rs. 23999", "2 days", R.drawable.mobile1));
        shoppingItemsList.add(new ShoppingItem("Paintings9", "Rs. 79999", "2 days", R.drawable.mobile1));
        shoppingItemsList.add(new ShoppingItem("Paintings10", "Rs. 28999", "2 days", R.drawable.mobile1));
        return shoppingItemsList;
    }

    public static ArrayList<ShoppingItem> getShoppingItemsList(String category){
        switch (category){
            default:
            case "Odisha Sarees" :
                return getClothingItemsList();
            case "Lacquer Work" :
                return getShoesItemsList();
            case "Patachitra" :
                return getBooksItemsList();
            case "Paintings":
                return getMobileItemsList();
        }
    }
}
