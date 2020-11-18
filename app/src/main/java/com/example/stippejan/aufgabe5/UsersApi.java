package com.example.stippejan.aufgabe5;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsersApi {

    // Get a random user with a specified data-format (options: json, xml, csv, prettyjson, yaml)
    @GET("?")
    Call<ApiResponse> getRandomUser(@Query("format") String format);

    // Get a random user with a specified data-format (options: json, xml, csv, prettyjson, yaml)
    // Also specify the gender of the user (options: male, female)
    @GET("?")
    Call<ApiResponse> getUserOfCertainGender(@Query("format") String format, @Query("gender") String gender);

}
