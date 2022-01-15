package com.example.androidclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity6 extends AppCompatActivity {
    EditText mail;
    Button btn;
     FirebaseAuth mAuth;
     String email;
     ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        mail=findViewById(R.id.emailForgot);
        btn=findViewById(R.id.btnForgot);
        mAuth=FirebaseAuth.getInstance();
        back=findViewById(R.id.back6);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity6.this,MainActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=mail.getText().toString();
                if(TextUtils.isEmpty(email)) {
                    mail.setError("Enter email");
                    mail.requestFocus();
                    return;

                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                    mail.setError("Enter valid email");
                    mail.requestFocus();
                    return;
                }

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity6.this, "Reset link sent to your Email", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity6.this,MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(MainActivity6.this, "Error!! Please try after some time!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}