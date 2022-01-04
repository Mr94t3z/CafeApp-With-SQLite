package com.mtaopik.cafeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//public class FoodsAdapter extends BaseAdapter {
//
//    private Context context;
//    private int layout;
//    private ArrayList<Food> foodList;
//
//    public FoodsAdapter(ArrayList<Food> foodList) {
//        this.context = context;
//        this.layout = layout;
//        this.foodList = foodList;
//    }
//
//    @Override
//    public int getCount() {
//        return foodList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return foodList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    private class ViewHolder {
//        ImageView imageView;
//        TextView txtJudul, txtHarga, txtDeskripsi;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        View row = convertView;
//        ViewHolder holder = new ViewHolder();
//
//        if(row == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            row = inflater.inflate(layout, null);
//
//            holder.txtJudul = (TextView) row.findViewById(R.id.food_name);
//            holder.txtHarga = (TextView) row.findViewById(R.id.food_price);
//            holder.txtDeskripsi = (TextView) row.findViewById(R.id.detail_deskripsi);
//            holder.imageView = (ImageView) row.findViewById(R.id.image_photo);
//            row.setTag(holder);
//        } else {
//            holder = (ViewHolder) row.getTag();
//        }
//
//        Food food = foodList.get(position);
//
//        holder.txtJudul.setText(food.getJudul());
//        holder.txtHarga.setText(food.getHarga());
//        holder.txtDeskripsi.setText(food.getDeskripsi());
//
//        byte[] foodImage = food.getImage();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
//        holder.imageView.setImageBitmap(bitmap);
//
//        return row;
//    }
//}

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
//        holder.image.setBackground(food.image);
//        holder.image.setImageDrawable(food.image);
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
