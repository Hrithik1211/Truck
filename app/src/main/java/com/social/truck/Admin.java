package com.social.truck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    int q=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("AdminData");
        Query query = rootRef.orderByChild("AdminData");
        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                final ArrayList<String> vehicle = new ArrayList<>();
                final ArrayList<String> customer = new ArrayList<>();
                final ArrayList<String> provider= new ArrayList<>();
                final ArrayList<String> money = new ArrayList<>();
                final ArrayList<String> weight = new ArrayList<>();
                final ArrayList<String> material = new ArrayList<>();
                final ArrayList<String> location = new ArrayList<>();
                final ArrayList<String> uid = new ArrayList<>();
                final ArrayList<String> count = new ArrayList<>();



                for(final DataSnapshot ds : dataSnapshot.getChildren()) {

                    Log.i("info",ds.getKey());

                    q=0;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(ds.child("Customer").getValue(String.class)).child("Requests").child(ds.child("count").getValue(String.class)).child("Status");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.getValue(String.class).equals("0"))
                            {
                                vehicle.add(ds.child("Vehicle").getValue(String.class));
                                customer.add("Customer: "+ds.child("CustomerName").getValue(String.class)+" "+ds.child("CustomerMobile").getValue(String.class));
                                provider.add("Provider: "+ds.child("ProviderName").getValue(String.class)+" "+ds.child("ProviderMobile").getValue(String.class));

                                money.add("₹ "+ ds.child("Money").getValue(String.class));

                                weight.add(ds.child("Weight").getValue(String.class)+"  Ton");

                                material.add(ds.child("Material").getValue(String.class));

                                location.add(ds.child("Location").getValue(String.class));
                                uid.add(ds.child("Customer").getValue(String.class));
                                count.add(ds.child("count").getValue(String.class));
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        final ListView listView = (ListView) findViewById(R.id.providerlist);
                        Adapter arrayAdapter = new Adapter(getApplicationContext(),vehicle,customer,provider,money,weight,material,location,uid,count);
                        listView.setAdapter(arrayAdapter);



                    }
                },2500);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("INFO", databaseError.getMessage()); //Don't ignore errors!
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);

















































    }








String customeruid,customercount;




    class Adapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> rvehicle ;
        ArrayList<String> rcustomer ;
        ArrayList<String> rprovider;
        ArrayList<String> rmoney;
        ArrayList<String> rweight ;
        ArrayList<String> rmaterial ;
        ArrayList<String> rlocation ;
        ArrayList<String> ruid ;
        ArrayList<String> rcount ;


        Adapter (Context c, ArrayList<String> vehicle,ArrayList<String> customer,ArrayList<String> provider, ArrayList<String> money , ArrayList<String> weight,ArrayList<String> material,ArrayList<String> location,ArrayList<String> uid,ArrayList<String> count){
            super(c,R.layout.list1,vehicle);
            this.context=c;
            this.rvehicle=vehicle;
            this.rcustomer = customer;
            this.rprovider = provider;
            this.rmoney = money;
            this.rweight = weight;
            this.rmaterial = material;
            this.rlocation = location;
            this.ruid = uid;
            this.rcount=count;

        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list1,parent,false);

            TextView vehicle = row.findViewById(R.id.adminvehicle);
            TextView customer = row.findViewById(R.id.customer);
            TextView provider = row.findViewById(R.id.provider);
            TextView money = row.findViewById(R.id.money);
            TextView weight = row.findViewById(R.id.weight);
            TextView material= row.findViewById(R.id.material);
            TextView location = row.findViewById(R.id.location);

            final Button button  = (Button) row.findViewById(R.id.admin);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(ruid.get(position)).child("Requests").child(rcount.get(position)).child("Status");


                    databaseReference.setValue("1");
                    button.setText("Done");

                }
            });

            vehicle.setText(rvehicle.get(position));

            customer.setText(rcustomer.get(position));

            provider.setText(rprovider.get(position));

            money.setText(rmoney.get(position));

            weight.setText(rweight.get(position));

            material.setText(rmaterial.get(position));

            location.setText(rlocation.get(position));


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
