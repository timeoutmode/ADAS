package com.example.adas.IdeationalPraxis;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adas.Model.Patient;
import com.example.adas.Model.Result;
import com.example.adas.R;

public class IdeationalPraxisActivity extends AppCompatActivity {

    private MazeView mazeView;
    private TextView mTimerTextView;
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideational_praxis);

        initialiseObjects();

        //Intent intent = getIntent();
        //result = intent.getParcelableExtra("result");

        Patient patient = new Patient();

        result = new Result(5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, patient);

        mazeView.getTextView(mTimerTextView);
    }

    private void initialiseObjects() {
        mazeView = findViewById(R.id.Maze);
        mTimerTextView = findViewById(R.id.timerTextView);
    }

    public void setScore(int score) {
        result.setIdeationalPraxisScore(score);
    }

    public int getScore() {
        return result.getIdeationalPraxisScore();
    }
}