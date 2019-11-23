package com.example.adas.GuessingObjects;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.Model.FingerQuestions;
import com.example.adas.Model.ImageQuestion;
import com.example.adas.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class NamingFingers extends AppCompatActivity {

    Button button;
    ImageView img;
    EditText editText;
    TextView displyQuery;

    List<FingerModel> myList;
    Random r;


    int score = 0;
    Handler handler;
    int counter = 0;

   int len;

    FirebaseDatabase database;
    DatabaseReference myRef;

    private ArrayList<FingerQuestions> fingerQuestionsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naming_fingers);

        button = findViewById(R.id.button_submit);
        editText = findViewById(R.id.fingerImageTextView);
        img = findViewById(R.id.fingerImageView);
        displyQuery = findViewById(R.id.displyQuery);

        r = new Random();

        myList = new ArrayList<>();
        handler = new Handler();

        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();


        initialiseImages();
        len = fingerQuestionsArrayList.size();
        nextQuestion();



//        for (int i = 0; i < new FingerDatabase().ans.length; i++) {
//            myList.add(new FingerModel(new FingerDatabase().ans[i], new FingerDatabase().fingerImages[i]));
//        }



//        Collections.shuffle(myList);
//        nextQuestion(turn);


    }


    private void initialiseImages(){
        FingerQuestions image1 = new FingerQuestions();
        FingerQuestions image2 = new FingerQuestions();
        FingerQuestions image3 = new FingerQuestions();
        FingerQuestions image4 = new FingerQuestions();
        FingerQuestions image5 = new FingerQuestions();

        image1.setImageId(R.drawable.thumb);
        image1.setAnsList(new String[]{
                "Thumb"
        });
        image1.setClue("What is another name for this finger");


        image2.setImageId(R.drawable.index);
        image2.setAnsList(new String[]{
                "Index","forefinger", "pointer"
        });
        image2.setClue("What is another name for this finger");

        image3.setImageId(R.drawable.middel);
        image3.setAnsList(new String[]{
                "middle"
        });
        image3.setClue("What is another name for this finger");


        image4.setImageId(R.drawable.ring);
        image4.setAnsList(new String[]{
                "ring"
        });
        image4.setClue("What is another name for this finger");

        image5.setImageId(R.drawable.pinky);
        image5.setAnsList(new String[]{
                "pinky"
        });
        image5.setClue("What is another name for this finger");

         fingerQuestionsArrayList = new ArrayList<>();
         fingerQuestionsArrayList.add(image1);
         fingerQuestionsArrayList.add(image2);
         fingerQuestionsArrayList.add(image3);
         fingerQuestionsArrayList.add(image4);
         fingerQuestionsArrayList.add(image5);
         Collections.shuffle(fingerQuestionsArrayList);


    }


    private void nextQuestion() {
//        img.setImageResource(myList.get(number - 1).getImage());
        if(counter < len) {
            Log.e("NewQuestion", "Called");
           FingerQuestions temp = fingerQuestionsArrayList.get(counter);
            displyQuery.setText("");
            img.setImageResource(temp.getImageId());
        } else {
            //When all th questions are finished
            Intent intent = new Intent(NamingFingers.this, HigestScoreActivity.class);
            startActivity(intent);



            // message that it's finished
            Log.e("NewQuestion", "Counter > Length");
        }
    }


    public void button_submit(View view) {

        image();


        //  attamp++;
    }

//

    private void image() {
        FingerQuestions currentFinger = fingerQuestionsArrayList.get(counter);
        String answer = editText.getText().toString().toLowerCase();
        if(currentFinger.checkAnswer(answer)) {
            editText.setText("");
            score++;
            counter++;
            nextQuestion();
            Toast.makeText(NamingFingers.this, "Correct", Toast.LENGTH_LONG).show();
            handler.removeCallbacksAndMessages(null);
        } else if (currentFinger.checkClue(answer)) {

            Toast.makeText(NamingFingers.this, "Yes that is the function, but what is the name?", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(NamingFingers.this, "Incorrect", Toast.LENGTH_LONG).show();
            showClue();
        }

    }

    private void showClue() {


        handler.postDelayed(new Runnable() {
            public void run() {


                // yourMethod();
                //Toast.makeText(GuessTheImage.this, list.get(turn - 1).getClues(), Toast.LENGTH_LONG).show();
               FingerQuestions temp = fingerQuestionsArrayList.get(counter);
                // Toast.makeText(GuessTheImage.this, temp.getClue(), Toast.LENGTH_LONG).show();
                displyQuery .setText(temp.getClue());

                moveToNextQuestion();

            }
        }, 3000);


    }


    private void moveToNextQuestion() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //nextTurn();
                Log.e("D2", "Called");
                counter++;
                nextQuestion();
            }

        }, 10000);

    }

    private void addToFirebase(){


       Score_2 score_2 = new Score_2(score);
//       Wrong_2 wrong_2 = new Wrong_2(wrong);


        myRef.child("users").child("Score_2").child("right").setValue(score_2);
       // myRef.child("users").child("Score_2").child("wrong").setValue(wrong_2);

        //child(id).child("Goals").child("Steps").setValue(issues);



    }

}














