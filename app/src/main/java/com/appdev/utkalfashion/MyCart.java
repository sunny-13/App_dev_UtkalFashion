package com.appdev.utkalfashion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appdev.utkalfashion.Adapters.CartAdapter;
import com.appdev.utkalfashion.Models.ShoppingItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyCart extends AppCompatActivity {

    private DatabaseReference cartReference;
    private RecyclerView recyclerView;

    private int amountTotal;
    private TextView amountTotalTxtView;

    private LinearLayout loadingPanel;

    private CardView cardTotalAmount;

    private String address = "Avas Vikas, Lucknow, UP";
    private ImageView goToHomeButton;

    private ArrayList<ShoppingItem> cartList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        cartReference = FirebaseDatabase.getInstance().getReference("shopping_cart");
        recyclerView = findViewById(R.id.cart_recycler_view);
        amountTotalTxtView = findViewById(R.id.amount_total_txt);
        loadingPanel = findViewById(R.id.loading_panel);
        cardTotalAmount = findViewById(R.id.card_total_amount);
        goToHomeButton = findViewById(R.id.go_home_button);

        goToHomeButton.setOnClickListener(v -> finish());

        cartList = new ArrayList<>();

        cartReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    for (DataSnapshot snap : snapshot.getChildren()){
                        ShoppingItem item = snap.getValue(ShoppingItem.class);
                        int amount = Integer.parseInt(item.getItem_price().substring(4)) + 40;
                        cartList.add(item);
                        amountTotal += amount;
                    }
                }
                amountTotalTxtView.setText("Rs. " + amountTotal);
                initAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initAdapter(){
        recyclerView.setAdapter(new CartAdapter(this, cartList));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setLoading(false);
    }

    private void setLoading(boolean bool){
        if(bool){
            loadingPanel.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            cardTotalAmount.setVisibility(View.GONE);
        }
        else{
            loadingPanel.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            cardTotalAmount.setVisibility(View.VISIBLE);
        }
    }
}