package com.example.adas.WordRecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adas.DelayedRecall.DelayedRecall;
import com.example.adas.DelayedRecall.DelayedRecallAns;
import com.example.adas.Model.Result;
import com.example.adas.R;

import java.util.Random;

public class WordRecog extends AppCompatActivity {
    TextView randtvWRC;
    Button randbtnWRC;
    private int count =0;
    private Result result;
    private static final String TAG = WordRecog.class.getSimpleName();


    private String wordbank[] =
            {
                    "COST",
                    "CHIMNEY",
                    "DAMAGES",
                    "SANDWICH",
                    "SOLUTION",
                    "TUBE",
                    "ENGINE",
                    "RICHES",
                    "GRAVITY",
                    "MEAL",
                    "PASSENGER",
                    "ACID"
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_recog);


        randtvWRC = findViewById(R.id.randWordWRC);
        randbtnWRC = findViewById(R.id.btn_generateWRC);
        Random random = new Random();
        int num = random.nextInt(wordbank.length);
        randtvWRC.setText(wordbank[num]);
        count++;

        Intent intent = getIntent();
        if(intent.hasExtra("result")) {
            result = intent.getParcelableExtra("result");
            Log.e(TAG, String.valueOf(result.getNumberCancellationTargetHits()));
        }

        randbtnWRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count != 12) {
                    Random random = new Random();
                    int num = random.nextInt(wordbank.length);
                    randtvWRC.setText(wordbank[num]);
                    count++;
                } else {
                    Intent answerWR = new Intent(WordRecog.this, WordRecogAns.class);
                    if(result != null) {
                        answerWR.putExtra("result", result);
                    }
                    startActivity(answerWR);
                }
            }

        });
        }

    }


