package com.example.bloodfinder.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    private TextView intoRegister;
    private EditText email_et, password_et;
    private Button login_btn;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        email_et = findViewById(R.id.email_login_et);
        password_et = findViewById(R.id.password_login_et);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });


        intoRegister = findViewById(R.id.into_register_txt);
        intoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterPage();
            }
        });
    }

    private void userLogin() {


        ConnectivityManager manager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        assert manager != null;
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (activeNetwork != null) {


            final String Email = email_et.getText().toString().trim();
            String Password = password_et.getText().toString().trim();
            if (TextUtils.isEmpty(Email)) {
                email_et.setError("Please Enter Email");
                return;
            }
            if (TextUtils.isEmpty(Password)) {
                password_et.setError("Please Enter Password");
                return;
            }

            progressDialog = new ProgressDialog(LoginPage.this);
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent
            );

            mAuth.signInWithEmailAndPassword(Email, Password)

                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                startActivity(new Intent(LoginPage.this, HomePage.class));
                                finish();

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginPage.this, "Check your Email or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else {
            progressDialog.dismiss();
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    private void openRegisterPage() {
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }
}
