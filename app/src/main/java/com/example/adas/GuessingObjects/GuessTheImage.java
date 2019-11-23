package com.example.adas.GuessingObjects;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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


import com.example.adas.HomeActivity;
import com.example.adas.Model.ImageQuestion;
import com.example.adas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GuessTheImage extends AppCompatActivity {

    TextView displayClue;
    Button button;
    EditText editText;
    ImageView img;



    int score = 0;

    int turn = 1;
    int counter = 0;
    int length;

    Handler handler;


    private ArrayList<ImageQuestion> imageQuestionArrayList;







    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    int sum = 0;
     FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_image);


        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        button = findViewById(R.id.button_submit);
        editText = findViewById(R.id.guessImageTextView);
        img = findViewById(R.id.ImageView);
        displayClue = findViewById(R.id.displayClue);


        handler = new Handler();






//        for (int i = 0; i < new ImageDatabase().imageList.length; i++) {
//            list.add(new GameModel(new ImageDatabase().imageList[i], new ImageDatabase().clues[i]));
//        }


        //Shuffle the data
//        Collections.shuffle(list);



        initialiseImages();
        length = imageQuestionArrayList.size();
        newQuestion();


    }


    private void newQuestion() {

       //img.setImageResource(list.get(number - 1).getImage());
        if(counter < length) {
            Log.e("NewQuestion", "Called");
            ImageQuestion temp = imageQuestionArrayList.get(counter);
            displayClue.setText("");
            img.setImageResource(temp.getImageid());
        } else {


            //When all th questions are finished
            arletinstructions();


            // message that it's finished
            Log.e("NewQuestion", "Counter > Length");
        }

    }


    private void arletinstructions(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(GuessTheImage.this, R.style.MyDialogTheme);
        alertDialog.setTitle("Guess the Finger");
        alertDialog.setMessage("“Now I am going to point to a part of your hand and I want\n" +
                         "you to tell me what it’s called.");

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(GuessTheImage.this, NamingFingers.class);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(GuessTheImage.this, HomeActivity.class);
                startActivity(intent);

            }
        });


        alertDialog.create().show();

    }

    private void initialiseImages() {
        ImageQuestion image1 = new ImageQuestion();
        ImageQuestion image2 = new ImageQuestion();
        ImageQuestion image3 = new ImageQuestion();
        ImageQuestion image4 = new ImageQuestion();
        ImageQuestion image5 = new ImageQuestion();
        ImageQuestion image6 = new ImageQuestion();
        ImageQuestion image7 = new ImageQuestion();
        ImageQuestion image8 = new ImageQuestion();
        ImageQuestion image9 = new ImageQuestion();
        ImageQuestion image10 = new ImageQuestion();
        ImageQuestion image11 = new ImageQuestion();
        ImageQuestion image12 = new ImageQuestion();

        //
        image1.setImageid(R.drawable.bed);

        image1.setAnswerList(new String[] {
                "bed", "berth", "billet", "kip"
        });

        image1.setClue("Used for sleeping in");
        image1.setFunctionList(new String[] {
                "used for sleeping in"
        });


        image2.setImageid(R.drawable.comb);
        image2.setAnswerList(new String[] {
                "comb", "groom", "untangle", "rake"
        });
        image2.setClue("Used on hair");
        image2.setFunctionList(new String[] {
                "comb hair"
        });



        image3.setImageid(R.drawable.flower);
        image3.setAnswerList(new String[] {
                "flower", "bloom", "blossom", "floret"
        });
        image3.setClue("Grows in a garden");
        image3.setFunctionList(new String[] {
                "grows in a garden"
        });



        image4.setImageid(R.drawable.harmonica);
        image4.setAnswerList(new String[] {
                "harmonica", "mouth organ", "mouth harp", "Hobo Harp"
        });
        image4.setClue("A musical instrument");
        image4.setFunctionList(new String[] {
                "play music"
        });

        image5.setImageid(R.drawable.mask);
        image5.setAnswerList(new String[] {
                "mask", "false face", "disguise", "vail"
        });
        image5.setClue("Hides your face");
        image5.setFunctionList(new String[] {
                "Hides your face"
        });

        image6.setImageid(R.drawable.pencil);
        image6.setAnswerList(new String[] {
                "pencil"
        });
        image6.setClue("Used for writing");
        image6.setFunctionList(new String[] {
                "write","write down","jot down"
        });

        image7.setImageid(R.drawable.scissors);
        image7.setAnswerList(new String[] {
                "scissors","shears","rippers","shears"
        });
        image7.setClue("Cuts pape");
        image7.setFunctionList(new String[] {
                "Cuts things"
        });

        image8.setImageid(R.drawable.stethoscope);
        image8.setAnswerList(new String[] {
                "stethoscope"
        });
        image8.setClue("Doctor uses it to listen to your hear");
        image8.setFunctionList(new String[] {
                "listen to your hear"
        });

        image9.setImageid(R.drawable.tong);
        image9.setAnswerList(new String[] {
                "tong","pinchers", "pincers"
        });
        image9.setClue("Picks up food");
        image9.setFunctionList(new String[] {
                "Picks up food"
        });

        image10.setImageid(R.drawable.wallet);
        image10.setAnswerList(new String[] {
                "wallet","billfold", "notecase", "purse"
        });
        image10.setClue("Holds your money");
        image10.setFunctionList(new String[] {
                "Holds your money"
        });

        image11.setImageid(R.drawable.whistle);
        image11.setAnswerList(new String[] {
                "whistle"
        });
        image11.setClue("Makes a sound when you blow on it");
        image11.setFunctionList(new String[] {
                "Makes a sound when you blow on it"
        });

        image12.setImageid(R.drawable.rattle);
        image12.setAnswerList(new String[] {
                "rattle"
        });
        image12.setClue("A baby’s toy");
        image12.setFunctionList(new String[] {
                "A baby’s toy"
        });



        imageQuestionArrayList = new ArrayList<>();
        imageQuestionArrayList.add(image1);
        imageQuestionArrayList.add(image2);
        imageQuestionArrayList.add(image3);
        imageQuestionArrayList.add(image4);
        imageQuestionArrayList.add(image5);
        imageQuestionArrayList.add(image6);
        imageQuestionArrayList.add(image7);
        imageQuestionArrayList.add(image8);
        imageQuestionArrayList.add(image9);
        imageQuestionArrayList.add(image10);
        imageQuestionArrayList.add(image11);
        imageQuestionArrayList.add(image12);
        Collections.shuffle(imageQuestionArrayList);

    }



    private void image() {
        ImageQuestion currentImage = imageQuestionArrayList.get(counter);
        String answer = editText.getText().toString().toLowerCase();
        if(currentImage.checkAnswer(answer)) {
            editText.setText("");
            score++;
            counter++;
            newQuestion();
            Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
            handler.removeCallbacksAndMessages(null);
        } else if (currentImage.checkClue(answer)) {

            Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name?", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
            showClue();
        }

    }




    private void showClue() {


        handler.postDelayed(new Runnable() {
            public void run() {


                // yourMethod();
                //Toast.makeText(GuessTheImage.this, list.get(turn - 1).getClues(), Toast.LENGTH_LONG).show();
                ImageQuestion temp = imageQuestionArrayList.get(counter);
               // Toast.makeText(GuessTheImage.this, temp.getClue(), Toast.LENGTH_LONG).show();
                displayClue.setText(temp.getClue());

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
                newQuestion();
            }

        }, 10000);

    }


    public void buttonClicked(View view) {
        image();
    }

//    private void nextTurn(){
//        if (turn < list.size()) {
//            turn++;
//            newQuestion();
//            addToFirebase();
//
//        } else {
//            Toast.makeText(GuessTheImage.this, "You are done", Toast.LENGTH_LONG).show();
////                Intent intent = new Intent(MainActivity.this, HigestScoreActivity.class);
////                intent.putExtra("Total Score", score);
////                startActivity(intent);
//
//            addToFirebase();
//            Intent intent = new Intent(GuessTheImage.this, NamingFingers.class);
//            startActivity(intent);
//
//        }
//
//    }

    private void addToFirebase(){

        Score_1 score_1 = new Score_1(score);
        myRef.child("users").child("Score").child("right").setValue(score_1);

    }
}
