package com.example.androidproject;

public class feedback_list {
    int key;
    private String review;

    public feedback_list() {}


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }



    public feedback_list(int key, String review) {
        this.key=key;
        this.review = review;
    }
}