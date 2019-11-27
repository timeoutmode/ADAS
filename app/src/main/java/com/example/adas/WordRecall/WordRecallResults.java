package com.example.adas.WordRecall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.adas.R;

public class WordRecallResults extends AppCompatActivity {

    TextView tv1, tv2, tv3;


    int res1=0,res2=0, res3=0,sum=0,ave=0;
    final String TAG = WordRecallAns.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_recall_results);


        Intent score = getIntent();
        res1 = score.getIntExtra("RES1",0);
        Log.e(TAG, "RESULT 1 : " + res1 );
        res2 = score.getIntExtra("RES2",0);
        Log.e(TAG, "RESULT 2 : " + res2 );
        res3 = score.getIntExtra("RES3",0);
        Log.e(TAG, "RESULT 3 : " + res3 );

        sum = res1+res2+res3;
        Log.e(TAG, " SUM ALL : " + sum );
        ave=sum/3;
        Log.e(TAG, "AVERAGE : " + ave );



    }
}
