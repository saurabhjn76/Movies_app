package com.movies_app.saurabhjn76.moviesapp;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
    public String key = "7c8618ff3d5fd73e6601c1d5e1ef3f33";
    public TrailerAdapter trailerAdapter;
    public ReviewsAdapter reviewsAdapter;
    public LinearLayout trailersList;
    public LinearLayout reviewList;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailactivity);

        trailersList = (LinearLayout) findViewById(R.id.trailerList);
        reviewList=(LinearLayout) findViewById(R.id.reviewsList);
        trailerAdapter = new TrailerAdapter(getApplicationContext());
        reviewsAdapter = new ReviewsAdapter(getApplicationContext());
        ReqQueue= Volley.newRequestQueue(getApplicationContext());;
       final Movies movies = getIntent().getParcelableExtra(Intent.EXTRA_SUBJECT);
        getTrailers(movies.id);
        getReviews(movies.id);
      TextView headder=  ((TextView) findViewById(R.id.textView_movietitle));
        headder.setText(movies.name);
        Picasso.with(this).load(movies.poster_url).
                placeholder(R.mipmap.ic_launcher).into((ImageView)findViewById(R.id.imageView));
        ((TextView) findViewById(R.id.textView_plotSynopsis)).setText(movies.synopsis);
        ((RatingBar) findViewById(R.id.ratingBar)).setRating(movies.rating / 2f);
        ((TextView) findViewById(R.id.Rating_text)).setText((float) Math.round(movies.rating*10d)/10d + "/10");

        SimpleDateFormat df = new SimpleDateFormat("dd MMM, yyyy");
        SimpleDateFormat dfInput = new SimpleDateFormat("yyyy-MM-dd");
        String releasedDate;
        try {
            releasedDate = df.format(dfInput.parse(movies.released_date));
        } catch (ParseException e){
            e.printStackTrace();
            releasedDate = movies.released_date;
        }
        ((TextView) findViewById(R.id.textView_release_date)).setText(releasedDate);
        headder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver = getApplicationContext().getContentResolver();
                MoviesDB mdb = new MoviesDB();
                String message;
                if (mdb.isMovieFavorited(contentResolver, movies.id)){
                    message = "Removed from Favorites";
                    mdb.removeMovie(contentResolver, movies.id);
                    Toast.makeText(getApplicationContext(),"Removed from favourites",Toast.LENGTH_LONG);
                  //  fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), android.R.drawable.btn_star_big_off));
                } else {
                    mdb.addMovie(contentResolver, movies);
                    message = "Added to favorites";
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG);

                }
                //(MainFragment.instance).updateFavoritesGrid(); // till I start using a Loader, this one should suffice
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }

        });



    }
    public void getTrailers(int id){
        String url = "http://api.themoviedb.org/3/movie/" + id + "/videos?api_key=" + key;

        JsonObjectRequest req = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray items = response.getJSONArray("results");
                            JSONObject trailerObj;
                            for (int i=0; i<items.length(); i++){
                                trailerObj = items.getJSONObject(i);
                                Trailer trailer = new Trailer(trailerObj.getString("id"),trailerObj.getString("key"),trailerObj.getString("name"));
                                trailerAdapter.addTrailer(trailer);
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        for (int i = 0; i < trailerAdapter.getCount(); i++){
                            trailersList.addView(trailerAdapter.getView(i, null, null));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in json parsing
            }
        });

        ReqQueue.add(req);
    }
    public void getReviews(int id){
        String url = "http://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=" + key;
        //System.out.println("link" +url);

        JsonObjectRequest req = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray items = response.getJSONArray("results");
                            JSONObject reviewObj;
                            View view;
                            for (int i = 0; i < items.length(); i++) {
                                reviewObj = items.getJSONObject(i);
                                Review review = new Review(reviewObj.getString("author"),reviewObj.getString("content"),reviewObj.getString("url"));
                                reviewsAdapter.addReview(review);

                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                        if(reviewsAdapter.getCount()==0){
                            reviewList.addView(reviewsAdapter.getView(-1,null,null));
                        }
                        for (int i = 0; i < reviewsAdapter.getCount(); i++){
                            reviewList.addView(reviewsAdapter.getView(i, null, null));
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              // error in json parsing
            }
        });

        ReqQueue.add(req);
    }





}
