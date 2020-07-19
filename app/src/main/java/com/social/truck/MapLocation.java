package com.social.truck;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class MapLocation extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.maplocation,container,false);

    }


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
    AutoCompleteTextView fromlocation , tolocation;
    String  from , to;
    SupportMapFragment supportMapFragment;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
   Places.initialize(getContext(), "AIzaSyCOjQp-gFJyxEAhxKbYV1L7tL5Sxpzgq-c");
        fromlocation = (AutoCompleteTextView) view.findViewById(R.id.fromlocation);
        tolocation = (AutoCompleteTextView) view.findViewById(R.id.toloaction);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,cities);
        fromlocation.setAdapter(arrayAdapter);
        tolocation.setAdapter(arrayAdapter);
        from = fromlocation.getText().toString();
        to = tolocation.getText().toString();


//        fromlocation.setFocusable(false);
//        fromlocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,
//                        Place.Field.LAT_LNG, Place.Field.NAME);
//                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(getContext());
//                startActivityForResult(intent, 100);
//            }
//        });
//        tolocation.setFocusable(false);
//
//        tolocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,
//                        Place.Field.LAT_LNG, Place.Field.NAME);
//                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(getContext());
//                startActivityForResult(intent, 100);
//            }
//        });

        Button request = (Button) view.findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                {String uid = FirebaseAuth.getInstance().getUid();
                DatabaseReference  ref = FirebaseDatabase.getInstance().getReference("Booking").child(uid);

                ref.child(count+"").child("Vehicle").setValue(vehicle);
                ref.child(count+"").child("Weight").setValue(w);
                ref.child(count+"").child("MaterialType").setValue(m);
                ref.child(count+"").child("User").setValue(uid);

                    ref.child(count+"").child("Status").setValue("0");


                    ref.child(count+"").child("Location").setValue(from+" TO "+to);

                ref.child(count+"").child("Number").setValue(count+"");


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(uid);
                databaseReference.child("Requests").child(""+count).child("Status").setValue("0");
                databaseReference.child("Requests").child(count+"").child("Vehicle").setValue(vehicle);
                databaseReference.child("Requests").child(count+"").child("Weight").setValue(w);
                databaseReference.child("Requests").child(count+"").child("MaterialType").setValue(m);
                databaseReference.child("Requests").child(count+"").child("Location").setValue(from+" TO "+to);

                count++;

                    AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
                 //   appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Animation()).commit();



                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == -1) {
            Place place = Autocomplete.getPlaceFromIntent(data);
            fromlocation.setText(place.getAddress());

        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getContext(), status.getStatusMessage(), 1).show();
        }
    }

    String vehicle,w,m;
    long  count;

    public MapLocation(String vehicle, String w, String m ,long count) {
        this.vehicle = vehicle;
        this.w = w;
        this.m = m;
        this.count = count;
    }

    public MapLocation(int contentLayoutId, String vehicle, String w, String m ,long count) {
        super(contentLayoutId);
        this.vehicle = vehicle;
        this.w = w;
        this.m = m;
        this.count=count;
    }
}
