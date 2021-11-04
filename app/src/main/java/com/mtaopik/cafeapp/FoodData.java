package com.mtaopik.cafeapp;

import android.content.Context;

import java.util.ArrayList;

public class FoodData {
    public static ArrayList<Food> getData(Context context) {
        ArrayList<Food> list = new ArrayList<Food>();
        list.add(new Food(
                "Mie Goreng",
                "Mie goreng berarti mie yang digoreng adalah makanan yang berasal dari Indonesia yang populer dan juga digemari di Malaysia, dan Singapura. Mi goreng terbuat dari mi kuning yang digoreng dengan sedikit minyak goreng, dan ditambahkan bawang putih, bawang merah, udang serta daging ayam atau daging sapi, irisan bakso, cabai, sayuran, tomat, telur ayam, dan acar.",
                "Rp.15000",
                context.getDrawable(R.drawable.mie_goreng)));
        list.add(new Food(
                "Cireng",
                "Cireng adalah makanan ringan yang berasal dari daerah Sunda yang dibuat dengan cara menggoreng campuran adonan yang berbahan utama tepung kanji atau tapioka. Makanan ringan ini sangat populer di daerah Priangan, dan dijual dalam berbagai bentuk dan variasi rasa. Makanan ini cukup terkenal pada era 80-an.",
                "Rp.7000",
                context.getDrawable(R.drawable.cireng)));
        list.add(new Food(
                "Caphuchino",
                "Kapucino adalah minuman khas Italia yang dibuat dari espreso dan susu, tetapi referensi lain juga ada yang menyebutkan bahwa kapucino berawal dari biji biji kopi tentara Turki yang tertinggal setelah peperangan yang di pimpin oleh Kara Mustapha Pasha di Wina, Austria melawan tentara gabungan Polandia-Germania.",
                "Rp.8000",
                context.getDrawable(R.drawable.cappuchino)));
        list.add(new Food(
                "Donut",
                "Donat adalah penganan yang digoreng, dibuat dari adonan tepung terigu, gula, telur, dan mentega. Donat yang paling umum adalah donat berbentuk cincin dengan lubang di tengah dan donat berbentuk bundar dengan isian manis, seperti selai, jelly, krim, dan custard.",
                "Rp.5000",
                context.getDrawable(R.drawable.donut)));
        list.add(new Food(
                "Sparkling Tea",
                "Minuman ini dijamin bikin segaar. Perpaduan rasa asamnya strawberry yang segar dicampur dengan teh dan soda. Terbayang bukan sensainya segarnya? Yuk kita coba membuatnya! Minuman ini bisa disajikan siang hari di sela waktu istirahat Anda, ataupun dijadikan menu berbuka Anda selepas Magrib nanti. Selamat mencoba!",
                "Rp.9000",
                context.getDrawable(R.drawable.sparkling_tea)));
        list.add(new Food(
                "Nasi Goreng",
                "Nasi goreng adalah sebuah makanan berupa nasi yang digoreng dan diaduk dalam minyak goreng, margarin, atau mentega. Biasanya ditambah kecap manis, bawang merah, bawang putih, asam jawa, lada dan bumbu-bumbu lainnya; seperti telur, ayam, dan kerupuk.",
                "Rp.12000",
                context.getDrawable(R.drawable.nasigoreng)));
        return list;
    }
}
