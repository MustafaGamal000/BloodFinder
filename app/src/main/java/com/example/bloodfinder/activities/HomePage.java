package com.example.bloodfinder.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

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

    private Spinner bloodGroup_spinner, address_spinner;
    private Button search, logout;
    private DatabaseReference reference;
    private ArrayList<UserModel> userModelsList;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        userModelsList = new ArrayList<>();
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
                searchUsers(bloodGroup, address);
            }
        });

    }

    private void intoLoginPage() {
        startActivity(new Intent(HomePage.this, LoginPage.class));
        finish();
    }

    private void searchUsers(String bloodGroup, final String address) {
        progressDialog = new ProgressDialog(HomePage.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("bloodGroup")
                .startAt(bloodGroup).endAt(bloodGroup + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userModelsList.clear();
                ArrayList<UserModel> newUserList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserModel userModel = dataSnapshot.getValue(UserModel.class);
                    userModelsList.add(userModel);
                }
                if (userModelsList.isEmpty()) {
                    progressDialog.dismiss();
                    Toast.makeText(HomePage.this, "There is no Users have the same blood group", Toast.LENGTH_SHORT).show();
                } else {
                    for (UserModel model : userModelsList) {
                        if (model.getAddress().equals(address)) {
                            newUserList.add(model);
                        }
                    }

                    if (newUserList.isEmpty()) {
                        progressDialog.dismiss();
                        Toast.makeText(HomePage.this, "There is no Users have the same blood group in your address", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Intent intent = new Intent(HomePage.this, SearchResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userList", newUserList);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
