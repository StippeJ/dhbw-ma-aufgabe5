package com.example.stippejan.aufgabe5;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("street")
    private Street street;
    @SerializedName("postcode")
    private String postCode;        // Type String to allow postcodes with leading zeros or containing letters
    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String country;

    // Constructor
    public Location(Street street, String postCode, String city, String country) {
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }

    // Get- and Set-methods
    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Get postcode and city as one String
    public String getPostCodeAndCity() {
        return String.format("%s %s", getPostCode(), getCity());
    }

}
