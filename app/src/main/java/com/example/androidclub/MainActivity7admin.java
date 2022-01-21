package com.example.androidclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity7admin extends AppCompatActivity {
    ListView listView;
    ArrayList<String> listEvent = new ArrayList<>();
    ArrayList<String> listId = new ArrayList<>();
    ArrayList<String> listDes = new ArrayList<>();
    ArrayList<String> listVenue = new ArrayList<>();
    ArrayList<String> listDate= new ArrayList<>();
    Button addEvent;
    ImageButton log;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity7admin);
        listView = findViewById(R.id.listView);
        display();
        addEvent=findViewById(R.id.addEvent);
        log=findViewById(R.id.logOutAdmin);
        mAuth=FirebaseAuth.getInstance();
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(MainActivity7admin.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity7admin.this,MainActivity.class));
            }
        });
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(MainActivity7admin.this,MainActivity7.class));
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert =new AlertDialog.Builder(MainActivity7admin.this);
                alert.setTitle("Are you sure to delete?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteEvent(position);
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();



                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=listEvent.get(position);
                startActivity(new Intent(MainActivity7admin.this,MainActivity8.class).putExtra("name",name));
            }
        });

    }

    private void deleteEvent(int pos) {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Events").child(listId.get(pos));
        ref.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity7admin.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(MainActivity7admin.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void display() {

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
                    listId.add(info.getId());
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
            @SuppressLint("ViewHolder") View myView = getLayoutInflater().inflate(R.layout.list_itemadmin, null);
            TextView event = myView.findViewById(R.id.txtEventA);
            TextView ven=myView.findViewById(R.id.txtVenueA);
            TextView dt=myView.findViewById(R.id.txtDateA);
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