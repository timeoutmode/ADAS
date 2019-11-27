package com.example.adas.NumberCancellation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adas.AssessmentLandingPageActivity;
import com.example.adas.Model.Patient;
import com.example.adas.Model.Result;
import com.example.adas.R;
import com.example.adas.WordRecognition.WordRecog;
import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NumberCancellationActivity extends AppCompatActivity {
    LinearLayout column1, column2, column3, column4, column5, column6;
    TextView tvShowInstructions, tvInstructions;
    int hits, errors, reminders;
    Result  result;
    Runnable timer, hideInstruction;
    Handler handler;
    private final static String TAG = "NumbersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_cancellation);

        initialiseObjects();
        initialiseRunnables();
        setOnClickListeners();

        hits = 0;
        errors = 0;
        reminders = 0;

        // get Result from intent
        Intent intent = getIntent();
        if(intent.hasExtra("result")) {
            result = intent.getParcelableExtra("result");
            Log.e(TAG, String.valueOf(result.getSpokenLanguageScore()));
        }

        // hide instructions after 30secs
        handler = new Handler();
        handler.postDelayed(hideInstruction, 10000);

        // move to next activity after 120secs
        handler.postDelayed(timer, 90000);
    }

    private void initialiseObjects() {
        column1 = findViewById(R.id.column1);
        column2 = findViewById(R.id.column2);
        column3 = findViewById(R.id.column3);
        column4 = findViewById(R.id.column4);
        column5 = findViewById(R.id.column5);
        column6 = findViewById(R.id.column6);
        tvShowInstructions = findViewById(R.id.tv_show_instructions);
        tvInstructions = findViewById(R.id.instructions);
        tvShowInstructions.setVisibility(View.GONE);

        LinearLayout[] columnsArray = {column1, column2, column3, column4, column5, column6};
        ArrayList<Integer> array = createNumberArray();
        Log.e("Array", String.valueOf(array.size()));
        int counter = 0;
        while (counter < array.size()) {
            for(LinearLayout layout : columnsArray) {
                int number = array.get(counter);
                NumberButton button = new NumberButton(this);
                button.setText(String.valueOf(number));
                button.setBackgroundResource(R.drawable.number_cancellation_button);
                button.setTextColor(Color.WHITE);
                button.setElevation(0);
                button.setTextSize(18);
                // set button margin
                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                buttonLayoutParams.bottomMargin = 12;
                button.setLayoutParams(buttonLayoutParams);
                layout.addView(button);

                // set onclick listeners
                button.setOnClickListener(c -> {
                    int buttonNumber = Integer.parseInt(button.getText().toString());
                    if(!button.isToggled) {
                        // change background colour
                        button.setBackgroundResource(R.drawable.number_cancellation_button_purple);
                        if(buttonNumber == 2 || buttonNumber  == 8) {
                            hits++;
                        } else {
                            errors++;
                        }
                        button.isToggled = true;
                    } else {
                        button.setBackgroundResource(R.drawable.number_cancellation_button);
                        if(buttonNumber == 2 || buttonNumber  == 8) {
                            hits--;
                        }
                        button.isToggled = false;
                    }
                });
                counter++;
            }
        }
    }

    private void setOnClickListeners() {
        tvShowInstructions.setOnClickListener(view -> {
            tvInstructions.setVisibility(View.VISIBLE);
            tvShowInstructions.setVisibility(View.GONE);
            handler.removeCallbacks(hideInstruction);
            handler.postDelayed(hideInstruction, 10000);
            reminders++;
        });
    }

    private ArrayList<Integer> createNumberArray() {
        ArrayList<Integer> numberArray = new ArrayList<>();
        int counter = 0;

        // add numbers to the array
        while (counter < 240) {
            if (counter < 20) {
                numberArray.add(2);
                counter++;
            } else if (counter < 40) {
                numberArray.add(8);
                counter++;
            } else {
                int temp = ThreadLocalRandom.current().nextInt(0, 10);
                if (!(temp == 2 || temp == 8)) {
                    numberArray.add(temp);
                    counter++;
                }
            }

        }
        Collections.shuffle(numberArray);
        return numberArray;
    }

    private void initialiseRunnables() {
        timer = new Runnable() {
            @Override
            public void run() {
                // save data and start next activity
                result = new Result();
                result.setNumberCancellationErrors(errors);
                result.setNumberCancellationTargetHits(hits);
                result.setNumberCancellationTaskReminder(reminders);
                Intent intent = new Intent(getApplicationContext(), WordRecog.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        };

        hideInstruction = new Runnable() {
            @Override
            public void run() {
                tvInstructions.setVisibility(View.GONE);
                tvShowInstructions.setVisibility(View.VISIBLE);

            }
        };
    }

}
