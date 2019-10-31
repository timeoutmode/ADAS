package com.example.adas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.example.adas.Helper.Helpers;
import com.example.adas.Model.User;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private static final int REQUEST_CODE = 2019;
    private EditText etName, etAddress, etEmail, etPassword, etRepassword;
    private Button btnRegister;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialiseObjects();
        setOnClickListeners();
    }

    private void initialiseObjects() {
        etName = findViewById(R.id.tv_name);
        etAddress = findViewById(R.id.tv_address);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etRepassword = findViewById(R.id.et_repassword);
        btnRegister = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // initialise Places SDK
        Places.initialize(getApplicationContext(), getResources().getString(R.string.places_api_key));
    }

    private void setOnClickListeners() {
        etAddress.setOnClickListener(v -> {
            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)).setCountry("NZ").setTypeFilter(TypeFilter.ADDRESS).build(this);
            startActivityForResult(intent, REQUEST_CODE);
        });

        btnRegister.setOnClickListener(v -> {
            registerUser();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                etAddress.setText(Objects.requireNonNull(place.getAddress()).replace(", New Zealand", ""));
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, Objects.requireNonNull(status.getStatusMessage()));
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private void registerUser() {
        Helpers.showDialog(progressBar);
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        Log.i(TAG, email);
        Log.i(TAG, password);

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            // send verification email
            Log.i(TAG, "Right after createUser");
            FirebaseUser fbUser = firebaseAuth.getInstance().getCurrentUser();
            fbUser.sendEmailVerification().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Helpers.showToast(getApplicationContext(), "Please verify your email address.");
                }
            });

            // set user data
            User user = new User();
            user.setName(etName.getText().toString());
            user.setAddress(etAddress.getText().toString());
            user.setUserId(fbUser.getUid());
            user.setCreateDate(Calendar.getInstance().getTime());

            Log.i(TAG, "Before adding the user.");
            // add user to FireStore db
            db.collection("users").document(fbUser.getUid()).set(user).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Helpers.hideDialog(progressBar);
                    firebaseAuth.signOut();
                    Helpers.redirectLoginScreen(TAG, this);
                }
            });

        }).addOnFailureListener(e -> {
            Log.i(TAG, e.getMessage());
        });
    }
}
