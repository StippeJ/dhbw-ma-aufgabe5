package com.example.stippejan.aufgabe5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UsersRepo usersRepo;
    RadioGroup userTypeGroup;
    RadioButton randomUser;
    RadioButton femaleUser;
    RadioButton maleUser;
    Button loadUser;
    Group userDataGroup;
    TextView userName;
    TextView userStreetHouseNumber;
    TextView userPostCodeCity;
    TextView userCountry;
    TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersRepo = new UsersRepo();

        userTypeGroup = findViewById(R.id.userTypeGroup);
        randomUser = findViewById(R.id.randomUser);
        femaleUser = findViewById(R.id.femaleUser);
        maleUser = findViewById(R.id.maleUser);
        userTypeGroup.check(R.id.randomUser);
        loadUser = findViewById(R.id.loadUserButton);

        userDataGroup = findViewById(R.id.userDataGroup);
        userName = findViewById(R.id.userName);
        userStreetHouseNumber = findViewById(R.id.userStreetHouseNumber);
        userPostCodeCity = findViewById(R.id.userPostCodeCity);
        userCountry = findViewById(R.id.userCountry);
        errorText = findViewById(R.id.errorText);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadUser.setOnClickListener((v) -> {
            switch (userTypeGroup.getCheckedRadioButtonId()) {
                case R.id.randomUser:
                    getRandomUser();
                    break;
                case R.id.femaleUser:
                    getFemaleUser();
                    break;
                case R.id.maleUser:
                    getMaleUser();
                    break;
            }
        });
    }

    public void getRandomUser() {
        userDataGroup.setVisibility(View.INVISIBLE);
        errorText.setVisibility(View.INVISIBLE);

        usersRepo.getRandomUser(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    User user = apiResponse.getUserList().get(0);

                    userName.setText(user.getName().toString());
                    userStreetHouseNumber.setText(user.getLocation().getStreet().toString());
                    userPostCodeCity.setText(user.getLocation().getPostCodeAndCity());
                    userCountry.setText(user.getLocation().getCountry());
                    userDataGroup.setVisibility(View.VISIBLE);
                } else {
                    errorText.setText(getString(R.string.error_api_connection));
                    errorText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                errorText.setText(getString(R.string.error_parsing));
                errorText.setVisibility(View.VISIBLE);
            }
        });
    }

    public void getFemaleUser() {
        userDataGroup.setVisibility(View.INVISIBLE);
        errorText.setVisibility(View.INVISIBLE);

        usersRepo.getFemaleUser(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    User user = apiResponse.getUserList().get(0);

                    userName.setText(user.getName().toString());
                    userStreetHouseNumber.setText(user.getLocation().getStreet().toString());
                    userPostCodeCity.setText(user.getLocation().getPostCodeAndCity());
                    userCountry.setText(user.getLocation().getCountry());
                    userDataGroup.setVisibility(View.VISIBLE);
                } else {
                    errorText.setText(getString(R.string.error_api_connection));
                    errorText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                errorText.setText(getString(R.string.error_parsing));
                errorText.setVisibility(View.VISIBLE);
            }
        });
    }

    public void getMaleUser() {
        userDataGroup.setVisibility(View.INVISIBLE);
        errorText.setVisibility(View.INVISIBLE);

        usersRepo.getMaleUser(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    User user = apiResponse.getUserList().get(0);

                    userName.setText(user.getName().toString());
                    userStreetHouseNumber.setText(user.getLocation().getStreet().toString());
                    userPostCodeCity.setText(user.getLocation().getPostCodeAndCity());
                    userCountry.setText(user.getLocation().getCountry());
                    userDataGroup.setVisibility(View.VISIBLE);
                } else {
                    errorText.setText(getString(R.string.error_api_connection));
                    errorText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                errorText.setText(getString(R.string.error_parsing));
                errorText.setVisibility(View.VISIBLE);
            }
        });
    }

}