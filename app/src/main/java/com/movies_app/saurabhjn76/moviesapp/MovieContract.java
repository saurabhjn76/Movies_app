package com.movies_app.saurabhjn76.moviesapp;

import android.provider.BaseColumns;

/**
 * Created by saurabh on 26/7/16.
 */
public class MovieContract {
    public MovieContract(){
    }

    public static final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "MOVIES";
        public static final String COLUMN_NAME = "movie_name";
        public static final String COLUMN_RATING = "movie_rating";
        public static final String COLUMN_RELEASE = "released_date";
        public static final String COLUMN_SYNOPSIS = "synopsis";
        public static final String COLUMN_POSTER = "poster_url";
        public static final String COLUMN_ID = "_id";
    }
}
