package com.example.stippejan.aufgabe5;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersRepo {

    UsersApi usersApi;

    public UsersRepo() {
        Gson gson = new Gson();

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

    public void getRandomUser(Callback<ApiResponse> callback) {
        Call<ApiResponse> call = usersApi.getRandomUser("json");
        call.enqueue(callback);
    }

    public void getFemaleUser(Callback<ApiResponse> callback) {
        Call<ApiResponse> call = usersApi.getUserOfCertainGender("json", "female");
        call.enqueue(callback);
    }

    public void getMaleUser(Callback<ApiResponse> callback) {
        Call<ApiResponse> call = usersApi.getUserOfCertainGender("json", "male");
        call.enqueue(callback);
    }

}
