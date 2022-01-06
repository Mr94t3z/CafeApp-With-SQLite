/*
 * Created by Muhamad Taopik on 1/5/22, 6:07 PM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 1/5/22, 5:18 PM
 */

package com.mtaopik.cafeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Menu Cafe");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Database Helper
        DatabaseHelper database = new DatabaseHelper(getApplicationContext());

        ArrayList<Food> data = (ArrayList<Food>)database.getFoodList(getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.list_item_view);
        FoodsAdapter adapter = new FoodsAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

//public class HomeActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        this.setTitle("Menu Cafe");
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        ArrayList<Food> data = FoodData.getData(getApplicationContext());
//        RecyclerView recyclerView = findViewById(R.id.list_item_view);
//        FoodsAdapter adapter = new FoodsAdapter(data);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.logout:
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//                this.finish();
//                break;
//            default:
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}