package com.movies_app.saurabhjn76.moviesapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by saurabh on 6/5/16.
 */
public  class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
               /* imageView.setLayoutParams(new GridView.LayoutParams(85, 85));*/
            imageView.setPadding(0, 0, 0, 0);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).load("http://i.imgur.com/DvpvklR.png").placeholder(R.mipmap.ic_launcher).into(imageView);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.lee_chong_wei, R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei,  R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei, R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei,  R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei, R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei,  R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei, R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei,  R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei, R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei,  R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei, R.drawable.lee_chong_wei,
            R.drawable.lee_chong_wei,  R.drawable.lee_chong_wei,
    };
}
