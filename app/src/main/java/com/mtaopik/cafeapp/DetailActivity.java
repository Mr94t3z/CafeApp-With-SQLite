package com.mtaopik.cafeapp;

import androidx.appcompat.app.AppCompatActivity;

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
            TextView judulCF = findViewById(R.id.detail_judul);
            TextView deskripsiCF = findViewById(R.id.detail_deskripsi);
            ImageView image = findViewById(R.id.detail_photo);

            image.setImageDrawable(food.image);
            judulCF.setText(food.judul);
            deskripsiCF.setText(food.deskripsi);
        }
    }
}