package com.example.bloodfinder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodfinder.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {
    private SlidrInterface slidrInterface;
    private TextView intoLoginPage_tv;
    private EditText username_et, email_et, phone_et, password_et, rePassword;
    private Spinner bloodGroup;
    private Button register_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        slidrInterface = Slidr.attach(this);

        username_et = findViewById(R.id.username_et);
        email_et = findViewById(R.id.email_et);
        phone_et = findViewById(R.id.mobile_et);
        password_et = findViewById(R.id.password_et);
        rePassword = findViewById(R.id.re_password_et);
        bloodGroup = findViewById(R.id.blood_group_spinner);
        register_btn = findViewById(R.id.register_btn);


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

    private void registerNewAccount(final String username, final String email, final String phoneNumber, final String bloodGroup, final String password) {
        final ProgressDialog progressDialog = new ProgressDialog(RegisterPage.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Registering New Account...!");
        progressDialog.show();
        String url = "http://10.0.2.2/loginregister/register.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully Registered")) ;
                progressDialog.dismiss();
                Toast.makeText(RegisterPage.this, "New User Is Registered DCccessfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(RegisterPage.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("username", username);
                param.put("email", email);
                param.put("password", password);
                param.put("phoneNumber", phoneNumber);
                param.put("bloodGroup", bloodGroup);
                return param;

            }
        };
    }
}
