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
public class TrailerAdapter extends BaseAdapter {

    private Context mContext;
    public ArrayList<Trailer> trailers = new ArrayList<Trailer>();


    public TrailerAdapter(Context context){
        mContext = context;
    }
    @Override
    public View getView(final int i, final View convertView, ViewGroup viewGroup) {
        View trailerRow;
        if (convertView == null) {
            trailerRow = View.inflate(mContext, R.layout.trailer_list, null);
        } else {
            trailerRow = convertView;
        }
        trailerRow.setId(10+i);
        ((TextView) trailerRow.findViewById(R.id.trailer_label)).setText(trailers.get(i).getLabel());

        Picasso.with(mContext).load("http://img.youtube.com/vi/" + trailers.get(i).getUrl() + "/mqdefault.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) trailerRow.findViewById(R.id.image_trailer));
      //  System.out.println("http://img.youtube.com/vi/" + trailers.get(i).getUrl() + "/default.jpg");
        // youtube thumbnail - http://stackoverflow.com/questions/2068344/how

        final String url = trailers.get(i).getUrl();
        trailerRow.findViewById(R.id.image_trailer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "You Clicked "+i, Toast.LENGTH_LONG).show();
                watchYoutubeVideo(url);
            }
        });
        return trailerRow;
    }
    @Override
    public int getCount() {
        return trailers.size();
    }

    @Override
    public Object getItem(int i) {
        return trailers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public void addTrailer(Trailer trailer){
        trailers.add(trailer);
        notifyDataSetChanged();
    }
    public void watchYoutubeVideo(String id){
       /*from -- http://stackoverflow.com/questions/574195/android-youtube-app-play-video-intent */
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }catch (ActivityNotFoundException ex){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+id));
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }

}
