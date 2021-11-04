package com.mtaopik.cafeapp;

import android.graphics.drawable.Drawable;

public class Food {
    String judul;
    String deskripsi;
    String harga;
    Drawable image;

    Food(String judul, String deskripsi, String harga, Drawable image) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.image = image;
    }
}
