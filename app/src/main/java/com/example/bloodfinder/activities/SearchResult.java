package com.example.bloodfinder.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bloodfinder.Adapters.SearchCustomAdapter;
import com.example.bloodfinder.ModelClasses.UserModel;
import com.example.bloodfinder.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity implements SearchCustomAdapter.Holder.onNoteListener {
    private SlidrInterface slidrInterface;
    private RecyclerView users_RV;
    private ArrayList<UserModel> userModelsList;
    private int itemPosition;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        slidrInterface = Slidr.attach(this);
        userModelsList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        userModelsList = (ArrayList<UserModel>) bundle.getSerializable("userList");

        users_RV = findViewById(R.id.searchResult_RV);
        users_RV.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL, false));
        SearchCustomAdapter adapter = new SearchCustomAdapter(SearchResult.this, userModelsList, this);
        users_RV.setAdapter(adapter);

    }

    @Override
    public void onNoteClick(int position) {
        itemPosition=position;
        makePhoneCall(position);
    }

    private void makePhoneCall(int position) {
        String phoneNumber = userModelsList.get(position).getPhone();
        if (ContextCompat.checkSelfPermission(SearchResult.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SearchResult.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + phoneNumber;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if ((requestCode == REQUEST_CALL) && (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            makePhoneCall(itemPosition);
        }
        else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }
}
