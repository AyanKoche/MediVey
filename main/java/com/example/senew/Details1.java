package com.example.senew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Details1 extends AppCompatActivity {

    private ImageButton mLogOutButton;
    private FirebaseAuth mAuth;

//    private static final String TAG = "MainActivity";
    private Button nextButton;
    public static EditText firstname;
    public static EditText lastname;
    public static EditText villagename;
    public static Spinner gender;
    public static EditText age;
    public static EditText aadhar;
    public Map<String, Object> user = new HashMap<>();
    public FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);

        mLogOutButton=findViewById(R.id.logOutButton);
        mAuth=FirebaseAuth.getInstance();

        nextButton = (Button)findViewById(R.id.nextButton);
        firstname = (EditText)findViewById(R.id.name);
        lastname = (EditText)findViewById(R.id.name3);
        villagename = (EditText)findViewById(R.id.name2);
        age = (EditText)findViewById(R.id.age);
        aadhar = (EditText)findViewById(R.id.aadhar);
        gender = (Spinner)findViewById(R.id.spinner2);

        mLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(Details1.this,MainActivity.class));
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Details1.this,Details2.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user==null){
            Toast.makeText(Details1.this,"already logged in",Toast.LENGTH_SHORT).show();
        }
    }
}