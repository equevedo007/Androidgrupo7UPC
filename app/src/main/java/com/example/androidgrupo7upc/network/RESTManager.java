package com.example.androidgrupo7upc.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RESTManager {

    private static RESTManager instance = null;
    private static RequestQueue requestQueue;
    private static Context context;
    private RESTManager(Context context) {
        RESTManager.context = context;
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized RESTManager getInstance(Context context) {
        instance = new RESTManager(context);
        return instance;
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static Context getContext() {
        return context;
    }

    public interface RESTListener<T> {
        void onResult(T Object);
    }
}