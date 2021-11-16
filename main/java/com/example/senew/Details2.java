package com.example.senew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Details2 extends AppCompatActivity{

    private Button nextButton2;
    public static Spinner spinnerD1;
    public static Spinner spinnerD2;
    public static Spinner spinnerD3;
    public static Spinner spinnerD4;
    public static Spinner spinnerD5;
    private TextView change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);

        change=findViewById(R.id.surveyTitle);

        nextButton2=findViewById(R.id.nextButton2);
        spinnerD1=findViewById(R.id.spinnerD1);
        spinnerD2=findViewById(R.id.spinnerD2);
        spinnerD3=findViewById(R.id.spinnerD3);
        spinnerD4=findViewById(R.id.spinnerD4);
        spinnerD5=findViewById(R.id.spinnerD5);

        nextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Details2.this,Details3.class));
            }
        });
    }
}