package com.example.androidclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity8 extends AppCompatActivity {
    ImageButton back;
    ListView listView;
    String evn;
    ArrayList<String> listName = new ArrayList<>();
    ArrayList<String> listBranch = new ArrayList<>();
    ArrayList<String> listPhone = new ArrayList<>();
    ArrayList<String> listEmail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        evn=getIntent().getStringExtra("name");
        listView=findViewById(R.id.listView8);
        display();
        back=findViewById(R.id.back8);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity8.this,MainActivity7admin.class));
            }
        });


    }
    public void display() {

        customAdapter customAdapter=new customAdapter();
        listView.setAdapter(customAdapter);



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(evn);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                listName.clear();
                listEmail.clear();
                listBranch.clear();
                listPhone.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    data info = snap.getValue(data.class);
                    String txtEvent = info.getName();
                    String txtDes = info.getEmail();

                    listName.add(txtEvent);
                    listEmail.add(txtDes);
                    listBranch.add(info.getBranch());
                    listPhone.add(info.getPhone());



                }
                customAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });


    }
    class customAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listName.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            @SuppressLint("ViewHolder") View myView = getLayoutInflater().inflate(R.layout.list_part, null);
            TextView event = myView.findViewById(R.id.txtNameA);
            TextView ven=myView.findViewById(R.id.txtEmailA);
            TextView dt=myView.findViewById(R.id.txtBranchA);
            TextView ph=myView.findViewById(R.id.txtPhA);
            event.setSelected(true);
            event.setText("Name: "+listName.get(position));
            String v,d;
            v="Email: "+listEmail.get(position);
            ven.setSelected(true);
            ven.setText(v);
            d="Branch: "+listBranch.get(position);
            dt.setSelected(true);
            dt.setText(d);
            String pd="Phone: "+listPhone.get(position);
            ph.setSelected(true);
            ph.setText(pd);
            return myView;
        }

    }
}