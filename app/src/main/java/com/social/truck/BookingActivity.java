package com.social.truck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.InternalTokenProvider;

public class BookingActivity extends AppCompatActivity {







    long count = -1;
    String uid;

    Spinner type, details;
    String vehicle[] = {"Trailer Empty 20 Feet", "Trailer Empty 40 Feet", " Trailer Loaded 20 Feet", "Trailer Loaded 40 Feet", "Full Body ", " Half Body", "Other"};
    EditText weight, materialtype, otype;
    String w, m, ot, vehicletype;
    DatabaseReference ref;

    String cities [] = {

            "Adilabad",
            "Anantapur",
            "Chittoor",
            "Kakinada",
            "Guntur",
            "Hyderabad",
            "Karimnagar",
            "Khammam",
            "Krishna",
            "Kurnool",
            "Mahbubnagar",
            "Medak",
            "Nalgonda",
            "Nizamabad",
            "Ongole",
            "Hyderabad",
            "Srikakulam",
            "Nellore",
            "Visakhapatnam",
            "Vizianagaram",
            "Warangal",
            "Eluru",
            "Kadapa",
            "Anjaw",
            "Changlang",
            "East Siang",
            "Kurung Kumey",
            "Lohit",
            "Lower Dibang Valley",
            "Lower Subansiri",
            "Papum Pare",
            "Tawang",
            "Tirap",
            "Dibang Valley",
            "Upper Siang",
            "Upper Subansiri",
            "West Kameng",
            "West Siang",
            "Baksa",
            "Barpeta",
            "Bongaigaon",
            "Cachar",
            "Chirang",
            "Darrang",
            "Dhemaji",
            "Dima Hasao",
            "Dhubri",
            "Dibrugarh",
            "Goalpara",
            "Golaghat",
            "Hailakandi",
            "Jorhat",
            "Kamrup",
            "Kamrup Metropolitan",
            "Karbi Anglong",
            "Karimganj",
            "Kokrajhar",
            "Lakhimpur",
            "Marigaon",
            "Nagaon",
            "Nalbari",
            "Sibsagar",
            "Sonitpur",
            "Tinsukia",
            "Udalguri",
            "Araria",
            "Arwal",
            "Aurangabad",
            "Banka",
            "Begusarai",
            "Bhagalpur",
            "Bhojpur",
            "Buxar",
            "Darbhanga",
            "East Champaran",
            "Gaya",
            "Gopalganj",
            "Jamui",
            "Jehanabad",
            "Kaimur",
            "Katihar",
            "Khagaria",
            "Kishanganj",
            "Lakhisarai",
            "Madhepura",
            "Madhubani",
            "Munger",
            "Muzaffarpur",
            "Nalanda",
            "Nawada",
            "Patna",
            "Purnia",
            "Rohtas",
            "Saharsa",
            "Samastipur",
            "Saran",
            "Sheikhpura",
            "Sheohar",
            "Sitamarhi",
            "Siwan",
            "Supaul",
            "Vaishali",
            "West Champaran",
            "Chandigarh",
            "Bastar",
            "Bijapur",
            "Bilaspur",
            "Dantewada",
            "Dhamtari",
            "Durg",
            "Jashpur",
            "Janjgir-Champa",
            "Korba",
            "Koriya",
            "Kanker",
            "Kabirdham (Kawardha)",
            "Mahasamund",
            "Narayanpur",
            "Raigarh",
            "Rajnandgaon",
            "Raipur",
            "Surguja",
            "Dadra and Nagar Haveli",
            "Daman",
            "Diu",
            "Central Delhi",
            "East Delhi",
            "New Delhi",
            "North Delhi",
            "North East Delhi",
            "North West Delhi",
            "South Delhi",
            "South West Delhi",
            "West Delhi",
            "North Goa",
            "South Goa",
            "Ahmedabad",
            "Amreli district",
            "Anand",
            "Banaskantha",
            "Bharuch",
            "Bhavnagar",
            "Dahod",
            "The Dangs",
            "Gandhinagar",
            "Jamnagar",
            "Junagadh",
            "Kutch",
            "Kheda",
            "Mehsana",
            "Narmada",
            "Navsari",
            "Patan",
            "Panchmahal",
            "Porbandar",
            "Rajkot",
            "Sabarkantha",
            "Surendranagar",
            "Surat",
            "Vyara",
            "Vadodara",
            "Valsad",
            "Ambala",
            "Bhiwani",
            "Faridabad",
            "Fatehabad",
            "Gurgaon",
            "Hissar",
            "Jhajjar",
            "Jind",
            "Karnal",
            "Kaithal",
            "Kurukshetra",
            "Mahendragarh",
            "Mewat",
            "Palwal",
            "Panchkula",
            "Panipat",
            "Rewari",
            "Rohtak",
            "Sirsa",
            "Sonipat",
            "Yamuna Nagar",
            "Bilaspur",
            "Chamba",
            "Hamirpur",
            "Kangra",
            "Kinnaur",
            "Kullu",
            "Lahaul and Spiti",
            "Mandi",
            "Shimla",
            "Sirmaur",
            "Solan",
            "Una",
            "Anantnag",
            "Badgam",
            "Bandipora",
            "Baramulla",
            "Doda",
            "Ganderbal",
            "Jammu",
            "Kargil",
            "Kathua",
            "Kishtwar",
            "Kupwara",
            "Kulgam",
            "Leh",
            "Poonch",
            "Pulwama",
            "Rajauri",
            "Ramban",
            "Reasi",
            "Samba",
            "Shopian",
            "Srinagar",
            "Udhampur",
            "Bokaro",
            "Chatra",
            "Deoghar",
            "Dhanbad",
            "Dumka",
            "East Singhbhum",
            "Garhwa",
            "Giridih",
            "Godda",
            "Gumla",
            "Hazaribag",
            "Jamtara",
            "Khunti",
            "Koderma",
            "Latehar",
            "Lohardaga",
            "Pakur",
            "Palamu",
            "Ramgarh",
            "Ranchi",
            "Sahibganj",
            "Seraikela Kharsawan",
            "Simdega",
            "West Singhbhum",
            "Bagalkot",
            "Bangalore Rural",
            "Bangalore Urban",
            "Belgaum",
            "Bellary",
            "Bidar",
            "Bijapur",
            "Chamarajnagar",
            "Chikkamagaluru",
            "Chikkaballapur",
            "Chitradurga",
            "Davanagere",
            "Dharwad",
            "Dakshina Kannada",
            "Gadag",
            "Gulbarga",
            "Hassan",
            "Haveri district",
            "Kodagu",
            "Kolar",
            "Koppal",
            "Mandya",
            "Mysore",
            "Raichur",
            "Shimoga",
            "Tumkur",
            "Udupi",
            "Uttara Kannada",
            "Ramanagara",
            "Yadgir",
            "Alappuzha",
            "Ernakulam",
            "Idukki",
            "Kannur",
            "Kasaragod",
            "Kollam",
            "Kottayam",
            "Kozhikode",
            "Malappuram",
            "Palakkad",
            "Pathanamthitta",
            "Thrissur",
            "Thiruvananthapuram",
            "Wayanad",
            "Alirajpur",
            "Anuppur",
            "Ashok Nagar",
            "Balaghat",
            "Barwani",
            "Betul",
            "Bhind",
            "Bhopal",
            "Burhanpur",
            "Chhatarpur",
            "Chhindwara",
            "Damoh",
            "Datia",
            "Dewas",
            "Dhar",
            "Dindori",
            "Guna",
            "Gwalior",
            "Harda",
            "Hoshangabad",
            "Indore",
            "Jabalpur",
            "Jhabua",
            "Katni",
            "Khandwa (East Nimar)",
            "Khargone (West Nimar)",
            "Mandla",
            "Mandsaur",
            "Morena",
            "Narsinghpur",
            "Neemuch",
            "Panna",
            "Rewa",
            "Rajgarh",
            "Ratlam",
            "Raisen",
            "Sagar",
            "Satna",
            "Sehore",
            "Seoni",
            "Shahdol",
            "Shajapur",
            "Sheopur",
            "Shivpuri",
            "Sidhi",
            "Singrauli",
            "Tikamgarh",
            "Ujjain",
            "Umaria",
            "Vidisha",
            "Ahmednagar",
            "Akola",
            "Amravati",
            "Aurangabad",
            "Bhandara",
            "Beed",
            "Buldhana",
            "Chandrapur",
            "Dhule",
            "Gadchiroli",
            "Gondia",
            "Hingoli",
            "Jalgaon",
            "Jalna",
            "Kolhapur",
            "Latur",
            "Mumbai City",
            "Mumbai suburban",
            "Nandurbar",
            "Nanded",
            "Nagpur",
            "Nashik",
            "Osmanabad",
            "Parbhani",
            "Pune",
            "Raigad",
            "Ratnagiri",
            "Sindhudurg",
            "Sangli",
            "Solapur",
            "Satara",
            "Thane",
            "Wardha",
            "Washim",
            "Yavatmal",
            "Bishnupur",
            "Churachandpur",
            "Chandel",
            "Imphal East",
            "Senapati",
            "Tamenglong",
            "Thoubal",
            "Ukhrul",
            "Imphal West",
            "East Garo Hills",
            "East Khasi Hills",
            "Jaintia Hills",
            "Ri Bhoi",
            "South Garo Hills",
            "West Garo Hills",
            "West Khasi Hills",
            "Aizawl",
            "Champhai",
            "Kolasib",
            "Lawngtlai",
            "Lunglei",
            "Mamit",
            "Saiha",
            "Serchhip",
            "Dimapur",
            "Kohima",
            "Mokokchung",
            "Mon",
            "Phek",
            "Tuensang",
            "Wokha",
            "Zunheboto",
            "Angul",
            "Boudh (Bauda)",
            "Bhadrak",
            "Balangir",
            "Bargarh (Baragarh)",
            "Balasore",
            "Cuttack",
            "Debagarh (Deogarh)",
            "Dhenkanal",
            "Ganjam",
            "Gajapati",
            "Jharsuguda",
            "Jajpur",
            "Jagatsinghpur",
            "Khordha",
            "Kendujhar (Keonjhar)",
            "Kalahandi",
            "Kandhamal",
            "Koraput",
            "Kendrapara",
            "Malkangiri",
            "Mayurbhanj",
            "Nabarangpur",
            "Nuapada",
            "Nayagarh",
            "Puri",
            "Rayagada",
            "Sambalpur",
            "Subarnapur (Sonepur)",
            "Sundergarh",
            "Karaikal",
            "Mahe",
            "Pondicherry",
            "Yanam",
            "Amritsar",
            "Barnala",
            "Bathinda",
            "Firozpur",
            "Faridkot",
            "Fatehgarh Sahib",
            "Fazilka",
            "Gurdaspur",
            "Hoshiarpur",
            "Jalandhar",
            "Kapurthala",
            "Ludhiana",
            "Mansa",
            "Moga",
            "Sri Muktsar Sahib",
            "Pathankot",
            "Patiala",
            "Rupnagar",
            "Ajitgarh (Mohali)",
            "Sangrur",
            "Nawanshahr",
            "Tarn Taran",
            "Ajmer",
            "Alwar",
            "Bikaner",
            "Barmer",
            "Banswara",
            "Bharatpur",
            "Baran",
            "Bundi",
            "Bhilwara",
            "Churu",
            "Chittorgarh",
            "Dausa",
            "Dholpur",
            "Dungapur",
            "Ganganagar",
            "Hanumangarh",
            "Jhunjhunu",
            "Jalore",
            "Jodhpur",
            "Jaipur",
            "Jaisalmer",
            "Jhalawar",
            "Karauli",
            "Kota",
            "Nagaur",
            "Pali",
            "Pratapgarh",
            "Rajsamand",
            "Sikar",
            "Sawai Madhopur",
            "Sirohi",
            "Tonk",
            "Udaipur",
            "East Sikkim",
            "North Sikkim",
            "South Sikkim",
            "West Sikkim",
            "Ariyalur",
            "Chennai",
            "Coimbatore",
            "Cuddalore",
            "Dharmapuri",
            "Dindigul",
            "Erode",
            "Kanchipuram",
            "Kanyakumari",
            "Karur",
            "Madurai",
            "Nagapattinam",
            "Nilgiris",
            "Namakkal",
            "Perambalur",
            "Pudukkottai",
            "Ramanathapuram",
            "Salem",
            "Sivaganga",
            "Tirupur",
            "Tiruchirappalli",
            "Theni",
            "Tirunelveli",
            "Thanjavur",
            "Thoothukudi",
            "Tiruvallur",
            "Tiruvarur",
            "Tiruvannamalai",
            "Vellore",
            "Viluppuram",
            "Virudhunagar",
            "Dhalai",
            "North Tripura",
            "South Tripura",
            "Khowai",
            "West Tripura",
            "Agra",
            "Allahabad",
            "Aligarh",
            "Ambedkar Nagar",
            "Auraiya",
            "Azamgarh",
            "Barabanki",
            "Budaun",
            "Bagpat",
            "Bahraich",
            "Bijnor",
            "Ballia",
            "Banda",
            "Balrampur",
            "Bareilly",
            "Basti",
            "Bulandshahr",
            "Chandauli",
            "Chhatrapati Shahuji Maharaj Nagar",
            "Chitrakoot",
            "Deoria",
            "Etah",
            "Kanshi Ram Nagar",
            "Etawah",
            "Firozabad",
            "Farrukhabad",
            "Fatehpur",
            "Faizabad",
            "Gautam Buddh Nagar",
            "Gonda",
            "Ghazipur",
            "Gorakhpur",
            "Ghaziabad",
            "Hamirpur",
            "Hardoi",
            "Mahamaya Nagar",
            "Jhansi",
            "Jalaun",
            "Jyotiba Phule Nagar",
            "Jaunpur district",
            "Ramabai Nagar (Kanpur Dehat)",
            "Kannauj",
            "Kanpur",
            "Kaushambi",
            "Kushinagar",
            "Lalitpur",
            "Lakhimpur Kheri",
            "Lucknow",
            "Mau",
            "Meerut",
            "Maharajganj",
            "Mahoba",
            "Mirzapur",
            "Moradabad",
            "Mainpuri",
            "Mathura",
            "Muzaffarnagar",
            "Panchsheel Nagar district (Hapur)",
            "Pilibhit",
            "Shamli",
            "Pratapgarh",
            "Rampur",
            "Raebareli",
            "Saharanpur",
            "Sitapur",
            "Shahjahanpur",
            "Sant Kabir Nagar",
            "Siddharthnagar",
            "Sonbhadra",
            "Sant Ravidas Nagar",
            "Sultanpur",
            "Shravasti",
            "Unnao",
            "Varanasi",
            "Almora",
            "Bageshwar",
            "Chamoli",
            "Champawat",
            "Dehradun",
            "Haridwar",
            "Nainital",
            "Pauri Garhwal",
            "Pithoragarh",
            "Rudraprayag",
            "Tehri Garhwal",
            "Udham Singh Nagar",
            "Uttarkashi",
            "Birbhum",
            "Bankura",
            "Bardhaman",
            "Darjeeling",
            "Dakshin Dinajpur",
            "Hooghly",
            "Howrah",
            "Jalpaiguri",
            "Cooch Behar",
            "Kolkata",
            "Maldah",
            "Paschim Medinipur",
            "Purba Medinipur",
            "Murshidabad",
            "Nadia",
            "North 24 Parganas",
            "South 24 Parganas",
            "Purulia",
            "Uttar Dinajpur",

    };
    @Override
    public void onBackPressed() {
        overridePendingTransition(0,0);
        super.onBackPressed();
    }
    AutoCompleteTextView fromlocation , tolocation;
    String  from , to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_booking);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationmenu);
        bottomNavigationView.setSelectedItemId(R.id.Booking);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent intent;
                switch (item.getItemId()){
                    case R.id.Booking:
                        return  true;
                    case R.id.provide:
                         intent = new Intent(getApplicationContext(),ProviderActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;
                    case R.id.ongoing:
                         intent = new Intent(getApplicationContext(),ActivityActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;
                    case  R.id.completed:
                         intent = new Intent(getApplicationContext(),CompletedActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;


                }

                return false;
            }
        });
        final String uid = FirebaseAuth.getInstance().getUid();

        ref = FirebaseDatabase.getInstance().getReference("Booking").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count = snapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        fromlocation = (AutoCompleteTextView) findViewById(R.id.fromlocation);
        tolocation = (AutoCompleteTextView) findViewById(R.id.toloaction);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cities);
        fromlocation.setAdapter(arrayAdapter);
        tolocation.setAdapter(arrayAdapter);
        from = fromlocation.getText().toString();
        to = tolocation.getText().toString();
        type = (Spinner) findViewById(R.id.vehicletype);
        otype = (EditText) findViewById(R.id.type);


        type.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, vehicle));
        weight = (EditText) findViewById(R.id.Weight);
        materialtype = (EditText) findViewById(R.id.materialtype);
        w = "";
        m = "";



        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weight.setVisibility(View.VISIBLE);
                materialtype.setVisibility(View.VISIBLE);
                otype.setVisibility(View.INVISIBLE);
                vehicletype = vehicle[position];
                if (position == 0 || position == 1) {
                    weight.setVisibility(View.INVISIBLE);
                    materialtype.setVisibility(View.INVISIBLE);
                } else {

                    w = weight.getText().toString();
                    m = materialtype.getText().toString();

                }
                if (position == 6) {
                    otype.setVisibility(View.VISIBLE);
                    ot = otype.getText().toString();

                }

                Log.i("INFO", "" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button button = (Button) findViewById(R.id.request);
        {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(count>-1)
                    {

                        w = weight.getText().toString();
                        m = materialtype.getText().toString();
                        if (vehicletype.equals("Other"))
                            vehicletype = otype.getText().toString();
                        from = fromlocation.getText().toString();
                        to = tolocation.getText().toString();

                        int count1=0,q=1;
                        for(int j=0;j<cities.length;j++){
                            if(cities[j].equalsIgnoreCase(from))
                                count1++;
                        }
                        if(count1==0)
                        {
                            q=0;
                            fromlocation.setError("Enter Valid City Name");
                            fromlocation.requestFocus();
                        }
                        count1=0;
                        for(int j=0;j<cities.length;j++){
                            if(cities[j].equalsIgnoreCase(to))
                                count1++;
                        }
                        if(count1==0)
                        {
                            q=0;
                            tolocation.setError("Enter Valid City Name");
                            tolocation.requestFocus();
                        }



                        if(q==1)
                        {
                            DatabaseReference  ref = FirebaseDatabase.getInstance().getReference("Booking").child(uid);

                            ref.child(count+"").child("Vehicle").setValue(vehicletype);
                            ref.child(count+"").child("Weight").setValue(w);
                            ref.child(count+"").child("MaterialType").setValue(m);
                            ref.child(count+"").child("User").setValue(uid);

                            ref.child(count+"").child("Status").setValue("0");


                            ref.child(count+"").child("Location").setValue(from+" TO "+to);

                            ref.child(count+"").child("Number").setValue(count+"");


                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(uid);
                            databaseReference.child("Requests").child(""+count).child("Status").setValue("0");
                            databaseReference.child("Requests").child(count+"").child("Vehicle").setValue(vehicletype);
                            databaseReference.child("Requests").child(count+"").child("Weight").setValue(w);
                            databaseReference.child("Requests").child(count+"").child("MaterialType").setValue(m);
                            databaseReference.child("Requests").child(count+"").child("Location").setValue(from+" TO "+to);

                            count++;


                            Intent intent = new Intent(getApplicationContext(),AnimationActivity.class);
                            startActivity(intent);
                        }







                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Loading",1).show();
                    }
                }

            });

        }

        Log.i("info","Booking Activity");
    }
}