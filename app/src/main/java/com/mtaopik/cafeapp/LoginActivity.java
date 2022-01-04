package com.mtaopik.cafeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Login");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {

        TextView usernameTV = findViewById(R.id.username_text);
        TextView passwordTV = findViewById(R.id.password_text);

        String username = usernameTV.getText().toString();
        String password = passwordTV.getText().toString();

        Log.d("print", "Username = " + username + " dan Password = " + password);
        Log.d("print", String.valueOf(username.equals("Muhamad Taopik")));
        Log.d("print", String.valueOf(password.equals("1q2w3e4r")));

        if (username.equals("Muhamad Taopik") && password.equals("1q2w3e4r")) {
            Log.d("success", "Login Sukses!");
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(intent);
            this.finish();
        }  else {
            Log.d("failed", "Login Gagal!");
            Toast toast = Toast.makeText(getApplicationContext(), "Username atau password salah!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}