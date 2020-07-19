package com.social.truck;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityClick extends Fragment {







    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("info","activityclick");
        return inflater.inflate(R.layout.click,container,false);

    }


    String uid ,count;
     ArrayList<String> vehicle = new ArrayList<>();
     ArrayList<String> materialtype = new ArrayList<>();
     ArrayList<String> weight = new ArrayList<>();
    ArrayList<String> mobile = new ArrayList<>();
    ArrayList<String> userid = new ArrayList<>();
    ArrayList<String>  amount= new ArrayList<>();



    public ActivityClick(String uid, String count, String vechicletype, String mat, String mass, String loc) {
        this.uid = uid;
        this.count = count;
        this.vechicletype = vechicletype;
        this.mat = mat;
        this.mass = mass;
        this.loc = loc;
    }

    public ActivityClick(int contentLayoutId, String uid, String count, String vechicletype, String mat, String mass, String loc) {
        super(contentLayoutId);
        this.uid = uid;
        this.count = count;
        this.vechicletype = vechicletype;
        this.mat = mat;
        this.mass = mass;
        this.loc = loc;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("info",vechicletype);
        uid = FirebaseAuth.getInstance().getUid().toString();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Customer").child(uid).child("Requests").child(count);
        Query query = rootRef.orderByChild(count);
        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {


                   if(ds.getKey().equals("Status")||ds.getKey().equals("MaterialType")||ds.getKey().equals("Weight")||ds.getKey().equals("Vehicle")||ds.getKey().equals("Location"));
                   else
                   {
                       String string = ds.getKey();
                       userid.add(string);
                       weight.add(ds.getValue(String.class));
                       Log.i("info",ds.getValue(String.class));
                       DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(string);
                       Query q = databaseReference.orderByChild(string);
                       final ValueEventListener valueEventListener1 = new ValueEventListener() {
                           @Override
                           public void onDataChange(DataSnapshot dataSnapshot1) {
                               for(DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                   if(dataSnapshot2.getKey().equals("Name")){
                                       vehicle.add(dataSnapshot2.getValue(String.class));
                                       Log.i("info","HELLO");
                                   }

                                   if(dataSnapshot2.getKey().equals("OrganisationName"))
                                       materialtype.add(dataSnapshot2.getValue(String.class));
                                   if(dataSnapshot2.getKey().equals("Mobile"))
                                       mobile.add(dataSnapshot2.getValue(String.class));

                               }

                               Log.i("info",vehicle.size()+" "+materialtype.size()+" "+weight.size());
                               final ListView listView = (ListView) view.findViewById(R.id.clicklist);
                               Adapter arrayAdapter = new Adapter(getContext(),vehicle,materialtype,weight,mobile);
                               view.findViewById(R.id.clickloadingPanel).setVisibility(View.GONE);
                               listView.setAdapter(arrayAdapter);
                               listView.setClickable(true);
                               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                   @Override
                                   public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                       final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AdminData").child(uid+userid.get(position)+count);
                                       databaseReference.child("ProviderName").setValue(vehicle.get(position));
                                       databaseReference.child("ProviderMobile").setValue(mobile.get(position));
                                       databaseReference.child("Money").setValue(weight.get(position));
                                       databaseReference.child("Vehicle").setValue(vechicletype);
                                       databaseReference.child("Material").setValue(mat);
                                       databaseReference.child("Weight").setValue(mass);
                                       databaseReference.child("Location").setValue(loc);
                                       databaseReference.child("Provider").setValue(userid.get(position));
                                       databaseReference.child("Customer").setValue(uid);
                                       databaseReference.child("count").setValue(count);



                                       databaseReference.child("Status").setValue("0");
                                       DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Customer").child(uid);
                                       databaseReference1.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                                               datastring = snapshot.child("Name").getValue(String.class);
                                               databaseReference.child("CustomerName").setValue(datastring);
                                               Log.i("info",datastring);
                                               datastring = snapshot.child("Mobile").getValue(String.class);
                                               databaseReference.child("CustomerMobile").setValue(datastring);
                                               Log.i("info",datastring);



                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError error) {

                                           }
                                       });

                                       AppCompatActivity appCompatActivity = (AppCompatActivity) getContext();
                                       appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Bid()).addToBackStack("list").commit();





                                   }
                               });

                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }


                       };
                       q.addListenerForSingleValueEvent(valueEventListener1);


    }



                    }

                view.findViewById(R.id.clickloadingPanel).setVisibility(View.GONE);





            }





            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        query.addListenerForSingleValueEvent(valueEventListener);


        }
String vechicletype,mat,mass,loc,datastring;


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
