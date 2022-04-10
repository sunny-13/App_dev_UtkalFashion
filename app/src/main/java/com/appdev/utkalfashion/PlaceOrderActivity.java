package com.appdev.utkalfashion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appdev.utkalfashion.Models.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlaceOrderActivity extends AppCompatActivity {

    ImageView goHomebutton, itemImageView, editAddressImageView;
    TextView itemTextView, itemPriceTextView, totalAmountTextView,
            expectedDeliveryTextView, confirmedAddressTextView, confirmButtonTextView;
    EditText et_address;
    CardView placeOrderCardView, frameEditAddress, frameConfirmAddress;


    String itemName, itemPrice, itemExpectedDelivery, typedAddress;
    int itemResId;


    DatabaseReference orderReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        orderReference = FirebaseDatabase.getInstance().getReference();
        goHomebutton = findViewById(R.id.go_home_button);
        itemImageView = findViewById(R.id.img_view);
        editAddressImageView = findViewById(R.id.edit_confirmed_address_button);

        itemTextView = findViewById(R.id.item_name);
        itemPriceTextView = findViewById(R.id.item_price);
        totalAmountTextView = findViewById(R.id.total_amount_txt);
        expectedDeliveryTextView = findViewById(R.id.delivery_summary_txt);
        confirmedAddressTextView = findViewById(R.id.confirmed_address_txt);
        confirmButtonTextView = findViewById(R.id.confirm_button);

        et_address = findViewById(R.id.et_address);
        placeOrderCardView = findViewById(R.id.place_order_card_view);
        frameConfirmAddress = findViewById(R.id.frame_confirmed_address);
        frameEditAddress = findViewById(R.id.frame_edit_address);

        itemName = getIntent().getStringExtra("item_name");
        itemPrice = getIntent().getStringExtra("item_price");
        itemResId = getIntent().getIntExtra("item_res_id", 1);
        itemExpectedDelivery = getIntent().getStringExtra("item_delivery");

        goHomebutton.setOnClickListener(v -> {
            finish();
        });

        itemImageView.setImageDrawable(this.getResources().getDrawable(itemResId));
        itemTextView.setText(itemName);
        itemPriceTextView.setText(itemPrice);

        int totalAmount = Integer.parseInt(itemPrice.substring(4)) + 40;
        totalAmountTextView.setText("Rs. " + totalAmount);

        expectedDeliveryTextView.setText(itemExpectedDelivery);

        confirmButtonTextView.setOnClickListener(v -> {

            typedAddress = et_address.getText().toString();
            if(typedAddress.equals("")) {
                Toast.makeText(this, "Enter Some Address",
                        Toast.LENGTH_SHORT).show();
            }
            else{
                confirmedAddressTextView.setText(typedAddress);
                frameConfirmAddress.setVisibility(View.VISIBLE);
                frameEditAddress.setVisibility(View.GONE);
            }
        });

        editAddressImageView.setOnClickListener(v -> {
            et_address.setText(typedAddress);
            frameConfirmAddress.setVisibility(View.GONE);
            frameEditAddress.setVisibility(View.VISIBLE);
        });


        placeOrderCardView.setOnClickListener(v -> {

            if(et_address.getText().toString().equals("")){
                Toast.makeText(this, "Please Enter your Address", Toast.LENGTH_SHORT).show();
            }
            else{
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy HH:mm:ss a");
                String date = sdf.format(new Date());
                Order order = new Order(itemName, itemPrice, date, et_address.getText().toString(),itemResId);
                final int[] strength = new int[1];
                FirebaseDatabase.getInstance().getReference().child("orders_strength").get().addOnCompleteListener(task -> {
                    strength[0] =  Integer.valueOf(task.getResult().getValue(Integer.class));
                    int orderNum = strength[0] + 1;
                    orderReference.child("orders").child("order" + orderNum).setValue(order);


                    FirebaseDatabase.getInstance().getReference("orders_strength").setValue(orderNum);

                    Toast.makeText(PlaceOrderActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();

                    finish();
                });
            }

        });

    }
}