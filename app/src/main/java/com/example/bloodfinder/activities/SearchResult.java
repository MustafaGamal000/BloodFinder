package com.example.bloodfinder.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.bloodfinder.Adapters.SearchCustomAdapter;
import com.example.bloodfinder.ModelClasses.UserModel;
import com.example.bloodfinder.R;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {
    private RecyclerView users_RV;
    private ArrayList<UserModel> userModelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        userModelsList = new ArrayList<>();
/*
        userModelsList = (ArrayList<UserModel>) getIntent().getSerializableExtra("userList");
*/

        userModelsList.add(new UserModel("1", "Mustafa", "mustafa@yahaoo.com", "011114971078", "A+", "Cairo"));
        userModelsList.add(new UserModel("1", "Mustafa", "mustafa@yahaoo.com", "011114971078", "A+", "Cairo"));
        userModelsList.add(new UserModel("1", "Mustafa", "mustafa@yahaoo.com", "011114971078", "A+", "Cairo"));
        userModelsList.add(new UserModel("1", "Mustafa", "mustafa@yahaoo.com", "011114971078", "A+", "Cairo"));
        userModelsList.add(new UserModel("1", "Mustafa", "mustafa@yahaoo.com", "011114971078", "A+", "Cairo"));

        users_RV = findViewById(R.id.searchResult_RV);
        users_RV.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL, false));
        SearchCustomAdapter adapter = new SearchCustomAdapter(SearchResult.this, userModelsList);
        users_RV.setAdapter(adapter);

    }
}
