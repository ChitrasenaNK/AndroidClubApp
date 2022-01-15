package com.example.androidclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    Button btn,signup;
    EditText email,password;
    TextView forgot;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        forgot=findViewById(R.id.forgot);
        signup=findViewById(R.id.signup);
       mAuth = FirebaseAuth.getInstance();
       email.setText("");
       password.setText("");

        btn=findViewById(R.id.btn_login);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginAccount();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));


            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity6.class));
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser mUser=mAuth.getCurrentUser();
        if(mUser!=null)
        {
            startActivity(new Intent(MainActivity.this,MainActivity4.class));
        }
    }

    public void loginAccount()
    {



        // Take the value of two edit texts in Strings
        String e, p;
        e = email.getText().toString();

        p = password.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(e)) {
            email.setError("Enter this field!");
            email.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Please Enter your email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }if (TextUtils.isEmpty(p)) {
            password.setError("Enter this field!");
            password.requestFocus();
        Toast.makeText(getApplicationContext(),
                "Please Enter Password!!",
                Toast.LENGTH_LONG)
                .show();
        return;
    }
        mAuth.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "Login successful!!",
                                            Toast.LENGTH_SHORT)
                                            .show();



                                    // if sign-in is successful
                                    // intent to home activity
                                   Intent intent
                                            = new Intent(MainActivity.this,
                                            MainActivity4.class);
                                    startActivity(intent);
                                } else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                            "Login failed!! Email or password INCORRECT",
                                            Toast.LENGTH_LONG)
                                            .show();



                                }
                            }
                        });

    }
}