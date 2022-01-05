/*
 * Created by Muhamad Taopik on 1/5/22, 6:08 PM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 1/5/22, 10:50 AM
 */

package com.mtaopik.cafeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, Register;
    private EditText edtUsername, edtEmail, edtPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Register");
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById( R.id.CafeApp );
        banner.setOnClickListener( this );

        Register = (Button) findViewById( R.id.Register );
        Register.setOnClickListener( this );

        edtUsername = (EditText) findViewById( R.id.username_text );
        edtEmail = (EditText) findViewById( R.id.email_text );
        edtPassword = (EditText) findViewById( R.id.password_text );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CafeApp:
                startActivity( new Intent(this, LoginActivity.class) );
                break;
            case R.id.Register:
                Register();
                break;
        }
    }

    private void Register() {
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(username.isEmpty()) {
            edtUsername.setError( "Full name is required!" );
            edtUsername.requestFocus();
            return;
        }

        if(email.isEmpty()) {
            edtEmail.setError( "Email is required!" );
            edtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher( email ).matches()) {
            edtEmail.setError( "Please provide a valid email!" );
            edtEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            edtPassword.setError( "Password is required!" );
            edtPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            edtPassword.setError( "Min password length should be 6 characters!" );
            edtPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword( email, password )
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            User user = new User( username, email );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child( FirebaseAuth.getInstance().getCurrentUser().getUid() )
                                    .setValue( user ).addOnCompleteListener( new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()) {
                                        Toast.makeText( RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();

                                        // redirect to login layout!
                                    } else {
                                        Toast.makeText( RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            } );

                        } else {
                            Toast.makeText( RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                } );
    }
}