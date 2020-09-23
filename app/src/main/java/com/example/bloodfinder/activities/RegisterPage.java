package com.example.bloodfinder.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodfinder.ModelClasses.UserModel;
import com.example.bloodfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class RegisterPage extends AppCompatActivity {
    private SlidrInterface slidrInterface;
    private TextView intoLoginPage_tv;
    private EditText username_et, email_et, phone_et, password_et;
    private Spinner bloodGroup_spinner, cities_spinner;
    private Button register_btn;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        slidrInterface = Slidr.attach(this);
        auth = FirebaseAuth.getInstance();

        username_et = findViewById(R.id.username_et);
        email_et = findViewById(R.id.email_et);
        phone_et = findViewById(R.id.mobile_et);
        password_et = findViewById(R.id.password_et);
        cities_spinner = findViewById(R.id.cities_spinner);
        bloodGroup_spinner = findViewById(R.id.blood_group_spinner);
        register_btn = findViewById(R.id.register_btn);

        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<>(RegisterPage.this, R.layout.custom_bloodgroup_spinner, getResources().getStringArray(R.array.bloodGroupItem));
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(RegisterPage.this, R.layout.custom_cities_spinner, getResources().getStringArray(R.array.cities));

        bloodGroup_spinner.setAdapter(bloodGroupAdapter);
        cities_spinner.setAdapter(citiesAdapter);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });


        intoLoginPage_tv = findViewById(R.id.into_login_tv);
        intoLoginPage_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }
        });
    }

    private void openLoginPage() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        finish();
    }

    private void createUser() {
        final String username = username_et.getText().toString();
        final String email = email_et.getText().toString();
        final String phone = phone_et.getText().toString();
        final String password = password_et.getText().toString();
        final String address = cities_spinner.getSelectedItem().toString();
        final String bloodGroup = bloodGroup_spinner.getSelectedItem().toString();
        if (TextUtils.isEmpty(username)) {
            username_et.setError("username is required");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            email_et.setError("email is required");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            phone_et.setError("phone is required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            password_et.setError("password is required");
            return;
        }
        progressDialog = new ProgressDialog(RegisterPage.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterPage.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    String uid = task.getResult().getUser().getUid();
                    userModel = new UserModel(uid, username, email, phone, bloodGroup, address);
                    saveDataInRealtimeDB(userModel, uid);
                    startActivity(new Intent(RegisterPage.this, LoginPage.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterPage.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveDataInRealtimeDB(UserModel userModel, String uid) {
        reference.child("Users").child(uid).setValue(userModel);
    }

}
