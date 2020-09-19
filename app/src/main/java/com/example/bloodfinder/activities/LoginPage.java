package com.example.bloodfinder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bloodfinder.R;

public class LoginPage extends AppCompatActivity {
private TextView intoRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
