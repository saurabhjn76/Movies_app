package com.movies_app.saurabhjn76.moviesapp;

/**
 * Created by saurabh on 20/6/16.
 */
public class Review {
    private String author;
    private String content;
    private String url;


    Review(String author,String content,String url){
            this.author=author;
            this.content=content;
            this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}