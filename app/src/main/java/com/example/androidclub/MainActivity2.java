package com.example.androidclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class MainActivity2 extends AppCompatActivity {
    ImageButton img,back2;
    ListView listView;
    ArrayList<String> listEvent = new ArrayList<>();
    ArrayList<String> listDes = new ArrayList<>();
    ArrayList<String> listVenue = new ArrayList<>();
    ArrayList<String> listDate= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.listView);
        display();
              back2=findViewById(R.id.backBtn2);
           img=findViewById(R.id.btnHome2);
           back2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(MainActivity2.this,MainActivity4.class));
               }
           });
           img.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(MainActivity2.this,MainActivity4.class));
               }
           });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String eve=(String) listView.getItemAtPosition(position);
                String des=listDes.get(position);
                String venue=listVenue.get(position);
                String date=listDate.get(position);
                String name=listEvent.get(position);

                startActivity(new Intent(MainActivity2.this,MainActivity5.class)
                        .putExtra("eve",name)
                        .putExtra("des",des)
                        .putExtra("venue",venue)
                        .putExtra("date",date)
                        .putExtra("pos",position));
            }
        });

    }
    public void display() {
        /*ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);*/

        customAdapter customAdapter=new customAdapter();
        listView.setAdapter(customAdapter);



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Events");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
               listEvent.clear();
                listDes.clear();
                  listVenue.clear();
                  listDate.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    information info = snap.getValue(information.class);
                    String txtEvent = info.getName();
                    String txtDes = info.getDes();
                    listEvent.add(txtEvent);
                    listDes.add(txtDes);
                    listVenue.add(info.getVenue());
                    listDate.add(info.getDate());



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
            return listEvent.size();
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
            @SuppressLint("ViewHolder") View myView = getLayoutInflater().inflate(R.layout.list_item, null);
            TextView event = myView.findViewById(R.id.txtEvent);
            TextView ven=myView.findViewById(R.id.txtVenue);
            TextView dt=myView.findViewById(R.id.txtDate);
            event.setSelected(true);
            event.setText(listEvent.get(position));
            String v,d;
            v="Venue: "+listVenue.get(position);
            ven.setSelected(true);
            ven.setText(v);
            d="Date: "+listDate.get(position);
            dt.setSelected(true);
            dt.setText(d);
            return myView;
        }

    }
    }
