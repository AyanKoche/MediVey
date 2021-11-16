package com.example.senew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText mEmailAddress;
    private EditText mPassword;
    private Button mSignInButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSignInButton=findViewById(R.id.signInButton);
        mEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        mPassword=findViewById(R.id.editTextTextPassword);
        mAuth=FirebaseAuth.getInstance();

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInUser();
            }
        });
    }

    private void logInUser(){
        String email=mEmailAddress.getText().toString();
        String password=mPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            mEmailAddress.setError("Email cannot be empty");
            mEmailAddress.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            mPassword.setError("Password cannot be empty");
            mPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"User logged in successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Details1.class));
                    }else{
                        Toast.makeText(MainActivity.this,"Log in failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}