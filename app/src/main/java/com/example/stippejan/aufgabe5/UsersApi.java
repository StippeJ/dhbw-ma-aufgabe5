package com.example.stippejan.aufgabe5;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsersApi {

    @GET("?")
    Call<ApiResponse> getRandomUser(@Query("format") String format);

    @GET("?")
    Call<ApiResponse> getUserOfCertainGender(@Query("format") String format, @Query("gender") String gender);

}
