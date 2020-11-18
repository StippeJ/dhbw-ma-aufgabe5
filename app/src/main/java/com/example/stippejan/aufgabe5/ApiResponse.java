package com.example.stippejan.aufgabe5;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    @SerializedName("results")
    private List<User> userList;

    // Constructor
    public ApiResponse(List<User> userList) {
        this.userList = userList;
    }

    // Get- and Set-methods
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
