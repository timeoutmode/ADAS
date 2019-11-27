package com.example.adas.SpokenLanguage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adas.Model.Result;
import com.example.adas.NumberCancellation.NumberCancellationActivity;
import com.example.adas.R;

import java.util.ArrayList;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

public class SpokenLanguageActivity extends AppCompatActivity {
    private EditText etAnswer;
    private Button btnSubmit;
    private TextView tvSentence, tvPager;
    private ImageView ivRecord;
    private SpeechRecognizer mSpeechRecognizer;
    private Intent mSpeechRecognizerIntent;
    private static final int REQUEST_CODE = 1234;
    private int counter = 0;
    private String[] sentenceArray;
    private int missingWords = 0;
    private Handler handler;
    private boolean isLoaded = false;
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoken_language);

        verifyPermissions();
        initaliseObjects();
        setOnClickListeners();

        sentenceArray = createSentenceArray();
        loadNextSentence();

        Intent intent = getIntent();
        result = intent.getParcelableExtra("result");

    }

    private void initaliseObjects() {
        btnSubmit = findViewById(R.id.btn_submit);
        etAnswer = findViewById(R.id.et_answer);
        ivRecord = findViewById(R.id.iv_record);
        tvSentence = findViewById(R.id.tv_sentence);
        tvPager = findViewById(R.id.tv_pager);

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    }

    @SuppressLint("ClickableViewAccessibility")
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
                    return true;
            }
            return false;
        });

        btnSubmit.setOnClickListener(c -> {

            missingWords += getNumberOfMissingWords();
            counter++;
            tvPager.setText((counter + 1) + "/4");
            if(counter < sentenceArray.length) {
                loadNextSentence();
            } else {

                Intent intent = new Intent(this, NumberCancellationActivity.class);
                if(result != null) {
                    result.setSpokenLanguageScore(calculateScore());
                    intent.putExtra("result", result);
                }
                startActivity(intent);
            }
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
    private int calculateScore() {
        int totalWords = getNumberOfWords(sentenceArray);
        int percentage = (int)(missingWords/(double)totalWords * 100);
        Log.e("Percentage", String.valueOf(percentage));
        int score;
        if(percentage > 80) {
            score = 5;
        } else if (percentage > 50) {
            score = 4;
        } else if (percentage > 25) {
            score = 3;
        } else if (percentage > 15 ) {
            score = 2;
        } else if (percentage > 5) {
            score = 1;
        } else {
            score = 0;
        }

        return score;
    }

    private void loadNextSentence() {
        String sentence = sentenceArray[counter];
        tvSentence.setText(sentence);
        etAnswer.setText("Your answer will show up here.");
    }

    private int getNumberOfMissingWords() {
        String[] currentSentence = sentenceArray[counter].toLowerCase().replaceAll("[,.]", "").split(" ");
        String[] currentAnswer = etAnswer.getText().toString().toLowerCase().split(" ");
        Log.e("Current Sentence", sentenceArray[counter].toString().toLowerCase());
        Log.e("Current Answer", etAnswer.getText().toString().toLowerCase());
        int tempCounter = 0;
        for(String word : currentAnswer) {

            for (String w : currentSentence) {
                if(word.contains(w))
                {
                    Log.e("MATCHES", word + "  " + w);
                    tempCounter++;
                    break;
                }
            }
        }
        Log.e("COUNTER", String.valueOf(tempCounter));
        return currentSentence.length - tempCounter;
    }



    private String[] createSentenceArray() {
        String array[] = {
                "She was too short to see over the fence.",
                "The book is in front of the table.",
                "She did not cheat on the test, for it was not the right thing to do.",
                "He did not want to go to the dentist, yet he went anyway."
        };
        return array;
    }

    private int getNumberOfWords(String[] sentenceArray) {
        int numberOfWords = 0;
        for(String sentence : sentenceArray) {
            String[] words = sentence.toLowerCase().split(" ");
            numberOfWords += words.length;
        }
        return numberOfWords;
    }

    public class Question {
        private String question, answer;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}