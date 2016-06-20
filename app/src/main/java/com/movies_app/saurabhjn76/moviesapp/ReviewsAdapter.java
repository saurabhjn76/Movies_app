package com.movies_app.saurabhjn76.moviesapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by saurabh on 19/6/16.
 */
public class ReviewsAdapter extends BaseAdapter {

    private Context mContext;
    public ArrayList<Review> reviews = new ArrayList<Review>();


    public ReviewsAdapter(Context context){
        mContext = context;
    }
    @Override
    public View getView(final int i, final View convertView, ViewGroup viewGroup) {
        View reviewRow;
        if (convertView == null) {
            reviewRow = View.inflate(mContext, R.layout.review_layout, null);
        } else {
            reviewRow = convertView;
        }

        reviewRow.setId(1000+i);
        if(i!=-1) {
            ((TextView) reviewRow.findViewById(R.id.reviewAuthor)).setText(reviews.get(i).getAuthor());
            ((TextView) reviewRow.findViewById(R.id.reviewText)).setText(reviews.get(i).getContent());
            System.out.println(reviews.get(i).getAuthor());
        }
        else{
            ((TextView) reviewRow.findViewById(R.id.reviewText)).setText("No Reviews Available");
            ((TextView) reviewRow.findViewById(R.id.reviewText)).setVisibility(0);

        }
        // youtube thumbnail - http://stackoverflow.com/questions/2068344/how

        return reviewRow;
    }
    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int i) {
        return reviews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public void addReview(Review review){
        reviews.add(review);
        notifyDataSetChanged();
    }

}
