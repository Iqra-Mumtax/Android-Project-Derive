package com.example.hamza.derive;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String city;
    public String country;
    public String age;
    public String contact;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String city, String country, String age, String contact) {
        this.username = username;
        this.city = city;
        this.country = country;
        this.age = age;
        this.contact = contact;
    }



}