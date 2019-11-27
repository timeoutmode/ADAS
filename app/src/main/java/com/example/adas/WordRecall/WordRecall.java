package com.example.adas.WordRecall;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adas.Model.Result;
import com.example.adas.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import android.os.Handler;


public class WordRecall extends AppCompatActivity {

    TextView randtv;
    Button randbtn;
    private int counter =0, trial =0, correctAnswer = 0;
    private ArrayList<String> wordBankArray;
    private Result result;
    private static final String TAG = "WordRecallActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordrecall);

        // initalise array of words
        initialiseWordBank();

        // shuffles word bank array
        Collections.shuffle(wordBankArray);

        // intialise view objects
        initialiseObjects();

        // set OnClick listeners
        setOnClickListeners();

        Intent intent = getIntent();
        trial = intent.getIntExtra("trial", 0);
        correctAnswer = intent.getIntExtra("correctAnswer", 0);
        if(intent.hasExtra("result")) {
            result = intent.getParcelableExtra("result");
        }
    }

    private void setOnClickListeners() {
        randbtn.setOnClickListener(new View.OnClickListener() {
            @Override    public void onClick(View v) {
                if (trial != 3) {
                    if (counter < wordBankArray.size()) {
                        String word = wordBankArray.get(counter);
                        randtv.setText(word);
                        counter++;
                    } else {
                        Intent intent = new Intent(WordRecall.this, WordRecallAns.class);
                        intent.putExtra("trial", trial);
                        intent.putExtra("result", result);
                        intent.putExtra("correctAnswer", correctAnswer);
                        startActivity(intent);
                    }
                } else {
                    Intent resultWR = new Intent(WordRecall.this, WordRecallResults.class);
                    startActivity(resultWR);
                }
            }

        });
    }

    private void initialiseWordBank() {
        wordBankArray = new ArrayList<>();
        wordBankArray.add("bottle");
        wordBankArray.add("potato");
        wordBankArray.add("girl");
        wordBankArray.add("temple");
        wordBankArray.add("star");
        wordBankArray.add("animal");
        wordBankArray.add("forest");
        wordBankArray.add("lake");
        wordBankArray.add("clock");
        wordBankArray.add("office");
    }

    private void initialiseObjects() {
        randtv =  findViewById(R.id.randWord);
        randbtn = findViewById(R.id.btn_generate);

        // initialise the first word
        randtv.setText(wordBankArray.get(counter++));
    }

}


