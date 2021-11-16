package com.example.senew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Details3 extends AppCompatActivity {

    private Button offlineSync;
    private Button onlineSync;
    public static Spinner spinnerD6;
    public static Spinner spinnerD7;
    public static Spinner spinnerAccess;
    public static Spinner spinnerAvailable;
    public static EditText mediHistory;

    private Details1 mDetails1=new Details1();
    private Details2 mDetails2=new Details2();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details3);

        onlineSync=findViewById(R.id.onlineSync);
        offlineSync=findViewById(R.id.offlineSync);
        spinnerD6=findViewById(R.id.spinnerD6);
        spinnerD7=findViewById(R.id.spinnerD7);
        spinnerAccess=findViewById(R.id.spinnerAccess);
        spinnerAvailable=findViewById(R.id.spinnerAvailable);
        mediHistory=findViewById(R.id.mediHistory);

        onlineSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDetails1.user.put("first-name", mDetails1.firstname.getText().toString());
                mDetails1.user.put("last-name", mDetails1.lastname.getText().toString());
                mDetails1.user.put("age",Integer.parseInt(mDetails1.age.getText().toString()));
                mDetails1.user.put("gender",mDetails1.gender.getSelectedItem().toString());
                mDetails1.user.put("aadhar", Integer.parseInt(mDetails1.aadhar.getText().toString()));

                mDetails1.user.put("Disease 1",mDetails2.spinnerD1.getSelectedItem().toString());
                mDetails1.user.put("Disease 2",mDetails2.spinnerD2.getSelectedItem().toString());
                mDetails1.user.put("Disease 3",mDetails2.spinnerD3.getSelectedItem().toString());
                mDetails1.user.put("Disease 4",mDetails2.spinnerD4.getSelectedItem().toString());
                mDetails1.user.put("Disease 5",mDetails2.spinnerD5.getSelectedItem().toString());

                mDetails1.user.put("Disease 6",spinnerD6.getSelectedItem().toString());
                mDetails1.user.put("Disease 7",spinnerD7.getSelectedItem().toString());
                mDetails1.user.put("Hospital-Accessibility",spinnerAccess.getSelectedItem().toString());
                mDetails1.user.put("Pure-Water-Availability",spinnerAvailable.getSelectedItem().toString());
                mDetails1.user.put("Medical-History", mediHistory.getText().toString());


                mDetails1.db.collection(mDetails1.villagename.getText().toString()).document(mDetails1.aadhar.getText().toString())
                        .set(mDetails1.user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("Tag", "DocumentSnapshot successfully written!");
                                Toast.makeText(Details3.this,"successful",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error writing document", e);
                                Toast.makeText(Details3.this,"error",Toast.LENGTH_SHORT).show();
                            }
                        });

                startActivity(new Intent(Details3.this,Details1.class));
            }
        });


        offlineSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File file = new File(getFilesDir() + "newj.json");
                    JSONObject jsonObject = new JSONObject(mDetails1.user);
                    jsonObject.put("Village-name",mDetails1.villagename);
                    BufferedWriter output;
                    output = new BufferedWriter(new FileWriter(file,true));
                    output.write(jsonObject.toString());
                    output.close();

                    Toast.makeText(Details3.this,"Successfully saved.",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}