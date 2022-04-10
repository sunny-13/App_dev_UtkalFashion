package com.appdev.utkalfashion;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.appdev.utkalfashion.Adapters.ShoppingAdapter;

public class ShoppingActivity extends AppCompatActivity {

    String category;
    RecyclerView gridView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        category = getIntent().getStringExtra("shop_category");

        textView = findViewById(R.id.category_txt);
        textView.setText(category);

        gridView = findViewById(R.id.shopping_recycler_view);

        ShoppingAdapter shoppingAdapter = new ShoppingAdapter(this, category);

        gridView.setAdapter(shoppingAdapter);

        gridView.setLayoutManager(new GridLayoutManager(this, 2));

        findViewById(R.id.go_home_button).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.go_to_cart_button).setOnClickListener(v -> {
            startActivity(new Intent(this, MyCart.class));
        });
    }
}