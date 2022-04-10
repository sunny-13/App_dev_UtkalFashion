package com.appdev.utkalfashion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appdev.utkalfashion.Adapters.OrderAdapter;
import com.appdev.utkalfashion.Models.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyOrders extends AppCompatActivity {

    private DatabaseReference orderReference;
    private RecyclerView recyclerView;

    private int amountTotal;
    private TextView amountTotalTxtView;

    private LinearLayout loadingPanel;

    private ImageView goToHomeButton;

    private ArrayList<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        orderReference = FirebaseDatabase.getInstance().getReference("orders");
        recyclerView = findViewById(R.id.orders_recycler_view);
        amountTotalTxtView = findViewById(R.id.amount_total_txt);
        loadingPanel = findViewById(R.id.loading_panel);
        goToHomeButton = findViewById(R.id.go_home_button);

        goToHomeButton.setOnClickListener(v -> finish());

        orderList = new ArrayList<>();

        orderReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    for (DataSnapshot snap : snapshot.getChildren()){
                        Order item = snap.getValue(Order.class);
                        orderList.add(item);
                    }
                }
                initAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initAdapter(){
        recyclerView.setAdapter(new OrderAdapter(this, orderList));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setLoading(false);
    }

    private void setLoading(boolean bool){
        if(bool){
            loadingPanel.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else{
            loadingPanel.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}