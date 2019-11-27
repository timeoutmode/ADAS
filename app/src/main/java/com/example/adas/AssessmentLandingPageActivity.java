package com.example.adas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Button;


import com.example.adas.Constructional_Praxis.DrawingActivity;
import com.example.adas.DelayedRecall.DelayedRecall;
import com.example.adas.GuessingObjects.StartImageGame;
import com.example.adas.Model.Result;
import com.example.adas.NumberCancellation.NumberCancellationActivity;
import com.example.adas.Orientation.OrientationViewPager;
import com.example.adas.SpeechComprehension.SpeechTask;
import com.example.adas.SpokenLanguage.SpokenLanguageActivity;
import com.example.adas.WordRecall.WordRecall;

public class AssessmentLandingPageActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11,btn12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_landing_page);
        initialiseObjects();
        setOnClickListeners();

        // check if intent is working
        Intent intent = getIntent();
        Result result = intent.getParcelableExtra("result");
        if (result != null) {
            Log.e("Errors", String.valueOf(result.getNumberCancellationErrors()));
            Log.e("Hits", String.valueOf(result.getNumberCancellationTargetHits()));
            Log.e("Reminders", String.valueOf(result.getNumberCancellationTaskReminder()));
        }

    }

    private void initialiseObjects() {
        btn1 = findViewById(R.id.button_1);
        btn2 = findViewById(R.id.button_2);
        btn3 = findViewById(R.id.button_3);
        btn4 = findViewById(R.id.button_4);
        btn5 = findViewById(R.id.button_5);
        btn6 = findViewById(R.id.button_6);
        btn7 = findViewById(R.id.button_7);
        btn8 = findViewById(R.id.button_8);
        btn9 = findViewById(R.id.button_9);
        btn10 = findViewById(R.id.button_10);
        btn11 = findViewById(R.id.button_11);
        btn12 = findViewById(R.id.button_12);
    }

    private void setOnClickListeners() {
        btn1.setOnClickListener(c -> {
            navigateToActivity(WordRecall.class);
        });

        btn2.setOnClickListener(c -> {
            navigateToActivity(SpeechRecognizer.class);
        });

        btn3.setOnClickListener(c -> {
            navigateToActivity(SpeechTask.class);
        });

        btn4.setOnClickListener(c -> {
            navigateToActivity(OrientationViewPager.class);
        });

        btn5.setOnClickListener(c -> {
            navigateToActivity(StartImageGame.class);
        });

        btn6.setOnClickListener(c -> {
            navigateToActivity(DrawingActivity.class);
        });

        btn7.setOnClickListener(c ->{
            navigateToActivity(DelayedRecall.class);
        });

        btn11.setOnClickListener(view -> {
            navigateToActivity(SpokenLanguageActivity.class);
        });

        btn12.setOnClickListener(c -> {
            navigateToActivity(NumberCancellationActivity.class);
        });

    }

    // method helper for starting a new activity
    private void navigateToActivity(Class cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }
}
