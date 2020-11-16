package com.example.stippejan.aufgabe5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersRepo {

    UsersApi usersApi;

    public UsersRepo() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        usersApi = retrofit.create(UsersApi.class);
    }

    public void getRandomResult(Callback<ApiResponse> callback) {
        Call<ApiResponse> call = usersApi.getRandomResult();
        call.enqueue(callback);
    }

}
