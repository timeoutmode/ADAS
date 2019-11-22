package com.example.adas.Constructional_Praxis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.adas.R;

public class DrawingActivity extends AppCompatActivity implements View.OnClickListener {
    PaintView paintView;
    ImageButton drawBtn, eraser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);


        paintView = (PaintView) findViewById(R.id.drawing);
        drawBtn = findViewById(R.id.brushClicked);
        eraser = findViewById(R.id.eraserClicked);


        drawBtn.setOnClickListener(this);
        eraser.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.brushClicked){
            paintView.setupDrawing();
        }

        if (v.getId() == R.id.eraserClicked){
            paintView.clear();

        }
    }
}
