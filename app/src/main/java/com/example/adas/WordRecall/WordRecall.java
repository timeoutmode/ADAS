package com.example.adas.WordRecall;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adas.R;


import java.util.Random;


public class WordRecall extends AppCompatActivity {
    TextView randtv;
    Button randbtn;

    private String words[] = {"bottle","potato","girl", "temple", "star","animal","forest","lake","clock","office"};
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordrecall);

            randtv = findViewById(R.id.randWord);
            randbtn = (Button) findViewById(R.id.btn_generate);
            randbtn.setOnClickListener(new View.OnClickListener() {

                @Override    public void onClick(View v)
                {
                    Random random=new Random();
                    int num = random.nextInt(words.length);
                    randtv.setText(words[num]);
                }
            });
    }
}


