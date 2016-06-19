package com.movies_app.saurabhjn76.moviesapp;

/**
 * Created by saurabh on 19/6/16.
 */
public class Trailer {
    private String url;
    private String label;
    private String id;

     Trailer(){

    }
     Trailer (String id,String url,String label)
    {
        this.url=url;
        this.id=id;
        this.label=label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }
}
