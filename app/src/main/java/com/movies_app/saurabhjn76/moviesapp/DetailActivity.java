package com.movies_app.saurabhjn76.moviesapp;


import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by saurabh on 6/5/16.
 */
public class DetailActivity extends AppCompatActivity {

    RequestQueue ReqQueue;
   // public String key ="Insert Your Key Here";
    public String key = "7c8618ff3d5fd73e6601c1d5e1ef3f33"; // Wrong Key
    public TrailerAdapter trailerAdapter;
    public ReviewsAdapter reviewsAdapter;
    public LinearLayout trailersList;
    public LinearLayout reviewList;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailactivity);

        FragmentManager manager = getSupportFragmentManager();
        getSupportActionBar().setHomeButtonEnabled(true);
        Fragment frag =new DetailFragment();

        manager.beginTransaction().replace(R.id.detailContainer,frag).commit();



    }



}
