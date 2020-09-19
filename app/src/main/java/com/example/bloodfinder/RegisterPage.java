package com.example.bloodfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
import com.rengwuxian.materialedittext.MaterialEditText;

public class RegisterPage extends AppCompatActivity {
    private SlidrInterface slidrInterface;
    private TextView intoLoginPage_tv;
    private EditText username_et, email_et, phone_et, password_et,rePassword;
    private Spinner bloodGroup;
    private Button register_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        slidrInterface = Slidr.attach(this);

        username_et=findViewById(R.id.username_et);
        email_et=findViewById(R.id.email_et);
        phone_et=findViewById(R.id.mobile_et);
        password_et=findViewById(R.id.password_et);
        rePassword=findViewById(R.id.re_password_et);
        bloodGroup=findViewById(R.id.blood_group_spinner);
        register_btn=findViewById(R.id.register_btn);


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
    private void registerNewAccount(String username, String email, String phoneNumber, String bloodGroup, String password){

    }
}
