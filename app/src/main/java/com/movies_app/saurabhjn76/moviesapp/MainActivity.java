package com.movies_app.saurabhjn76.moviesapp;

import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public  View v;
    public View s;
    boolean mTwoPane=true;
    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v= findViewById(R.id.content_layout);
        gridView= (GridView)findViewById(R.id.gridView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (!checknetconnection()) {
            Snackbar.make(v, "No internet Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                    .show();

        }



        setSupportActionBar(toolbar);
        FragmentManager manager = getSupportFragmentManager();

        if (findViewById(R.id.content_layout) == null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            MainFragment.instance.update();
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    DetailFragment detailActivityFragment = DetailFragment.newInstance(MainFragment.instance.movies.get(position));
                  //  putExtra(Intent.EXTRA_SUBJECT,(Parcelable)movies.get(position));
                    getSupportFragmentManager().beginTransaction().replace(R.id.detailContainer, detailActivityFragment).commit();

                }
            });



        } else {
            mTwoPane = false;
            manager.beginTransaction().replace(R.id.content_layout, new MainFragment()).commit();
        }
        //

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!checknetconnection()) {
            Snackbar.make(v, "No internet Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                    .show();

        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!checknetconnection()) {
            Snackbar.make(v, "No internet Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                    .show();

        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (!checknetconnection()) {
            Snackbar.make(v, "No internet Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                    .show();

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (!checknetconnection()) {
            Snackbar.make(v, "No internet Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                    .show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        MainFragment fragment = MainFragment.instance;

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_popularity) {
            fragment.sort_order = "popular";
            fragment.update();



        }
        if(id==R.id.action_rated)
        {
            fragment.sort_order = "top_rated";
            fragment.update();
        }
        if(id==R.id.action_fav)
        {
            fragment.sort_order="favourites";
            fragment.update();
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean checknetconnection()
    {
        ConnectivityManager manager_c =(ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activenetwork=manager_c.getActiveNetworkInfo();
        boolean isConnected= activenetwork!= null && activenetwork.isConnectedOrConnecting();
        return isConnected;
    }

}

