package com.example.adas.Constructional_Praxis;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.R;

public class DrawingActivity extends AppCompatActivity implements View.OnClickListener {
    PaintView paintView;
    ImageButton drawBtn, eraser;
    ImageView shapes;
    TextView textView17;
    private Bitmap canvasBitmap;
    int click = 0;

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
        if (click == 1){

            shapes.setImageResource(R.drawable.brush);
            textView17.setText("Draw a Cube");
            paintView.clear();


        }else if (click == 2){
            shapes.setImageResource(R.drawable.bed);
            textView17.setText("Draw a Diamond");
            paintView.clear();

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
