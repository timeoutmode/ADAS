package com.example.adas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

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
    private TextView tvConstructionalScore, tvWordRecallScore, tvIdeationalScore, tvOrientationScore, tvSpeechComprehensionScore, tvNamingScore
            , tvSpokenLanguageScore, tvNumberScore, tvNumberErrors, tvNumberReminders, tvWordRecognitionScore, tvPatientName;
    private Button btnSubmit;

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
        }

        initialiseObjects();
        loadResult();
        setOnClickListeners();
    }

    private void initialiseObjects() {
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        tvConstructionalScore = findViewById(R.id.tv_constructional_praxis);
        tvWordRecallScore = findViewById(R.id.tv_word_recall);
        tvIdeationalScore = findViewById(R.id.tv_ideational_praxis);
        tvOrientationScore = findViewById(R.id.tv_orientation);
        tvSpeechComprehensionScore = findViewById(R.id.tv_speech_comprehension);
        tvNamingScore = findViewById(R.id.tv_naming_task);
        tvSpokenLanguageScore = findViewById(R.id.tv_spoken_language);
        tvNumberScore = findViewById(R.id.tv_number_cancellation);
        tvNumberErrors = findViewById(R.id.tv_number_errors);
        tvNumberReminders = findViewById(R.id.tv_number_reminders);
        tvWordRecognitionScore = findViewById(R.id.tv_word_recognition);
        tvPatientName = findViewById(R.id.tv_patient_full_name);
        btnSubmit = findViewById(R.id.btn_submit);

    }

    private void loadResult() {
        tvConstructionalScore.setText(String.valueOf(result.getConstructionalPraxisScore()));
        tvWordRecallScore.setText(String.valueOf(result.getWordRecallScore()));
        tvIdeationalScore.setText(String.valueOf(result.getIdeationalPraxisScore()));
        tvOrientationScore.setText(String.valueOf(result.getOrientationScore()));
        tvSpeechComprehensionScore.setText(String.valueOf(result.getComprehensionScore()));
        tvNamingScore.setText(String.valueOf(result.getNamingScore()));
        tvSpokenLanguageScore.setText(String.valueOf(result.getSpokenLanguageScore()));
        tvNumberScore.setText(String.valueOf(result.getNumberCancellationTargetHits()));
        tvNumberErrors.setText(String.valueOf(result.getNumberCancellationErrors()));
        tvNumberReminders.setText(String.valueOf(result.getNumberCancellationTaskReminder()));
        tvWordRecognitionScore.setText(String.valueOf(result.getWordRecognitionTaskScore()));

        patient = result.getPatient();
        tvPatientName.setText(result.getPatient().getFirstName() + " " + result.getPatient().getLastName());
    }


    private void setOnClickListeners() {
        btnSubmit.setOnClickListener(c -> {
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            startActivity(intent);
        });
    }


}
