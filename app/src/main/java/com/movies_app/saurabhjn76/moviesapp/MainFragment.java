package com.movies_app.saurabhjn76.moviesapp;

import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by saurabh on 6/5/16.
 */
public class MainFragment extends Fragment {

    public View mainFragmentView;
    public ArrayList<Movies> movies = new ArrayList<Movies>();
    private RequestQueue mRequestQ;
    public ImageAdapter imageAdapter;
    GridView gridview;
    public String key = "7c8618ff3d5fd73e6601c1d5e1ef3f33";

    public MainFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainFragmentView = inflater.inflate(R.layout.content_main, container, false);
        Toast.makeText(getActivity(),"1234444",Toast.LENGTH_LONG).show();
        mRequestQ = Volley.newRequestQueue(mainFragmentView.getContext());

        // setting up adapters
        imageAdapter = new ImageAdapter(mainFragmentView.getContext());
        gridview = (GridView) mainFragmentView.findViewById(R.id.gridView);
        updateUI();
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getActivity(), "the......" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return mainFragmentView;
    }
    public void getMovies(){
        String url = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc"  + "&"
                + "&api_key=" + key;
        Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
        JsonObjectRequest req = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_SHORT).show();
                            JSONArray items = response.getJSONArray("results");
                            JSONObject movieObj;
                            for (int i=0; i<items.length(); i++){
                                movieObj = items.getJSONObject(i);
                                Movies movie = new Movies();
                                movie.id = movieObj.getInt("id");
                                movie.name = movieObj.getString("original_title");
                                movie.rating = (float) movieObj.getDouble("vote_average");
                                movie.popularity = movieObj.getDouble("popularity");
                                movie.synopsis = movieObj.getString("overview");
                                movie.poster_url = "http://image.tmdb.org/t/p/w185/" + movieObj.getString("poster_path");
                                movie.released_date = movieObj.getString("release_date");

                                movies.add(movie);
                                // Add image to adapter
                                imageAdapter.addItem(movie.poster_url);
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                gridview.setAdapter(imageAdapter);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Showcase", "Error in JSON Parsing");
            }
        });

        mRequestQ.add(req);
    }

    public void updateUI() {
        movies.clear();
        imageAdapter.clearItems();
        getMovies();
        Toast.makeText(getActivity(),"ddfdsfdsfds"+movies.size(),Toast.LENGTH_LONG).show();
    }
}

