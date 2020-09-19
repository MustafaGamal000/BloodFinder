package com.example.bloodfinder;

import android.content.Context;

import com.android.volley.RequestQueue;

public class MySingleton {
    private static MySingleton mInstace;
    private RequestQueue mRequestQueue;
    private Context context;

    public MySingleton(Context context) {
        this.context = context;
    }

}
