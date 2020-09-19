package com.example.bloodfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class RegisterPage extends AppCompatActivity {
    private SlidrInterface slidrInterface;
private TextView intoLoginPage_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        slidrInterface =Slidr.attach(this);

        intoLoginPage_tv=findViewById(R.id.into_login_tv);
        intoLoginPage_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }
        });
    }

    private void openLoginPage() {
        Intent intent =new Intent(this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}
