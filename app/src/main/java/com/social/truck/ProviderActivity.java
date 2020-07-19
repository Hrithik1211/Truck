package com.social.truck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_provider);
        ActionBar actionBar = getSupportActionBar();
         frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        actionBar.hide();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationmenu);
        bottomNavigationView.setSelectedItemId(R.id.provide);
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
                        return true;
                    case R.id.ongoing:
                         intent = new Intent(getApplicationContext(),ActivityActivity.class);
                         overridePendingTransition(0,0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;
                    case  R.id.completed:
                         intent = new Intent(getApplicationContext(),CompletedActivity.class);
                         overridePendingTransition(0,0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;


                }

                return false;
            }
        });
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Booking");
        Query query = rootRef.orderByChild("Booking");
        uid = FirebaseAuth.getInstance().getUid().toString();
        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<String> vehicle = new ArrayList<>();
                final ArrayList<String> materialtype = new ArrayList<>();
                final ArrayList<String> weight = new ArrayList<>();
                final ArrayList<String> count = new ArrayList<>();
                final ArrayList<String> strings = new ArrayList<>();
                final ArrayList<String> location = new ArrayList<>();


                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String string = ds.getKey();
                    if(string.equals(uid));
                    else{
                        strings.add(string);
                        for(DataSnapshot dataSnapshot1 : ds.getChildren()){
                            if(dataSnapshot1.child("Status").getValue(String.class).equals("0")){
                                vehicle.add(dataSnapshot1.child("Vehicle").getValue(String.class));
                                materialtype.add(dataSnapshot1.child("MaterialType").getValue(String.class));
                                weight.add(dataSnapshot1.child("Weight").getValue(String.class));
                                count.add(dataSnapshot1.child("Number").getValue(String.class));
                                location.add(dataSnapshot1.child("Location").getValue(String.class));

                        }
                        }

                    }
                }
                listView = (ListView) findViewById(R.id.providerlist);
                Adapter arrayAdapter = new Adapter(getApplicationContext(),vehicle,materialtype,weight,location);
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Fragment favorites_fragment = new MoneyBid(vehicle.get(position),materialtype.get(position),weight.get(position),"Location",count.get(position),strings.get(position));
                        listView.setVisibility(View.INVISIBLE);




                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,favorites_fragment).addToBackStack("list1").commit();





                    }
                });










            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("INFO", databaseError.getMessage()); //Don't ignore errors!
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);
    }
    FrameLayout frameLayout ;
    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();

            //additional code
        } else {
            listView.setVisibility(View.VISIBLE);

            frameLayout.removeAllViews();
        }

    }


    ListView listView;
    String uid;


    class Adapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> rvehicle ;
        ArrayList<String> rmaterialtype ;
        ArrayList<String> rweight ;
        ArrayList<String> rlocation;



        Adapter (Context c, ArrayList<String> vehicle,ArrayList<String> materialtype,ArrayList<String> weight, ArrayList<String> location){
            super(c,R.layout.list,vehicle);
            this.context=c;
            this.rvehicle=vehicle;
            this.rmaterialtype = materialtype;
            this.rweight = weight;
            this.rlocation = location;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list,parent,false);
            ImageView images = row.findViewById(R.id.searchimage);
            TextView pv = row.findViewById(R.id.providervehicle);
            TextView pm = row.findViewById(R.id.providermaterialtype);
            TextView pw = row.findViewById(R.id.providerweight);
            TextView pl = row.findViewById(R.id.location);

            images.setImageResource(R.drawable.image);
            pv.setText(rvehicle.get(position));
            pm.setText(rmaterialtype.get(position));
            pw.setText(rweight.get(position));
            pl.setText(rlocation.get(position));
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