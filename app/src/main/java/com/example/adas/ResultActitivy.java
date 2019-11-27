package com.example.adas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.adas.Model.Patient;
import com.example.adas.Model.Result;
import com.example.adas.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResultActitivy extends AppCompatActivity {
    private static final String TAG = ResultActitivy.class.getSimpleName();
    private Result result;
    private User user;
    private Patient patient;

    // database objects
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_actitivy);

        Intent intent = getIntent();
        if(intent.hasExtra("result")) {
            result = intent.getParcelableExtra("result");
            Log.e(TAG, String.valueOf(result.getWordRecognitionTaskScore()));
            Log.e(TAG, String.valueOf(result.getNumberCancellationTargetHits()));
        }
    }
}
