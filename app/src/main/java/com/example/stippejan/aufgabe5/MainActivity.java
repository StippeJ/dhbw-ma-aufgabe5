package com.example.stippejan.aufgabe5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UsersRepo usersRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersRepo = new UsersRepo();

        getRandomResult();
    }

    public void getRandomResult() {
        usersRepo.getRandomResult(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    Log.i("MainActivity", "getRandomUser: onResponse successful: " + apiResponse.getUserList().get(0).getName());
                } else {
                    Log.e("MainActivity", "getRandomUser: onResponse not successful");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("MainActivity", "getRandomUser: onFailure -> " + t);
            }
        });
    }

}