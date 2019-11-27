package com.example.adas.WordRecall;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.adas.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;
import android.os.Handler;


public class WordRecall extends AppCompatActivity {

    TextView randtv;
    Button randbtn;
    private int count =0, trial =0;


    private String wordbank[] =
                     {
                    "bottle",
                    "potato",
                    "girl",
                    "temple",
                    "star",
                    "animal",
                    "forest",
                    "lake",
                    "clock",
                    "office"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordrecall);

        randtv =  findViewById(R.id.randWord);
        randbtn = findViewById(R.id.btn_generate);


        Intent intent = getIntent();
        trial = intent.getIntExtra("TRIAL", 0);


        randbtn.setOnClickListener(new View.OnClickListener() {
            @Override    public void onClick(View v) {
                if (trial != 3) {
                    if (count != 10) {
                        Random random = new Random();
                        int num = random.nextInt(wordbank.length);
                        randtv.setText(wordbank[num]);
                        count++;
                    } else {
                        trial++;
                        Intent answerWR = new Intent(WordRecall.this, WordRecallAns.class);
                        answerWR.putExtra("TRIAL", (trial));
                        startActivity(answerWR);
                        Log.e("TRIAL NUMBER", String.valueOf(trial));
                    }
                } else {
                    Intent resultWR = new Intent(WordRecall.this, WordRecallResults.class);
                    startActivity(resultWR);
                }
            }

        });
    }

    }


