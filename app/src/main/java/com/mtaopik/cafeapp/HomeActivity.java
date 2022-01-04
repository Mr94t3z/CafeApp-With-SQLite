package com.mtaopik.cafeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Currency;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Food> list;
    FoodsAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Menu Cafe");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.list_item_view);
        list = new ArrayList<>();
        adapter = new FoodsAdapter(this, R.layout.food_item, list);
        //recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = AddActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String judul = cursor.getString(1);
            String harga = cursor.getString(2);
            String deskripsi = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            list.add(new Food(id, judul, harga, deskripsi, image));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}