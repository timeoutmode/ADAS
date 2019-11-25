package com.example.adas.DelayedRecall;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.adas.R;
import com.example.adas.WordRecall.WordRecall;
import com.example.adas.WordRecall.WordRecallAns;
import com.example.adas.WordRecall.WordRecallResults;

import java.util.Random;

public class DelayedRecall extends AppCompatActivity {

    TextView randtvDR;
    Button randbtnDR;
    private int count =0;


    private String wordbank[] =
            {
                    "bottle",
                    "potato",
                    "girl",
                    "temple",
                    "star",
                    "animal",
                    "forest",
                    "lake",
                    "clock",
                    "office"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed_recall);

        randtvDR =  findViewById(R.id.randWordDR);
        randbtnDR = findViewById(R.id.btn_generateDR);

        randbtnDR.setOnClickListener(new View.OnClickListener() {
            @Override    public void onClick(View v) {

                    if (count != 10) {
                        Random random = new Random();
                        int num = random.nextInt(wordbank.length);
                        randtvDR.setText(wordbank[num]);
                        count++;
                    } else {
                        Intent answerWR = new Intent(DelayedRecall.this, DelayedRecallAns.class);
                        startActivity(answerWR);
                    }
                }

        });
    }

}

