package com.example.bloodfinder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bloodfinder.R;

public class LoginPage extends AppCompatActivity {
private TextView intoRegister;
private EditText email_et, password_et;
private Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_et=findViewById(R.id.email_login_et);
        password_et=findViewById(R.id.password_login_et);
        login_btn=findViewById(R.id.login_btn);


        intoRegister=findViewById(R.id.into_register_txt);
        intoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterPage();
            }
        });
    }

    private void openRegisterPage() {
        Intent intent =new Intent(this, RegisterPage.class);
        startActivity(intent);
    }
}
