package com.example.stippejan.aufgabe5;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Street {

    @SerializedName("name")
    private String name;
    @SerializedName("number")
    private int number;

    // Constructor
    public Street(String name, int number) {
        this.name = name;
        this.number = number;
    }

    // Get- and Set-methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // Overriding toString-method to get Street-objects as a String
    @NotNull
    @Override
    public String toString() {
        return getName() + " " + getNumber();
    }

}
