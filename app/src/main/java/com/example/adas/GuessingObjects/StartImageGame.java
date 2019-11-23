package com.example.adas.GuessingObjects;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.adas.HomeActivity;
import com.example.adas.Model.ImageQuestion;
import com.example.adas.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class StartImageGame extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_image_game);
    }

    public void btn_start(View view) {
        arletinstructions();
//        Intent intent = new Intent(StartImageGame.this, GuessTheImage.class);
//        startActivity(intent);
    }








    private void arletinstructions(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StartImageGame.this, R.style.MyDialogTheme);
        alertDialog.setTitle("Guess the Finger");
        alertDialog.setMessage(R.string.Guess_the_image_instructions);

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(StartImageGame.this, GuessTheImage.class);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(StartImageGame.this, HomeActivity.class);
                startActivity(intent);

            }
        });


        alertDialog.create().show();

    }
}
