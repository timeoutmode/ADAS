package com.example.adas.WordRecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.adas.R;

public class WordRecogResult extends AppCompatActivity {

    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_recog_result);tv1 = findViewById(R.id.trial1res);
        tv1.setText(getIntent().getStringExtra("COUNT"));

    }
}
