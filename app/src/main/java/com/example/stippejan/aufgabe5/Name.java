package com.example.stippejan.aufgabe5;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Name {

    @SerializedName("first")
    private String firstName;
    @SerializedName("last")
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Override
    public String toString() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

}
