package com.example.stippejan.aufgabe5;

import com.google.gson.annotations.SerializedName;

public class Picture {

    @SerializedName("large")
    private String largePictureUrl;

    // Constructor
    public Picture(String largePictureUrl) {
        this.largePictureUrl = largePictureUrl;
    }

    // Get- and Set-methods
    public String getLargePictureUrl() {
        return largePictureUrl;
    }

    public void setLargePictureUrl(String largePictureUrl) {
        this.largePictureUrl = largePictureUrl;
    }

}
