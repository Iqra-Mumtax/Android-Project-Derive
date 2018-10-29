package com.example.hamza.derive;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User implements Parcelable {

    public String id;
    public String name;
    public String age;
    public String city;
    public String country;
    public String contact;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String id, String name, String age, String city, String country, String contact) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.country = country;
        this.contact = contact;
    }

    public User(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.age = in.readString();
        this.city = in.readString();
        this.country = in.readString();
        this.contact = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getContact() {
        return contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(city);
        dest.writeString(country);
        dest.writeString(contact);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>(){
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int describeContents() {
        return 0;
    }
}