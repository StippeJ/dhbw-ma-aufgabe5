package com.example.stippejan.aufgabe5;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersApi {

    @GET("?format=json")
    Call<ApiResponse> getRandomResult();

}
