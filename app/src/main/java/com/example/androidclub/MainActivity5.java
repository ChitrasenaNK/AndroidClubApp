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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity5 extends AppCompatActivity {
    TextView title,venue,des,date,status;

    Button btnRegister;
    String e,n,p,b,v,dt,evn,test;
    DatabaseReference df;
    ImageButton back;
    FirebaseUser mUser;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        title=findViewById(R.id.title1);
        venue=findViewById(R.id.venue1);
        date=findViewById(R.id.date1);
        des=findViewById(R.id.txtDes1);

         status=findViewById(R.id.status);
        btnRegister=findViewById(R.id.btnRegister);
        back=findViewById(R.id.back);




         evn=getIntent().getStringExtra("eve");
        title.setText(evn);
        v="VENUE: "+ getIntent().getStringExtra("venue");
        dt="DATE: "+getIntent().getStringExtra("date");
        venue.setText(v);
        date.setText(dt);

        des.setText(getIntent().getStringExtra("des"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity5.this,MainActivity2.class));
            }
        });
         check();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag)
                {
                    addParticipants();


                    Toast.makeText(MainActivity5.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    status.setText("Registered!!");
                }
                else
                {
                    status.setText("Registered Already!!");
                    Toast.makeText(MainActivity5.this, "Registered Already!!!", Toast.LENGTH_LONG).show();

                }





            }
        });


    }
    public void check()
    {
        mUser= FirebaseAuth.getInstance().getCurrentUser();

        e=mUser.getEmail();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(evn);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren()) {
                    data data2=snap.getValue(data.class);

                     test=data2.getEmail();
                    if(test.equals(e))
                    {
                        flag=true;
                    }



                }



            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });


    }
    public void addParticipants()
    {
        mUser= FirebaseAuth.getInstance().getCurrentUser();

       e=mUser.getEmail();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren()) {
                     data data1=snap.getValue(data.class);

                     test=data1.getEmail();
                     if(test.equals(e))
                     {
                         n=data1.getName();
                         b=data1.getBranch();
                         p=data1.getPhone();

                         df= FirebaseDatabase.getInstance().getReference(evn);
                         String id=df.push().getKey();
                         data dataClass=new data(id,n,e,p,b);
                         df.child(id).setValue(dataClass);
                     }



                }



            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });






    }
}