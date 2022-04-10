package com.appdev.utkalfashion.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdev.utkalfashion.ItemView;
import com.appdev.utkalfashion.Models.ShoppingItem;
import com.appdev.utkalfashion.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    private ArrayList<ShoppingItem> cartList = new ArrayList<>();

    public CartAdapter(Context context, ArrayList<ShoppingItem> cartList){
        super();
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_cart_object, parent,
                false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.itemNameTextView.setText(cartList.get(position).getItem_name());
            holder.itemImageView.setImageDrawable(context.getResources().getDrawable(cartList.get(position).getItem_resId()));
            holder.priceTextView.setText(cartList.get(position).getItem_price());

            int amount = Integer.parseInt(cartList.get(position).getItem_price().substring(4)) + 40;

            holder.totalTextView.setText("Rs. " + amount);

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ItemView.class);
                intent.putExtra("item_name", cartList.get(position).getItem_name());
                intent.putExtra("item_price", cartList.get(position).getItem_price());
                intent.putExtra("item_res_id", cartList.get(position).getItem_resId());
                intent.putExtra("item_delivery", cartList.get(position).getItem_delivery());
                context.startActivity(intent);
            });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemNameTextView, priceTextView, expectedDeliveryTextView, totalTextView;
        ImageView itemImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.item_name);
            priceTextView = itemView.findViewById(R.id.item_price);
            itemImageView = itemView.findViewById(R.id.img_view);
            expectedDeliveryTextView = itemView.findViewById(R.id.expected_delivery_text);
            totalTextView = itemView.findViewById(R.id.total_amount_txt);
        }
    }
}

