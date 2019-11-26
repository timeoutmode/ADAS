package com.example.adas.SpeechComprehension;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.GuessingObjects.StartImageGame;
import com.example.adas.Model.Item;
import com.example.adas.Model.Result;
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
    boolean isInitialized = false;
    Result result;


    private SpeechRecogntionQuestion mquestion;

    public void initializeQuestion(){
        mquestion = new SpeechRecogntionQuestion();
        mquestion.setPhrase("One of the things I like best about my school is my art class.\n" +
                "We have a great teacher named Mrs. Hilbert.\n" +
                "She taught us how to mix paint to make just the right colours for our paintings.\n" +
                "She plays good music while we draw and paint.\n" +
                "We draw an paint almost everyday in class.\n" +
                "Some days we look at  pictures of other artists.\n" +
                "It is interesting to do this.\n" +
                "It helps me think of things that I want to paint or draw.\n" +
                "We have an art exhibit May this year.\n" +
                "Our work will be done in one of the banks in our town.\n" +
                "We are inviting people from the community to our exhibit.\n" +
                "Our parents are invited to go with us.\n" +
                "I have three paintings I am working on now.\n" +
                "I hope one of them will be chosen to be in the exhibit.\n" +
                "I like having a goal to work toward.");

        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        Item item4 = new Item();
        Item item5 = new Item();
        Item item6 = new Item();

        item1.setQuestion("Which is one of the favourite classes of this student?");
        item1.setChoice1("math");
        item1.setChoice2("reading");
        item1.setChoice3("art");
        item1.setChoice4("music");
        item1.setAnswer("art");

        item2.setQuestion("The author describes Mrs Hilbert as a?");
        item2.setChoice1("nice lady");
        item2.setChoice2("wonderful artist");
        item2.setChoice3("sweet person");
        item2.setChoice4("beautiful person");
        item2.setAnswer("wonderful artist");

        item3.setQuestion("What did Mrs.Hilbert teach the class to do?");
        item3.setChoice1("bake chocolate cake");
        item3.setChoice2("write great stories");
        item3.setChoice3("mix paint");
        item3.setChoice4("do long division");
        item3.setAnswer("mix paint");

        item4.setQuestion("Why does it help this student to see the works of other artists?");
        item4.setChoice1("to learn to draw");
        item4.setChoice2("mix paint");
        item4.setChoice3("to think about what she wants to paint");
        item4.setChoice4("none of these");
        item4.setAnswer("to think about what she wants to paint");

        item5.setQuestion("Where will the art exhibit be?");
        item5.setChoice1("at the library");
        item5.setChoice2("at one of the banks");
        item5.setChoice3("at the school");
        item5.setChoice4("at one of the churches");
        item5.setAnswer("at one of the banks");

        item6.setQuestion("What does this student hope will happen with her paintings?");
        item6.setChoice1("one of them will be sold");
        item6.setChoice2("one of them will be chosen for the exhibit");
        item6.setChoice3("one of them will be of her younger sister");
        item6.setChoice4("one of them will be lost");
        item6.setAnswer("one of them will be chosen for the exhibit");

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

        Intent intent = getIntent();
        result = intent.getParcelableExtra("result");
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
                    } else {
                    Toast.makeText(SpeechTask.this, "WRONG", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                    }
                   // itemcounter++;
                } else{
                    result.setComprehensionScore(mScore);
                    Intent intent = new Intent(SpeechTask.this, StartImageGame.class);
                    intent.putExtra("result", result);

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

        if(isInitialized == true)
        {
            itemcounter++;
            mButtonChoice1.setText(temp.getChoice1());
            mButtonChoice2.setText(temp.getChoice2());
            mButtonChoice3.setText(temp.getChoice3());
            mButtonChoice4.setText(temp.getChoice4());
        }else{
            mButtonChoice1.setText(temp.getChoice1());
            mButtonChoice2.setText(temp.getChoice2());
            mButtonChoice3.setText(temp.getChoice3());
            mButtonChoice4.setText(temp.getChoice4());
            isInitialized = true;
        }




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
