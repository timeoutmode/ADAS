package com.example.adas.DelayedRecall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.adas.R;

public class DelayedRecallResults extends AppCompatActivity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed_recall_results);
        tv1 = findViewById(R.id.trial1res);
        tv1.setText(getIntent().getStringExtra("COUNT"));

    }
}
