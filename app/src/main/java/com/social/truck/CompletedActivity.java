package com.social.truck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_register);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationmenu);
        bottomNavigationView.setSelectedItemId(R.id.completed);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent intent;
                switch (item.getItemId()){
                    case R.id.Booking:
                        intent = new Intent(getApplicationContext(),BookingActivity.class);
                        overridePendingTransition(0,0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;
                    case R.id.provide:
                        intent = new Intent(getApplicationContext(),ProviderActivity.class);
                        overridePendingTransition(0,0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                    case R.id.ongoing:
                        intent = new Intent(getApplicationContext(),ActivityActivity.class);
                        overridePendingTransition(0,0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;
                    case  R.id.completed:
                        return true;


                }

                return false;
            }
        });

        Button button = findViewById(R.id.Logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        });
        uid=  FirebaseAuth.getInstance().getUid().toString();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Customer").child(uid).child("Requests");
        Query query = rootRef.orderByChild("Requests");
        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<String> vehicle = new ArrayList<>();
                final ArrayList<String> materialtype = new ArrayList<>();
                final ArrayList<String> weight = new ArrayList<>();
                final ArrayList<String> count = new ArrayList<>();
                final ArrayList<String> location = new ArrayList<>();
                final ArrayList<String> drivermobile = new ArrayList<>();
                final ArrayList<String> vehiclenumber = new ArrayList<>();


                for(DataSnapshot ds : dataSnapshot.getChildren()) {


                    if(ds.child("Status").getValue(String.class).equals("1")){
                        String name = ds.child("Vehicle").getValue(String.class);
                        vehicle.add(name);
                        name =  ds.child("MaterialType").getValue(String.class);
                        materialtype.add(name);
                        weight.add(ds.child("Weight").getValue(String.class));
                        count.add(ds.getKey());
                        location.add(ds.child("Location").getValue(String.class));
                        drivermobile.add(ds.child("DriverNumber").getValue(String.class));
                        vehiclenumber.add(ds.child("VehicleNumber").getValue(String.class));

                    }


//                        count.add(ds.child("Number").getValue(String.class));


                }

                final ListView listView = (ListView) findViewById(R.id.list);
                Adapter arrayAdapter = new Adapter(getApplicationContext(),vehicle,materialtype,weight,location,drivermobile,vehiclenumber);
                listView.setAdapter(arrayAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("INFO", databaseError.getMessage()); //Don't ignore errors!
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);
    }
    String uid;  @Override
    public void onBackPressed() {
        overridePendingTransition(0,0);
        super.onBackPressed();
    }


    class Adapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> rvehicle ;
        ArrayList<String> rmaterialtype ;
        ArrayList<String> rweight ;
        ArrayList<String> rlocation;
        ArrayList<String> rdrivernumber;
        ArrayList<String> rvehiclenumber;



        Adapter (Context c, ArrayList<String> vehicle,ArrayList<String> materialtype,ArrayList<String> weight, ArrayList<String> location,ArrayList<String> drivernumber,ArrayList<String> vehiclenumber){
            super(c,R.layout.list,vehicle);
            this.context=c;
            this.rvehicle=vehicle;
            this.rmaterialtype = materialtype;
            this.rweight = weight;
            this.rlocation = location;
            this.rdrivernumber = drivernumber;
            this.rvehiclenumber = vehiclenumber;


        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list2,parent,false);
            TextView pv = row.findViewById(R.id.pvehicle);
            TextView pm = row.findViewById(R.id.pmaterialtype);
            TextView pw = row.findViewById(R.id.pweight);
            TextView pl = row.findViewById(R.id.plocation);
            TextView vn = row.findViewById(R.id.pvehiclenumber);
            TextView dn = row.findViewById(R.id.pdrivernumber);

            Button button = (Button) row.findViewById(R.id.pbutton);

            pv.setText(rvehicle.get(position));
            pm.setText(rmaterialtype.get(position));
            pw.setText(rweight.get(position));
            pl.setText(rlocation.get(position));
            dn.setText(rdrivernumber.get(position));
            vn.setText(rvehiclenumber.get(position));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Tracking.class);
                    intent.putExtra("mobile",rdrivernumber.get(position));
                    startActivity(intent);
                }
            });
//            if(rImgs[position] != null && rImgs[position].length()>0)
//            {
//                Picasso.get().load(rImgs[position]).placeholder(R.drawable.common_full_open_on_phone).into(images);
//            }else
//            {
//                Picasso.get().load(R.drawable.common_full_open_on_phone).into(images);
//            }



            return row;
        }
    }




}