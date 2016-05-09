package com.movies_app.saurabhjn76.moviesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by saurabh on 6/5/16.
 */
public class Movies implements Parcelable{
    public int id;
    public String name;
    public float rating;
    public String released_date;
    public String synopsis;
    public String poster_url;
    public double popularity;

    public Movies()
    {

    }
    protected Movies(Parcel in) {
        name = in.readString();
        rating = in.readFloat();
        popularity = in.readDouble();
        released_date = in.readString();
        synopsis = in.readString();
        poster_url = in.readString();
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeFloat(rating);
        parcel.writeDouble(popularity);
        parcel.writeString(released_date);
        parcel.writeString(synopsis);
        parcel.writeString(poster_url);
        parcel.writeInt(id);
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel parcel) {
            return new Movies(parcel);
        }

        @Override
        public Movies[] newArray(int i) {
            return new Movies[i];
        }
    };
}

