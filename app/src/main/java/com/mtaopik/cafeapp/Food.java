package com.mtaopik.cafeapp;

import android.graphics.drawable.Drawable;

public class Food {
    private int id;
    private String judul;
    private String harga;
    private String deskripsi;
    byte[] image;

    public  Food (int id, String judul, String harga, String deskripsi, byte[] image) {
        this.id = id;
        this.judul = judul;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
