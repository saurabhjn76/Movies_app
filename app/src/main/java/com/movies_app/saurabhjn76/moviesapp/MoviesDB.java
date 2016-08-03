package com.movies_app.saurabhjn76.moviesapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by saurabh on 26/7/16.
 */
public class MoviesDB {
    static final String AUTHORITY_Uri = "content://" + MovieContract.AUTHORITY;
    public boolean isMovieFavorited(ContentResolver contentResolver, int id){
        boolean val = false;
        Cursor cursor = contentResolver.query(Uri.parse(AUTHORITY_Uri + "/" + id), null, null, null, null, null);
        if (cursor != null && cursor.moveToNext()){
            val = true;
            cursor.close();
        }
        return val;
    }

    public void addMovie(ContentResolver contentResolver, Movies movie){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.MovieEntry.COLUMN_ID, movie.id);
        contentValues.put(MovieContract.MovieEntry.COLUMN_NAME, movie.name);
        contentValues.put(MovieContract.MovieEntry.COLUMN_SYNOPSIS, movie.synopsis);
        contentValues.put(MovieContract.MovieEntry.COLUMN_POSTER, movie.poster_url);
        contentValues.put(MovieContract.MovieEntry.COLUMN_RATING, movie.rating + "");
        contentValues.put(MovieContract.MovieEntry.COLUMN_RELEASE, movie.released_date);
        contentResolver.insert(Uri.parse(AUTHORITY_Uri + "/movies"), contentValues);
    }

    public void removeMovie(ContentResolver contentResolver, int id){
        Uri uri = Uri.parse(AUTHORITY_Uri + "/" + id);
        contentResolver.delete(uri, null, new String[]{id + ""});
    }

    public ArrayList<Movies> getFavoriteMovies(ContentResolver contentResolver){
            Uri uri = Uri.parse(AUTHORITY_Uri + "/movies");
        Cursor cursor = contentResolver.query(uri, null, null, null, null, null);
        ArrayList <Movies> movies = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()){
            do {
                Movies movie = new Movies();
                movie.id = cursor.getInt(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_ID));
                movie.name = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_NAME));
                movie.synopsis = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_SYNOPSIS));
                movie.rating = Float.parseFloat(cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RATING)));
                movie.poster_url = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER));
                movie.released_date = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE));
                movies.add(movie);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return movies;
    }
}
