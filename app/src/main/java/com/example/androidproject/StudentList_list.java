package com.example.androidproject;


public class StudentList_list {

    private String profileImageUrl;
    private String name;
    private int private_key;

    private StudentList_list() {}

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(int private_key) {
        this.private_key = private_key;
    }


    public StudentList_list(String profileImageUrl, String name, int private_key) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.private_key=private_key;
    }
}