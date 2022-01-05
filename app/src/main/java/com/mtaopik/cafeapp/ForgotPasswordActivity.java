/*
 * Created by Muhamad Taopik on 1/5/22, 6:07 PM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 1/5/22, 12:13 PM
 */

package com.mtaopik.cafeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Forgot Password");
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forgot_password );

        emailEditText = (EditText ) findViewById( R.id.email_text );
        resetPasswordButton = (Button ) findViewById( R.id.reset_button );

        progressBar = (ProgressBar ) findViewById( R.id.progressBar );

        auth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        } );
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();

        if(email.isEmpty()) {
            emailEditText.setError( "Email is required!" );
            emailEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher( email ).matches()) {
            emailEditText.setError( "Please provide a valid email!" );
            emailEditText.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail( email ).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()) {
                    progressBar.setVisibility( View.VISIBLE);
                    Toast.makeText( ForgotPasswordActivity.this, "Check your email to reset your password!", Toast.LENGTH_LONG ).show();
                    Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText( ForgotPasswordActivity.this, "Email tidak terdaftar! Coba lagi!", Toast.LENGTH_LONG ).show();
                }
            }
        } );
    }
}