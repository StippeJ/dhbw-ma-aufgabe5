package com.example.stippejan.aufgabe5;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("gender")
    private String gender;
    @SerializedName("name")
    private Name name;
    @SerializedName("location")
    private Location location;

    public User(String gender, Name name, Location location) {
        this.gender = gender;
        this.name = name;
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
