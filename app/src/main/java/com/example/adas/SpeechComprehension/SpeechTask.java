package com.example.adas.SpeechComprehension;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.R;

import java.util.Locale;

public class SpeechTask extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private ImageButton mQuestionView;
    private RadioButton mButtonChoice1;
    private RadioButton mButtonChoice2;
    private RadioButton mButtonChoice3;
    private RadioButton mButtonChoice4;
    private Button mSubmitButton;
    TextToSpeech tts;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_task);

        mScoreView = (TextView) findViewById(R.id.score);
        mQuestionView = (ImageButton) findViewById((R.id.question));
        mButtonChoice1 = (RadioButton) findViewById(R.id.choice1);
        mButtonChoice2 = (RadioButton) findViewById(R.id.choice2);
        mButtonChoice3 = (RadioButton) findViewById(R.id.choice3);
        mButtonChoice4 = (RadioButton) findViewById(R.id.choice4);
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        updateQuestion();



        mSubmitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
               switch(mQuestionNumber)
               {
                   case 1:
                   {
                       if (mButtonChoice1.isChecked())
                       {
                           mScore = mScore + 1;
                           updateScore(mScore);
                           updateQuestion();
                       }
                       else
                       {
                           Toast.makeText(SpeechTask.this, "WRONG", Toast.LENGTH_SHORT).show();
                           updateQuestion();
                       }
                   }
                   break;
                   case 2:
                   {
                       if (mButtonChoice2.isChecked())
                       {
                           mScore = mScore + 1;
                           updateScore(mScore);
                           updateQuestion();
                       }
                       else
                       {
                           Toast.makeText(SpeechTask.this, "WRONG", Toast.LENGTH_SHORT).show();
                           updateQuestion();
                       }
                   }
                   break;
                   case 3:
                   {
                       if (mButtonChoice3.isChecked())
                       {
                           mScore = mScore + 1;
                           updateScore(mScore);
                           updateQuestion();
                       }
                       else
                       {
                           Toast.makeText(SpeechTask.this, "WRONG", Toast.LENGTH_SHORT).show();
                           updateQuestion();
                       }
                   }
                   break;
                   case 4:
                   {
                       if (mButtonChoice4.isChecked())
                       {
                           mScore = mScore + 1;
                           updateScore(mScore);
                           updateQuestion();
                       }
                       else
                       {
                           Toast.makeText(SpeechTask.this, "WRONG", Toast.LENGTH_SHORT).show();
                           updateQuestion();
                       }
                   }
                   break;



               }
            }
        });
       /* //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mButtonChoice1.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //Message
                    Toast.makeText(SpeechTask.this, "correct", Toast.LENGTH_SHORT).show();

                } else {
                    //message
                    Toast.makeText(SpeechTask.this, "WRONG", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });
        // End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mButtonChoice2.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //Message
                    Toast.makeText(SpeechTask.this, "correct", Toast.LENGTH_SHORT).show();

                } else {
                    //message
                    Toast.makeText(SpeechTask.this, "WRONG", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });
        // End of Button Listener for Button2

        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mButtonChoice3.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //Message
                    Toast.makeText(SpeechTask.this, "correct", Toast.LENGTH_SHORT).show();

                } else {
                    //message
                    Toast.makeText(SpeechTask.this, "WRONG", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });
        // End of Button Listener for Button3

        //Start of Button Listener for Button4
        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mButtonChoice4.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();


                } else {

                    Toast.makeText(SpeechTask.this, "WRONG", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });
        // End of Button Listener for Button4*/

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


        mQuestionView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    tts.speak(mQuestionLibrary.getQuestion(mQuestionNumber-1), TextToSpeech.QUEUE_FLUSH,null,null);
                }
                else
                {
                    tts.speak(mQuestionLibrary.getQuestion(mQuestionNumber-1), TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
        mButtonChoice4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));

        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
        mButtonChoice1.setChecked(false);
        mButtonChoice2.setChecked(false);
        mButtonChoice3.setChecked(false);
        mButtonChoice4.setChecked(false);
    }

    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }
}
