package com.example.adas.GuessingObjects;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.Model.FingerQuestions;
import com.example.adas.Model.Patient;
import com.example.adas.Model.Result;
import com.example.adas.Model.Score_2;
import com.example.adas.Model.TotalScore;
import com.example.adas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class NamingFingers extends AppCompatActivity  {

    private ArrayList<FingerQuestions> fingerQuestionsArrayList;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    Button button;
    ImageView img;
    EditText editText;
    TextView displyQuery;
    Handler handler;
    Random r;
    Result result;


    int score  = 0;
    int counter = 0;
    int totalScore = 0;
    int wrongS ;
    int actualScroe = 0;


    int len;
    int s;
    int sc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naming_fingers);


        Intent intent = getIntent();
        result = intent.getParcelableExtra("result");
        Log.e("Score", String.valueOf(result.getNamingScore()));


        initialiseViews();
        initialise();
        initialiseFirebase();
        //receiveintentData();

        initialiseImages();
        len = fingerQuestionsArrayList.size();
        nextQuestion();

        //Receiving score from image guessing activity
//        Bundle b = getIntent().getExtras();
//        s = b.getInt("ImageScore");













    }

    private void initialiseViews(){
        button = findViewById(R.id.button_submit);
        editText = findViewById(R.id.fingerImageTextView);
        img = findViewById(R.id.fingerImageView);
        displyQuery = findViewById(R.id.displyQuery);
    }

    private void initialise(){
        r = new Random();
        handler = new Handler();
    }
    private void initialiseFirebase(){
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

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





    public void button_submit(View view) {

        image();

    }



    private void image() {
        FingerQuestions currentFinger = fingerQuestionsArrayList.get(counter);
        String answer = editText.getText().toString().toLowerCase();
        if(currentFinger.checkAnswer(answer)) {
            editText.setText("");
            score = score + 1;
             // adding score from images and fingers together

          //   totalScore = s + score;

           //  totalScore = result.getNamingScore() + score;
              sc = result.getNamingScore();

            totalScore = sc + score;
            Log.e("TotalScore", String.valueOf(totalScore));

            if(totalScore >= 15 && totalScore <= 17) {
                actualScroe = 5;

            }else if (totalScore >= 12 && totalScore <= 14){
                actualScroe = 4;
            }else if (totalScore >= 9 && totalScore <= 11 ){
                actualScroe = 3;
            }else if (totalScore >= 6 && totalScore <= 8 ){
                actualScroe = 2;
            }else if (totalScore >= 3 && totalScore <= 5 ){
                actualScroe = 1;
            }else if (totalScore >= 0 && totalScore <= 2 ){
                actualScroe = 0;
            }





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

    private void nextQuestion() {
//        img.setImageResource(myList.get(number - 1).getImage());
        if(counter < len) {
            Log.e("NewQuestion", "Called");
            FingerQuestions temp = fingerQuestionsArrayList.get(counter);
            displyQuery.setText("");
            img.setImageResource(temp.getImageId());
        } else {
            //When all th questions are finished

            Result result = new Result();
           //int totalScore = result.getNamingScore() + score;


            result.setNamingScore(actualScroe);
            Patient patient = new Patient();
            result.setPatient(patient);
            result.getPatient().setFirstName("Brian");
            Intent intent = new Intent(NamingFingers.this, HighScoreActivity.class);
            intent.putExtra("result", result);
            startActivity(intent);




             // startActivity(intent);
            // message that it's finished
            Log.e("NewQuestion", "Counter > Length");
        }
    }

    private void showClue() {


        handler.postDelayed(new Runnable() {
            public void run() {
               FingerQuestions temp = fingerQuestionsArrayList.get(counter);
                displyQuery .setText(temp.getClue());
                moveToNextQuestion();
            }
        }, 3000);

    }


    private void moveToNextQuestion() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("D2", "Called");
                counter++;
                nextQuestion();
                editText.getText().clear();





            }

        }, 1000);

    }//10000

    private void addToFirebase(){



        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference uidRef = db.collection("users").document(uid);


        Score_2 fingerScore = new Score_2();
        fingerScore.setFingerScoreing(score);
//        fingerScore.setFingerScore(score);

        //uidRef.collection("Naming_Task_Scores").document("Fingers_Scores").set(fingerScore);

        uidRef.collection("Fingers_Scores").document("Scores").set(fingerScore);
                //document("Fingers_Scores").set(fingerScore);
        //add(ImageScore);


    }

    private void addTotalToFirebase(){



        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference uidRef = db.collection("users").document(uid);

        int total = s + score;
        TotalScore totalScore = new TotalScore();
        totalScore.setTotalScoreing(total);

        uidRef.collection("Total_Scores").document("Scores").set(totalScore);

    }



}














