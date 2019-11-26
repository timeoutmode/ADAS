package com.example.adas.IdeationalPraxis;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adas.Model.Result;
import com.example.adas.R;

public class IdeationalPraxisActivity extends AppCompatActivity {

    private MazeView mazeView;
    private TextView mTimerTextView;
    private Result result;

    private AppCompatActivity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideational_praxis);

        initialiseObjects();

        Intent intent = getIntent();
        result = intent.getParcelableExtra("result");

        mazeView.getTextView(mTimerTextView);

    }

    private void initialiseObjects() {
        mazeView = findViewById(R.id.Maze);
        mTimerTextView = findViewById(R.id.timerTextView);
    }

    public void setScore(int score) {
        result.setIdeationalPraxisScore(score);
    }

    public static final Activity activity(Context context) {
        while (!(context instanceof Activity)) {
            if (!(context instanceof ContextWrapper)) {
                context = null;
            }
            ContextWrapper contextWrapper = (ContextWrapper) context;
            if (contextWrapper == null) {
                return null;
            }
            context = contextWrapper.getBaseContext();
            if (context == null) {
                return null;
            }
        }
        return (Activity) context;
    }
}