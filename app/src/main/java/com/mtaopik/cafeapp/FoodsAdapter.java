package com.mtaopik.cafeapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.ListViewHolder> {
    private ArrayList<Food> foodData;

    FoodsAdapter(ArrayList<Food> list) {
        this.foodData = list;
    }

    @NonNull
    @Override
    public FoodsAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsAdapter.ListViewHolder holder, int position) {
        Food food = foodData.get(position);
        holder.judul.setText(food.judul);
        holder.harga.setText(String.valueOf(food.harga));
        holder.image.setBackground(food.image);
        holder.image.setImageDrawable(food.image);
    }

    @Override
    public int getItemCount() {
        return foodData.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView image;
        public TextView judul, harga;
        public ListViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_photo);
            judul = itemView.findViewById(R.id.food_name);
            harga = itemView.findViewById(R.id.food_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int selected = getLayoutPosition();
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("id", selected);
            view.getContext().startActivity(intent);
        }
    }
}
