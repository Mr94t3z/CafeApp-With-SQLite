/*
 * Created by Muhamad Taopik on 1/5/22, 6:06 PM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 1/5/22, 2:14 PM
 */

package com.mtaopik.cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Detail Menu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getIntExtra("id", -1);

        ArrayList<Food> list = FoodData.getData(getApplicationContext());
        Food food = list.get(id);

        if (food != null) {
            TextView judul = findViewById(R.id.detail_judul);
            TextView deskripsi = findViewById(R.id.detail_deskripsi);
            ImageView image = findViewById(R.id.detail_photo);

            image.setImageDrawable(food.image);
            judul.setText(food.judul);
            deskripsi.setText(food.deskripsi);
        }
    }
}

//public class DetailActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        this.setTitle("Detail Menu");
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//
//        int id = getIntent().getIntExtra("id", -1);
//
//        ArrayList<Food> list = FoodData.getData(getApplicationContext());
//        Food food = list.get(id);
//
//        if (food != null) {
//            TextView judulCF = findViewById(R.id.detail_judul);
//            TextView deskripsiCF = findViewById(R.id.detail_deskripsi);
//
//            byte[] blob = food.getImage();
//            Bitmap bmp = BitmapFactory.decodeByteArray(blob,0, blob.length);
//            ImageView image = findViewById(R.id.detail_photo);
//            image.setImageBitmap(bmp);
//
//            judulCF.setText(food.judul);
//            deskripsiCF.setText(food.deskripsi);
//        }
//    }
//}