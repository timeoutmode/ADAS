package com.example.adas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.adas.DelayedRecall.DelayedRecall;
import com.example.adas.GuessingObjects.GuessTheImage;
import com.example.adas.GuessingObjects.NamingFingers;
import com.example.adas.Orientation.OrientationViewPager;
import com.example.adas.SpeechComprehension.SpeechTask;
import com.example.adas.WordRecall.WordRecall;

import java.util.Locale;

public class AssessmentLandingPageActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11,btn12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_landing_page);
        initialiseObjects();
        setOnClickListeners();

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
            navigateToActivity(GuessTheImage.class);
        });

        btn6.setOnClickListener(c -> {
            navigateToActivity(NamingFingers.class);
        });

        btn7.setOnClickListener(c ->{
            navigateToActivity(DelayedRecall.class);
        });

    }

    // method helper for starting a new activity
    private void navigateToActivity(Class cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }
}
