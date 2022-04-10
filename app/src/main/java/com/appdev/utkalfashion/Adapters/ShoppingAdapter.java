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

import com.appdev.utkalfashion.DataUtils;
import com.appdev.utkalfashion.ItemView;
import com.appdev.utkalfashion.Models.ShoppingItem;
import com.appdev.utkalfashion.PlaceOrderActivity;
import com.appdev.utkalfashion.R;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

    Context context;
    String category;

    public ShoppingAdapter(Context context, String category){
        super();
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_card_item, parent,
                false);
        return new ShoppingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ShoppingItem item = DataUtils
                .getShoppingItemsList(category).get(position);

        holder.itemNameTextView.setText(item.getItem_name());
        holder.priceTextView.setText(item.getItem_price());
        holder.itemImageView.setImageDrawable(context.getResources().getDrawable(item.getItem_resId()));

        holder.itemImageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemView.class);
            intent.putExtra("item_name", item.getItem_name());
            intent.putExtra("item_price", item.getItem_price());
            intent.putExtra("item_res_id", item.getItem_resId());
            intent.putExtra("item_delivery", item.getItem_delivery());
            context.startActivity(intent);
        });
        holder.addToCartImageView.setOnClickListener(v -> {

        });

        holder.buyNowImageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlaceOrderActivity.class);
            intent.putExtra("item_name", item.getItem_name());
            intent.putExtra("item_price", item.getItem_price());
            intent.putExtra("item_res_id", item.getItem_resId());
            intent.putExtra("item_delivery", item.getItem_delivery());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return DataUtils.getShoppingItemsList(category).size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemNameTextView, priceTextView;
        ImageView itemImageView, addToCartImageView, buyNowImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.sample_shopping_item_name);
            priceTextView = itemView.findViewById(R.id.sample_shopping_item_price);
            itemImageView = itemView.findViewById(R.id.sample_shopping_item_resource);
            addToCartImageView = itemView.findViewById(R.id.sample_shopping_add_to_cart);
            buyNowImageView = itemView.findViewById(R.id.sample_shopping_buy_now);
        }
    }
}
