package com.example.androidclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class MainActivity7 extends AppCompatActivity {
    ImageButton back;
    Button add;
    EditText name,venue,date,des;
    String evn,ven,dt,ds;
    DatabaseReference df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        name=findViewById(R.id.eventadd);
        venue=findViewById(R.id.venueadd);
        date=findViewById(R.id.dateadd);
        des=findViewById(R.id.desadd);
        back=findViewById(R.id.back7);
        add=findViewById(R.id.btn_add);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity7.this,MainActivity7admin.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evn=name.getText().toString();
                ven=venue.getText().toString();
                dt=date.getText().toString();
                ds=des.getText().toString();
                if (TextUtils.isEmpty(evn)) {
                    name.setError("Enter this field!");
                    name.requestFocus();

                    return;
                }
                if (TextUtils.isEmpty(ven)) {
                    venue.setError("Enter this field!");
                    venue.requestFocus();

                    return;
                }
                if (TextUtils.isEmpty(dt)) {
                    date.setError("Enter this field!");
                    date.requestFocus();

                    return;
                }
                if (TextUtils.isEmpty(ds)) {
                    des.setError("Enter this field!");
                    des.requestFocus();

                    return;
                }
                df= FirebaseDatabase.getInstance().getReference("Events");
                String id=df.push().getKey();
                information dataClass=new information(id,evn,ds,ven,dt);
                df.child(id).setValue(dataClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity7.this, "Upload Successful!!!", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(MainActivity7.this,MainActivity7admin.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(MainActivity7.this, "Upload Failed!!!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}