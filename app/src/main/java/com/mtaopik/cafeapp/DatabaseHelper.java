/*
 * Created by Muhamad Taopik on 1/5/22, 6:06 PM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 1/5/22, 4:00 PM
 */

package com.mtaopik.cafeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CafeAppDB.sqlite";

    // Table Name
    private static final String TABLE_MENU = "cafes";

    // Column Tables
    private static final String KEY_ID = "id";
    private static final String KEY_TITTLE = "judul";
    private static final String KEY_DESCRIPTION = "deskripsi";
    private static final String KEY_PRICE = "harga";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CAFES_TABLE = ""
                + "CREATE TABLE " + TABLE_MENU + ""
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITTLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_PRICE + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_CAFES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        onCreate(sqLiteDatabase);
    }

    public void addData(Food menu){
        SQLiteDatabase sqLiteDatabase  = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITTLE, menu.judul);
        values.put(KEY_DESCRIPTION, menu.deskripsi);
        values.put(KEY_PRICE, menu.harga);

        sqLiteDatabase.insert(TABLE_MENU, null, values);
        sqLiteDatabase.close();
    }

    public List<Food> getFoodList(Context context) {
        List<Food> contactList = new ArrayList<Food>();
        // SELECT * FROM cafes
        String selectQuery = "SELECT * FROM " + TABLE_MENU + " LIMIT 6";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Integer> image = FoodData.getImage();

        int index = 0;
        if (cursor.moveToFirst()) {
            do {
                if(index%6==0) {
                    index = 0;
                }
                String judul = cursor.getString(1);
                String deskripsi = cursor.getString(2);
                String harga = cursor.getString(3);

                Food menu = new Food(judul, deskripsi, harga, context.getDrawable(image.get(index)));

                contactList.add(menu);
                index++;
            } while (cursor.moveToNext());
        }
        return contactList;
    }
}

//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    public void queryData(String sql) {
//        SQLiteDatabase database = getWritableDatabase();
//        database.execSQL(sql);
//    }
//
//    public void insertData(String judul, String harga, String deskripsi, byte[] image) {
//        SQLiteDatabase database = getWritableDatabase();
//        String sql = "INSERT INTO FOOD VALUES (NULL, ?, ?, ?, ?)";
//
//        SQLiteStatement statement = database.compileStatement(sql);
//        statement.clearBindings();
//
//        statement.bindString(1, judul);
//        statement.bindString(2, harga);
//        statement.bindString(3, deskripsi);
//        statement.bindBlob(4, image);
//
//        statement.executeInsert();
//    }
//
//    public Cursor getData(String sql) {
//        SQLiteDatabase database = getReadableDatabase();
//        return database.rawQuery(sql, null);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//}
