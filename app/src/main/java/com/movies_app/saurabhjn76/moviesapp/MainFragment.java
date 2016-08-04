package com.movies_app.saurabhjn76.moviesapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
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
import java.util.List;

/**
 * Created by saurabh on 6/5/16.
 */
public class MainFragment extends Fragment {

    public View mainFragmentView;
    public ArrayList<Movies> movies = new ArrayList<Movies>();
    private RequestQueue mRequestQ;
    public ImageAdapter imageAdapter;


    GridView gridview;
    public static MainFragment instance;
    public  int gridPosition=-2;
    public static String sort_order="popular";
  //  public String key = "c8618ff3d5fd73e6601c1d5e1ef3f337"; // invalid key actual key has been removed
   public String  key ="Insert key here";

    public MainFragment() {
    instance=this;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        Log.e("Onview",sort_order);




            //   Toast.makeText(getActivity(),"1234444",Toast.LENGTH_LONG).show();
            mainFragmentView = inflater.inflate(R.layout.content_main, container, false);
            mRequestQ = Volley.newRequestQueue(mainFragmentView.getContext());

            // setting up adapters
            imageAdapter = new ImageAdapter(mainFragmentView.getContext());

            gridview = (GridView) mainFragmentView.findViewById(R.id.gridView);

        if (savedInstanceState != null)
        {
            movies.clear();

            movies =(ArrayList<Movies>)savedInstanceState.get("Movie_Saved");
            sort_order=(String) savedInstanceState.get("Sort_Order");
            int ssize=savedInstanceState.getInt("Size");
            gridPosition=savedInstanceState.getInt("POSITION");
            Log.e("saed",movies.toString());
            Log.e("Size",gridPosition+"");
        }
            update(sort_order);
            Log.e("sorr ord",sort_order);
            gridview.setAdapter(imageAdapter);

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    //Toast.makeText(getActivity(), "the......" + position,
                    //      Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    Bundle movi = new Bundle();
                    intent.putExtra(Intent.EXTRA_SUBJECT, (Parcelable) movies.get(position));
                    startActivity(intent);
                }
            });


        return mainFragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putParcelableArrayList("Movie_Saved",  movies);
        outState.putString("Sort_Order",sort_order);
        outState.putInt("Size",movies.size());
        outState.putInt("POSITION", gridview.getFirstVisiblePosition());
        Log.e("Movieatsave",outState.getParcelableArrayList("Movie_Saved").toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("create",sort_order);
    }

    public void getMovies(String sort_order){
            if(sort_order.equals("favourites"))
            {
                    getFavourites();
            }
        else {
                String url = "http://api.themoviedb.org/3/movie/" + sort_order + "?api_key=" + key;
                //Toast.makeText(getActivity(),sort_order,Toast.LENGTH_SHORT).show();

                JsonObjectRequest req = new JsonObjectRequest(url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    //  Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_SHORT).show();
                                    JSONArray items = response.getJSONArray("results");
                                    JSONObject movieObj;
                                    for (int i = 0; i < items.length(); i++) {
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
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (isAdded()) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            gridview.setAdapter(imageAdapter);
                                        }
                                    });
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Verbose", "Error");
                        Toast.makeText(getActivity(), "Error:Check your internet connection", Toast.LENGTH_LONG);
                    }
                });

                mRequestQ.add(req);
            }
    }
    public void getFavourites()
    {
        movies.addAll((new MoviesDB()).getFavoriteMovies(getContext().getContentResolver()));
        for (Movies movie : movies){
            imageAdapter.addItem(movie.poster_url);
        }
        gridview.setAdapter(imageAdapter);

        gridview.setSelection(gridPosition);
        imageAdapter.notifyDataSetChanged();
    }

    public void update(String sort_orders) {
        sort_order=sort_orders;
        movies.clear();
        //Toast.makeText(getActivity(),movies.size(),Toast.LENGTH_SHORT).show();
        imageAdapter.clearItems();
        getMovies(sort_order);
        gridview.smoothScrollToPosition(gridview.getLastVisiblePosition());
        imageAdapter.notifyDataSetChanged();
        Log.e("Grid_POs",gridview.getFirstVisiblePosition()+"");
        if(gridPosition!=-2)
        {
            gridview.setSelection(gridPosition);
            Log.e("Actual",gridPosition+"");


        }

        //Toast.makeText(getActivity(),"ddfdsfdsfds"+movies.size(),Toast.LENGTH_LONG).show();
    }
}

