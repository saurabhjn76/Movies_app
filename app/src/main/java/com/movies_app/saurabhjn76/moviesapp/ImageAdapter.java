package com.movies_app.saurabhjn76.moviesapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by saurabh on 6/5/16.
 */
public  class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public ArrayList<String> images = new ArrayList<String>();

    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount(){
        return images.size();
    }

    @Override
    public Object getItem(int position){
        return images.get(position);
    }
    @Override
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
        // images.get(position)

        Picasso.with(mContext).load(images.get(position)).placeholder(R.drawable.lee_chong_wei).into(imageView);
        System.out.print(23);
        return imageView;
    }

    // references to our images
    /*private Integer[] mThumbIds = {
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
    };*/
    public void addItem(String url){
        images.add(url);
        /*notifyDataSetChanged();*/

    }

    public void clearItems() {
        images.clear();
    }
}
