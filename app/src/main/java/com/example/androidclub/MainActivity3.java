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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class MainActivity3 extends AppCompatActivity {
    EditText email,pswd,cpswd,name,ph,branch;
    Button btnSignup;
    ImageButton back;
    FirebaseAuth mAuth;
    DatabaseReference df;
      String mail;
    String e,p,cp,phn,n,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.emailSign);
          name=findViewById(R.id.nameSign);
          ph=findViewById(R.id.phoneSign);
          branch=findViewById(R.id.branchSign);
        pswd=findViewById(R.id.passwordSign);
        cpswd=findViewById(R.id.confirmPassword);
        btnSignup=findViewById(R.id.btn_signup);
        back=findViewById(R.id.back3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this,MainActivity.class));
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });
    }

    public void registerNewUser()
    {



        e = email.getText().toString();
         n=name.getText().toString();
         phn=ph.getText().toString();
         b=branch.getText().toString();
        p = pswd.getText().toString();
        cp=cpswd.getText().toString();
       mail=e+"1";

        // Validations for input email and password

        if (TextUtils.isEmpty(e)) {
            email.setError("Enter this field!");
            email.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Please Enter Email!!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(e).matches())
        {
            email.setError("Email invalid!");
            email.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Please Enter a Valid Email!!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;

        }
        if (TextUtils.isEmpty(n)) {
            name.setError("Enter this field!");
            name.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Please Enter Name!!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(b)) {
            branch.setError("Enter this field!");
            branch.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Please Enter Branch!!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(phn)) {
            ph.setError("Enter this field!");
            ph.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Please Enter Phone!!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(p)) {
            pswd.setError("Enter this field!");
            pswd.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Please Enter Password!!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(cp)) {
            Toast.makeText(getApplicationContext(),
                    "Please Enter confirm Password!!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if(p.length()<6)
        {
            pswd.setError("Too Short!!");
            pswd.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Passwords Too Short !!",
                    Toast.LENGTH_SHORT)
                    .show();
            pswd.setText("");
            cpswd.setText("");
            return;

        }
        if(phn.length()!=10)
        {
            ph.setError("Invalid Phone Number!");
            ph.requestFocus();
            Toast.makeText(getApplicationContext(),
                    "Invalid phone Number !!",
                    Toast.LENGTH_SHORT)
                    .show();
            ph.setText("");

            return;

        }


        if(!TextUtils.equals(p,cp))
        {

            Toast.makeText(getApplicationContext(),
                    "Passwords does not match !!",
                    Toast.LENGTH_SHORT)
                    .show();
            pswd.setText("");
            cpswd.setText("");
            return;

        }
        mAuth
                .createUserWithEmailAndPassword(e,p)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "User registered Successfully",
                                    Toast.LENGTH_SHORT)
                                    .show();

                           df=  FirebaseDatabase.getInstance().getReference("user");
                          // User u=new User(n,e,phn,b);


                            String id=df.push().getKey();
                            data dataClass=new data(id,n,e,phn,b);
                            df.child(id).setValue(dataClass);




                            startActivity(new Intent(MainActivity3.this,MainActivity4.class));
                        }else {

                            // Registration failed
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();


                        }
                    }
                });


    }
    }
