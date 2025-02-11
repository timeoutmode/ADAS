package com.example.adas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.adas.Orientation.OrientationTaskActivity;
import com.example.adas.R;
import com.example.adas.SpeechComprehension.SpeechTask;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private ConstraintLayout clPatients, clAssessment, clAccount;
    private ImageView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initaliseObjects();
        setOnClickListeners();
    }

    private void initaliseObjects() {
        clPatients = findViewById(R.id.cl_patients);
        clAssessment = findViewById(R.id.cl_assessment);
        clAccount = findViewById(R.id.cl_account);
        info = findViewById(R.id.iv_info);
    }

    private void setOnClickListeners() {
        clPatients.setOnClickListener(c -> {
            navigateToActivity(PatientActivity.class);
        });

        clAssessment.setOnClickListener(c -> {
            Intent intent = new Intent(this, AddPatientActivity.class);
            intent.putExtra("assessment", true);
            startActivity(intent);
            //navigateToActivity(AssessmentLandingPageActivity.class);
        });

        clAccount.setOnClickListener(c -> {
            navigateToActivity(AccountActivity.class);
        });

        info.setOnClickListener(c -> {
            // TODO implement a fragment dialog showing ADAS information
        });
    }

    // method helper for starting a new activity
    private void navigateToActivity(Class cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }
}
