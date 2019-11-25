package com.example.adas.GuessingObjects;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

    List<FingerModel> myList;
    Random r;

    int turn = 1;
    int score = 0;
    Handler handler;
    int attamp = 3;
    int wrong = 0;

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naming_fingers);

        button = findViewById(R.id.button_submit);
        editText = findViewById(R.id.fingerImageTextView);
        img = findViewById(R.id.fingerImageView);
        r = new Random();

        myList = new ArrayList<>();
        handler = new Handler();

        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();


        for (int i = 0; i < new FingerDatabase().ans.length; i++) {
            myList.add(new FingerModel(new FingerDatabase().ans[i], new FingerDatabase().fingerImages[i]));
        }


        Collections.shuffle(myList);
        nextQuestion(turn);


    }


    private void nextQuestion(int number) {
        img.setImageResource(myList.get(number - 1).getImage());
    }


    public void button_submit(View view) {





        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(NamingFingers.this, "Cannot leave blank", Toast.LENGTH_LONG).show();


        } else {


            if (editText.getText().toString().equalsIgnoreCase(myList.get(turn - 1).getName())) {
                Toast.makeText(NamingFingers.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();

                score = score + 1;


                    if (turn < myList.size()) {
                    turn++;
                    nextQuestion(turn);
                   addToFirebase();

                } else {
                    addToFirebase();
                    Toast.makeText(NamingFingers.this, "You are done", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NamingFingers.this, HigestScoreActivity.class);
                    startActivity(intent);

                }


            }


            else  {

                Toast.makeText(NamingFingers.this, "Incorrect", Toast.LENGTH_LONG).show();
                wrong = wrong + 1;
                delay();


//                if (attamp > 3){
//                    Toast.makeText(NamingFingers.this, "What is another name for this finger", Toast.LENGTH_LONG).show();
//
//                    addToFirebase();
//                }
//
//                attamp++;


            }


        }


        //  attamp++;
    }

    private void delay(){
        handler.postDelayed(new Runnable() {
            public void run() {


                // yourMethod();
                Toast.makeText(NamingFingers.this, "What is another name for this finger", Toast.LENGTH_LONG).show();


            }
        }, 7000);


        handler.postDelayed(new Runnable() {
            public void run() {


                // yourMethod();


                if (turn < myList.size()) {
                    turn++;
                    nextQuestion(turn);
                    addToFirebase();


                } else {
                    Toast.makeText(NamingFingers.this, "You have finished ", Toast.LENGTH_LONG).show();

                    // wrong = wrong + 1;
                    //  addToFirebase();
//                        Intent intent = new Intent(MainActivity.this, HigestScoreActivity.class);
////                        intent.putExtra("Total Score", score);
////                        startActivity(intent);

                    Intent intent = new Intent(NamingFingers.this, HigestScoreActivity.class);
                    startActivity(intent);

                }
            }
        }, 20000);

    }

    private void addToFirebaseWrong(){

    }

    private void addToFirebase(){


       Score_2 score_2 = new Score_2(score);
//       Wrong_2 wrong_2 = new Wrong_2(wrong);


        myRef.child("users").child("Score_2").child("right").setValue(score_2);
       // myRef.child("users").child("Score_2").child("wrong").setValue(wrong_2);

        //child(id).child("Goals").child("Steps").setValue(issues);



    }

}














