package com.movies_app.saurabhjn76.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by saurabh on 6/5/16.
 */
public class DetailActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailactivity);
        Movies movies = getIntent().getParcelableExtra(Intent.EXTRA_SUBJECT);
        ((TextView) findViewById(R.id.textView_movietitle)).setText(movies.name);
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


    }


}
