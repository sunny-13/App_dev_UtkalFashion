package com.appdev.utkalfashion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appdev.utkalfashion.Models.ShoppingItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ItemView extends AppCompatActivity {

    private ImageView imageViewItem, goHomeButton;
    private TextView itemTextView, priceTextView, expectedDeliveryTextView;
    private CardView addToCartView, buyNowView;

    String itemName, itemPrice, itemExpectedDelivery;
    int itemResId;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference addToCartReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        firebaseDatabase = FirebaseDatabase.getInstance();
        addToCartReference = firebaseDatabase.getReference("shopping_cart");
        imageViewItem = findViewById(R.id.img_view);
        goHomeButton = findViewById(R.id.go_home_button);
        itemTextView = findViewById(R.id.item_name);
        priceTextView = findViewById(R.id.item_price);
        expectedDeliveryTextView = findViewById(R.id.delivery_txt_view);
        addToCartView = findViewById(R.id.add_to_cart_button);
        buyNowView = findViewById(R.id.buy_now_button);

        itemName = getIntent().getStringExtra("item_name");
        itemPrice = getIntent().getStringExtra("item_price");
        itemResId = getIntent().getIntExtra("item_res_id", 1);
        itemExpectedDelivery = getIntent().getStringExtra("item_delivery");

        imageViewItem.setImageDrawable(this.getResources().getDrawable(itemResId));
        itemTextView.setText(itemName);
        priceTextView.setText(itemPrice);
        expectedDeliveryTextView.setText(itemExpectedDelivery);

        goHomeButton.setOnClickListener(v -> {
            finish();
        });

        buyNowView.setOnClickListener(v -> {
            Intent intent = new Intent(this, PlaceOrderActivity.class);
            intent.putExtra("item_name", itemName);
            intent.putExtra("item_price", itemPrice);
            intent.putExtra("item_res_id", itemResId);
            intent.putExtra("item_delivery", itemExpectedDelivery);
            startActivity(intent);
        });

        addToCartView.setOnClickListener(v -> {
            ShoppingItem shoppingItem = new ShoppingItem(itemName, itemPrice,
                    itemExpectedDelivery, itemResId);

            final int[] strength = new int[1];
            firebaseDatabase.getReference("cart_strength").get()
                    .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                   strength[0] =  Integer.valueOf(task.getResult().getValue(Integer.class));
                    int articleNum = strength[0] + 1;
                    addToCartReference.child("article" + articleNum).setValue(shoppingItem);

                    firebaseDatabase.getReference("cart_strength").setValue(articleNum);

                    Toast.makeText(ItemView.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                }
            });


        });


    }

}