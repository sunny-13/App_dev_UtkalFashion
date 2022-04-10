package com.appdev.utkalfashion.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdev.utkalfashion.Models.Order;
import com.appdev.utkalfashion.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    private ArrayList<Order> orderList = new ArrayList<>();

    public OrderAdapter(Context context, ArrayList<Order> orderList){
        super();
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_ordered_object, parent,
                false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemNameTextView.setText(orderList.get(position).getItem_name());
        holder.itemImageView.setImageDrawable(context.getResources().getDrawable(orderList.get(position).getItem_resId()));
        holder.priceTextView.setText(orderList.get(position).getItem_price());
        holder.orderedOnTextView.setText(orderList.get(position).getItem_ordered_date());
        holder.addressTextView.setText(orderList.get(position).getItem_address());

        int amount = Integer.parseInt(orderList.get(position).getItem_price().substring(4)) + 40;
        holder.totalTextView.setText("Rs. " + amount);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemNameTextView, priceTextView, orderedOnTextView, totalTextView, addressTextView;
        ImageView itemImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.item_name);
            priceTextView = itemView.findViewById(R.id.item_price);
            itemImageView = itemView.findViewById(R.id.img_view);
            orderedOnTextView = itemView.findViewById(R.id.ordered_on_txtView);
            addressTextView = itemView.findViewById(R.id.address_text_view);
            totalTextView = itemView.findViewById(R.id.total_amount_txt);
        }
    }
}

