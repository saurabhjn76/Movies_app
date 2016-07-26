package com.movies_app.saurabhjn76.moviesapp;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by saurabh on 26/7/16.
 */
public class MovieContract {
    public static final String AUTHORITY = "com.movies_app.saurabhjn76.moviesapp";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
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
