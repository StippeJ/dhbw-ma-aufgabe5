package com.example.stippejan.aufgabe5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Strings to access SharedPreferences
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String LAST_USER = "lastUser";
    private static final String LAST_USER_TYPE = "lastUserType";

    // Variables to access Views
    UsersRepo usersRepo;
    RadioGroup userTypeGroup;
    RadioButton randomUser;
    RadioButton femaleUser;
    RadioButton maleUser;
    Group userDataGroup;
    ImageView userImage;
    TextView userName;
    TextView userStreetHouseNumber;
    TextView userPostCodeCity;
    TextView userCountry;
    TextView errorText;
    ProgressBar progressSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersRepo = new UsersRepo();

        // Reference the RadioButtons
        userTypeGroup = findViewById(R.id.userTypeGroup);
        randomUser = findViewById(R.id.randomUser);
        femaleUser = findViewById(R.id.femaleUser);
        maleUser = findViewById(R.id.maleUser);

        // Reference all Views that are needed to represent the data of a user
        userDataGroup = findViewById(R.id.userDataGroup);
        userImage = findViewById(R.id.userImage);
        userName = findViewById(R.id.userName);
        userStreetHouseNumber = findViewById(R.id.userStreetHouseNumber);
        userPostCodeCity = findViewById(R.id.userPostCodeCity);
        userCountry = findViewById(R.id.userCountry);
        errorText = findViewById(R.id.errorText);
        progressSpinner = findViewById(R.id.progressSpinner);

        // Load the last user and the last checked RadioButton
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String lastUser = sharedPreferences.getString(LAST_USER, null);
        int lastUserType = sharedPreferences.getInt(LAST_USER_TYPE, R.id.randomUser);

        // If a user already exists, deserialize and show it
        // Else: Load a new user and show it instead
        if (lastUser != null) {
            User user = new Gson().fromJson(lastUser, User.class);
            userTypeGroup.check(lastUserType);
            errorText.setVisibility(View.INVISIBLE);
            showUser(user);
        } else {
            getRandomUser();
            userTypeGroup.check(R.id.randomUser);
        }
    }

    // Inflate the options-menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Load a new user when the menu-button gets clicked
    // Choose API-request based on selected RadioButton
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.loadUser) {
            switch (userTypeGroup.getCheckedRadioButtonId()) {
                case R.id.randomUser:
                    getRandomUser();
                    break;
                case R.id.femaleUser:
                    getUserOfCertainGender("female");
                    break;
                case R.id.maleUser:
                    getUserOfCertainGender("male");
                    break;
            }
        }

        return true;
    }

    // Request, show and save a random user from the API
    private void getRandomUser() {
        setVisibilityBeforeRequest();
        usersRepo.getRandomUser(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<ApiResponse> call, @NotNull Response<ApiResponse> response) {
                progressSpinner.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    User currentUser = apiResponse.getUserList().get(0);
                    showUser(currentUser);
                    saveUser(currentUser);
                } else {
                    errorText.setText(getString(R.string.error_api_connection));
                    errorText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ApiResponse> call, @NotNull Throwable t) {
                progressSpinner.setVisibility(View.INVISIBLE);
                errorText.setText(getString(R.string.error_parsing));
                errorText.setVisibility(View.VISIBLE);
            }
        });
    }

    // Request a user of a specific gender from the API (options: "male", "female")
    private void getUserOfCertainGender(String gender) {
        setVisibilityBeforeRequest();
        usersRepo.getUserOfCertainGender(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<ApiResponse> call, @NotNull Response<ApiResponse> response) {
                progressSpinner.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    User currentUser = apiResponse.getUserList().get(0);
                    showUser(currentUser);
                    saveUser(currentUser);
                } else {
                    errorText.setText(getString(R.string.error_api_connection));
                    errorText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ApiResponse> call, @NotNull Throwable t) {
                progressSpinner.setVisibility(View.INVISIBLE);
                errorText.setText(getString(R.string.error_parsing));
                errorText.setVisibility(View.VISIBLE);
            }
        }, gender);
    }

    // Hide all Views that show the user-data or error-information
    // Show a progress-spinner
    private void setVisibilityBeforeRequest() {
        progressSpinner.setVisibility(View.VISIBLE);
        userDataGroup.setVisibility(View.INVISIBLE);
        errorText.setVisibility(View.INVISIBLE);
    }

    // Load the data of a user-object into the Views
    private void showUser(User user) {
        userName.setText(user.getName().toString());
        userStreetHouseNumber.setText(user.getLocation().getStreet().toString());
        userPostCodeCity.setText(user.getLocation().getPostCodeAndCity());
        userCountry.setText(user.getLocation().getCountry());
        Picasso.get().load(user.getPicture().getLargePictureUrl()).placeholder(R.drawable.user_placeholder).into(userImage);
        userDataGroup.setVisibility(View.VISIBLE);
    }

    // Save a user as a serialized string in SharedPreferences
    private void saveUser(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LAST_USER, new Gson().toJson(user));
        editor.putInt(LAST_USER_TYPE, userTypeGroup.getCheckedRadioButtonId());
        editor.apply();
    }

}