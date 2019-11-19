package com.example.adas.SpeechComprehension;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.Model.Item;
import com.example.adas.Model.SpeechRecogntionQuestion;

import com.example.adas.R;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class SpeechTask extends AppCompatActivity {

    private TextView mScoreView;
    private ImageButton mQuestionView;
    private RadioButton mButtonChoice1;
    private RadioButton mButtonChoice2;
    private RadioButton mButtonChoice3;
    private RadioButton mButtonChoice4;
    private RadioGroup radiogroup;
    private ImageButton mPhraseView;
    private Button mSubmitButton;
    TextToSpeech tts;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    int counter = 6;
    int itemcounter = 0;


    private SpeechRecogntionQuestion mquestion;

    public void initializeQuestion(){
        mquestion = new SpeechRecogntionQuestion();
        mquestion.setPhrase("In the freezing ocean waters of Antarctica,\n" +
                "the planet's largest seals make their home in a\n" +
                "frozen world. These giants are southern elephant\n" +
                "seals, and they can grow as long as the length of a\n" +
                "car and weigh as much as two cars combined. The\n" +
                "name “elephant seal” comes from both the males'\n" +
                "enormous size and from their giant trunk-like nose,\n" +
                "called a proboscis");

        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        Item item4 = new Item();
        Item item5 = new Item();
        Item item6 = new Item();

        item1.setQuestion("question1?");
        item1.setChoice1("yes");
        item1.setChoice2("no");
        item1.setChoice3("maybe");
        item1.setChoice4("idk");
        item1.setAnswer("yes");

        item2.setQuestion("question2?");
        item2.setChoice1("yes");
        item2.setChoice2("no");
        item2.setChoice3("maybe");
        item2.setChoice4("idk");
        item2.setAnswer("yes");

        item3.setQuestion("question3?");
        item3.setChoice1("yes");
        item3.setChoice2("no");
        item3.setChoice3("maybe");
        item3.setChoice4("idk");
        item3.setAnswer("yes");

        item4.setQuestion("question4?");
        item4.setChoice1("yes");
        item4.setChoice2("no");
        item4.setChoice3("maybe");
        item4.setChoice4("idk");
        item4.setAnswer("yes");

        item5.setQuestion("question5?");
        item5.setChoice1("yes");
        item5.setChoice2("no");
        item5.setChoice3("maybe");
        item5.setChoice4("idk");
        item5.setAnswer("yes");

        item6.setQuestion("question6?");
        item6.setChoice1("yes");
        item6.setChoice2("no");
        item6.setChoice3("maybe");
        item6.setChoice4("idk");
        item6.setAnswer("yes");

        ArrayList<Item> items = new ArrayList<Item>();

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);

        mquestion.setListofitems(items);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_task);

        initializeQuestion();
        mScoreView = findViewById(R.id.score);
        mQuestionView =  findViewById((R.id.question));
        mButtonChoice1 =  findViewById(R.id.choice1);
        mButtonChoice2 =  findViewById(R.id.choice2);
        mButtonChoice3 = findViewById(R.id.choice3);
        mButtonChoice4 = findViewById(R.id.choice4);
        mSubmitButton = findViewById(R.id.submitButton);
        mPhraseView = findViewById(R.id.phrase);
        radiogroup = findViewById(R.id.radiogroup);
        updateQuestion();



        mSubmitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                if (counter != 0)
                {
                    counter--;

                    if (checkAnswer())
                {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                }
                else{
                    Toast.makeText(SpeechTask.this, "WRONG", Toast.LENGTH_SHORT).show();
                               updateQuestion();
                }


                    itemcounter++;
               }
            }
        });


    }

    private void updateQuestion() {

        // make text to speech listener
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.UK);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "it ain't working", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mPhraseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    tts.speak(mquestion.getPhrase(), TextToSpeech.QUEUE_FLUSH,null,null);
                }
                else
                {
                    tts.speak(mquestion.getPhrase(), TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });


        mQuestionView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               // Log.e("Current Question:", mquestion.getListofitems().get(0).getQuestion());
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    tts.speak(mquestion.getListofitems().get(itemcounter).getQuestion(), TextToSpeech.QUEUE_FLUSH,null,null);
                }
                else
                {
                    tts.speak(mquestion.getListofitems().get(itemcounter).getQuestion(), TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

        Item temp = mquestion.getListofitems().get(itemcounter);

        mButtonChoice1.setText(temp.getChoice1());
        mButtonChoice2.setText(temp.getChoice2());
        mButtonChoice3.setText(temp.getChoice3());
        mButtonChoice4.setText(temp.getChoice4());



        mButtonChoice1.setChecked(false);
        mButtonChoice2.setChecked(false);
        mButtonChoice3.setChecked(false);
        mButtonChoice4.setChecked(false);


    }

    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }

    private boolean checkAnswer(){
        int selectedId = radiogroup.getCheckedRadioButtonId();
        RadioButton rb = radiogroup.findViewById(selectedId);
        String tempAnswer = rb.getText().toString();
        String rightanswer = mquestion.getListofitems().get(itemcounter).getAnswer();
        if (tempAnswer.equals(rightanswer)){
            return true;
        }
        else{
            return false;
        }
    }
}
