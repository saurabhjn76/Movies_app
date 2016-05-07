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
        popularity = in.readByte() == 0x00 ? null : in.readDouble();
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(rating);

            dest.writeByte((byte) (0x01));
            dest.writeDouble(popularity);

        dest.writeString(released_date);
        dest.writeString(synopsis);
        dest.writeString(poster_url);
        dest.writeInt(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}

