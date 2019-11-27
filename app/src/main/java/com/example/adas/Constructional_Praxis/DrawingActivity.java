package com.example.adas.Constructional_Praxis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.Model.Result;
import com.example.adas.R;
import com.example.adas.WordRecall.WordRecall;

public class DrawingActivity extends AppCompatActivity implements View.OnClickListener {
    PaintView paintView;
    ImageButton drawBtn, eraser;
    ImageView shapes;
    TextView textView17;
    private Bitmap canvasBitmap;
    int click = 0;
    private static final String TAG = "DrawingActivity";
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);






        paintView = (PaintView) findViewById(R.id.drawing);

       // drawBtn = findViewById(R.id.brushClicked);
        eraser = findViewById(R.id.eraserClicked);
        textView17 = findViewById(R.id.textView17);
        shapes = findViewById(R.id.shapes);



      //  drawBtn.setOnClickListener(this);
        eraser.setOnClickListener(this);

        shapes.setImageResource(R.drawable.circle);
        textView17.setText("Draw a Circle");

        // grab the intent
        Intent intent = getIntent();
        if(intent.hasExtra("result")) {
            result = intent.getParcelableExtra("result");
        }

    }


    @Override
    public void onClick(View v) {
//        if(v.getId() == R.id.brushClicked){
//            paintView.setupDrawing();
//        }

        if (v.getId() == R.id.eraserClicked){
            paintView.clear();

        }
    }



    public void go_btn_submit(View view) {
        if (click == 0){
            shapes.setImageResource(R.drawable.cube);
            textView17.setText("Draw a Cube");
            paintView.clear();

        }else if (click == 1){
            shapes.setImageResource(R.drawable.diamond);
            textView17.setText("Draw a Diamond");
            paintView.clear();

        }else if (click == 2){
            shapes.setImageResource(R.drawable.overlappingrectangles);
            textView17.setText("Draw two Overlapping Rectangles ");
            paintView.clear();

        }else if (click == 3){
            Intent intent = new Intent(DrawingActivity.this, WordRecall.class);
            result.setConstructionalPraxisScore(-1);
            intent.putExtra("result", result);
            startActivity(intent);
        }

        click++;





    }

//    public void go_btn(View view) {
//
////        if (paintView.onTouchEvent()){
////
////            Toast.makeText(this, "This is a C", Toast.LENGTH_SHORT).show();
////
////        }else {
////            Toast.makeText(this, "This is a not C", Toast.LENGTH_SHORT).show();
////        }
////
//  }
}
