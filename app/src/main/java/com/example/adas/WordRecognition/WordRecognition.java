package com.example.adas.WordRecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adas.R;

import java.util.ArrayList;
import java.util.Random;

public class WordRecognition extends AppCompatActivity {

    TextView tv;
    Button btn;


    ArrayList<String> t1ArrayList = new ArrayList<String>();

    final String TAG = WordRecognition.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_recognition);

        tv.findViewById(R.id.randWordWRCog);
        btn.findViewById(R.id.btn_generateWRCog);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                Random random = new Random();
                int items = 12;
                for (int count=0; count!=items; count++){
                    int randomIndex = random.nextInt(t1ArrayList.size());
                    String randomElement = t1ArrayList.get(randomIndex);
                    t1ArrayList.remove(randomIndex);
                    tv.setText(randomElement);
                    Log.e(TAG, "RANDOM" + randomElement);

                }


            }


        });


    }
    public ArrayList<String> getT1ArrayList() {
        t1ArrayList.add("COST");
        t1ArrayList.add("CHIMNEY");
        t1ArrayList.add("DAMAGES");
        t1ArrayList.add("SANDWICH");
        t1ArrayList.add("SOLUTION");
        t1ArrayList.add("TUBE");
        t1ArrayList.add("ENGINE");
        t1ArrayList.add("RICHES");
        t1ArrayList.add("GRAVITY");
        t1ArrayList.add("MEAL");
        t1ArrayList.add("PASSENGER");
        t1ArrayList.add("ACID");
        return t1ArrayList;
    }

}
