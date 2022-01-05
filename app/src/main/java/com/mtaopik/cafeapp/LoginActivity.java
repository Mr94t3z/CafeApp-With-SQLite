/*
 * Created by Muhamad Taopik on 1/5/22, 6:08 PM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 1/5/22, 6:08 PM
 */

package com.mtaopik.cafeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register, forgotPassword;
    private EditText edtEmail, edtPassword;
    private Button signIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle( "Login" );
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        register = ( TextView ) findViewById( R.id.Register );
        register.setOnClickListener( this );

        forgotPassword = ( TextView ) findViewById( R.id.ForgotPassword );
        forgotPassword.setOnClickListener( this );

        signIn = ( Button ) findViewById( R.id.login_button );
        signIn.setOnClickListener( this );

        edtEmail = ( EditText ) findViewById( R.id.email_text );
        edtPassword = ( EditText ) findViewById( R.id.password_text );

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Register:
                startActivity( new Intent( this, RegisterActivity.class ) );
                break;
            case R.id.login_button:
                inputData();
                userLogin();
                break;
            case R.id.ForgotPassword:
                startActivity( new Intent( this, ForgotPasswordActivity.class ) );
                break;
        }
    }

    public  void inputData(){
        // Database Helper
        DatabaseHelper database = new DatabaseHelper(getApplicationContext());
        ArrayList<Food> data = FoodData.getData(getApplicationContext());

        for (Food food: data) {
            database.addData(food);
        }
    }

    private void userLogin() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (email.isEmpty()) {
            edtEmail.setError( "Email is required!" );
            edtEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher( email ).matches()) {
            edtEmail.setError( "Please enter a valid email!" );
            edtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edtPassword.setError( "Password is required!" );
            edtPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            edtPassword.setError( "Min password length is 6 characters!" );
            edtPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword( email, password ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()) {
                        //redirect to Home
                        startActivity( new Intent( LoginActivity.this, HomeActivity.class ) );
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText( LoginActivity.this, "Cek email Anda untuk verifikasi akun!", Toast.LENGTH_LONG ).show();
                    }

                } else {
                    Toast.makeText( LoginActivity.this, "Gagal login! Pastikan Email dan Password Anda benar!", Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }
}

//        Button loginButton = findViewById(R.id.login_button);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                login();
//            }
//        });

//    public void login() {
//
//        TextView usernameTV = findViewById(R.id.email_text);
//        TextView passwordTV = findViewById(R.id.password_text);
//
//        String username = usernameTV.getText().toString();
//        String password = passwordTV.getText().toString();
//
//        Log.d("print", "Username = " + username + " dan Password = " + password);
//        Log.d("print", String.valueOf(username.equals("Muhamad Taopik")));
//        Log.d("print", String.valueOf(password.equals("1q2w3e4r")));
//
//        if (username.equals("Muhamad Taopik") && password.equals("1q2w3e4r")) {
//            Log.d("success", "Login Sukses!");
//            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
//            startActivity(intent);
//            this.finish();
//        }  else {
//            Log.d("failed", "Login Gagal!");
//            Toast toast = Toast.makeText(getApplicationContext(), "Username atau password salah!", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }
