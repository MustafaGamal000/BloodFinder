package com.example.bloodfinder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bloodfinder.ModelClasses.UserModel;
import com.example.bloodfinder.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private SlidrInterface slidrInterface;
    private Spinner bloodGroup_spinner, address_spinner;
    private Button search, logout;
    private DatabaseReference reference;
    private ArrayList<UserModel> userModelsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        slidrInterface = Slidr.attach(this);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        bloodGroup_spinner = findViewById(R.id.blood_group_spinnerHomePage);
        address_spinner = findViewById(R.id.cities_spinnerHomePage);
        search = findViewById(R.id.search_btn_homePage);

        logout = findViewById(R.id.logout_btn_HomePage);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intoLoginPage();
            }
        });


        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<>(HomePage.this, R.layout.custom_bloodgroup_spinner, getResources().getStringArray(R.array.bloodGroupItem));
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(HomePage.this, R.layout.custom_cities_spinner, getResources().getStringArray(R.array.cities));

        bloodGroup_spinner.setAdapter(bloodGroupAdapter);
        address_spinner.setAdapter(citiesAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bloodGroup = bloodGroup_spinner.getSelectedItem().toString();
                String address = address_spinner.getSelectedItem().toString();
            }
        });

    }

    private void intoLoginPage() {
        startActivity(new Intent(HomePage.this, LoginPage.class));
        finish();
    }
}
