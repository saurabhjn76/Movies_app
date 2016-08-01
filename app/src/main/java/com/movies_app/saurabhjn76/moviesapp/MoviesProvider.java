package com.movies_app.saurabhjn76.moviesapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by saurabh on 26/7/16.
 */
public class MoviesProvider extends ContentProvider {
    SQLiteDatabase database;
    MovieDbHelper DbHelper;

    static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(MovieContract.AUTHORITY, "movies",1);
        sUriMatcher.addURI(MovieContract.AUTHORITY, "#", 2);
    }
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id=database.insert(MovieContract.MovieEntry.TABLE_NAME,null,values);
        if(id > 0)
        {
            Uri uri1 = ContentUris.withAppendedId(MovieContract.CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        }
        return uri;
    }

    @Override
    public boolean onCreate() {
        DbHelper = new MovieDbHelper(getContext());
        database = DbHelper.getWritableDatabase();
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int type =0;
        if(sUriMatcher.match(uri)==1){
            type = database.delete(MovieContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
        }
        else if(sUriMatcher.match(uri)==2)
        {
            type = database.delete(MovieContract.MovieEntry.TABLE_NAME, MovieContract.MovieEntry.COLUMN_ID + " = ?", selectionArgs);
        }
        else{
            throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return type;
    }

    @   Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        if(sortOrder==null)
            sortOrder= MovieContract.MovieEntry.COLUMN_ID;
        if(sUriMatcher.match(uri)==1){
            cursor = database.query(MovieContract.MovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        }
        else if(sUriMatcher.match(uri)==2)
        {
            cursor=database.query(MovieContract.MovieEntry.TABLE_NAME, projection, MovieContract.MovieEntry.COLUMN_ID + " = ?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
        }
        else{
            throw new IllegalArgumentException("Unsupported URI " + uri);
        }
       cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }
}
