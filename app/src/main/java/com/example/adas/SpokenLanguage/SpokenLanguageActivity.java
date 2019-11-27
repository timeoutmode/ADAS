package com.example.adas.SpokenLanguage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.adas.R;

import java.util.ArrayList;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SpokenLanguageActivity extends AppCompatActivity {
    EditText etAnswer;
    Button btnSubmit;
    ImageView ivRecord;
    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;
    private static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoken_language);

        verifyPermissions();
        initaliseObjects();
        setOnClickListeners();

    }

    private void initaliseObjects() {
        btnSubmit = findViewById(R.id.btn_submit);
        etAnswer = findViewById(R.id.et_answer);
        ivRecord = findViewById(R.id.iv_record);

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    }

    private void setOnClickListeners() {
        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if(matches != null)
                {
                    etAnswer.setText(matches.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        ivRecord.setOnTouchListener((view, motionEvent) -> {
            switch(motionEvent.getAction()){

                case MotionEvent.ACTION_UP:
                    mSpeechRecognizer.stopListening();
                    etAnswer.setText("Your answer will show up here.");
                    break;
                case MotionEvent.ACTION_DOWN:
                    etAnswer.setText("");
                    etAnswer.setText("Listening...");
                    mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                    break;
            }
            return false;
        });

    }

    public void verifyPermissions() {
        String[] permissions = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE,
                Manifest.permission.RECORD_AUDIO};

        boolean allPermissionsGranted = true;
        for (String permission : permissions)
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        if (!allPermissionsGranted) {

            ActivityCompat.requestPermissions(
                    SpokenLanguageActivity.this,
                    permissions,
                    REQUEST_CODE
            );
        }


    }
}