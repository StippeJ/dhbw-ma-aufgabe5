package com.example.stippejan.aufgabe5;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Street {

    @SerializedName("name")
    private String name;
    @SerializedName("number")
    private int number;

    public Street(String name, int number) {
        this.name = name;
        this.number = number;
    }

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

    @NotNull
    @Override
    public String toString() {
        return getName() + " " + getNumber();
    }

}
