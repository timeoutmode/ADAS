package com.example.adas.GuessingObjects;

import androidx.appcompat.app.AppCompatActivity;

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

public class GuessTheImage extends AppCompatActivity {

    Button button;
    EditText editText;
    ImageView img;

    List<GameModel> list;
    Random r;
    int score = 0;
    int wrong = 0;
    int turn = 1;
    int cl = 1;
    int len;
    int func = 1;
    Handler handler;
    boolean isCorrect = false;
    int attampt = 0;
    boolean isCliked = false;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_image);

        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        button = findViewById(R.id.button_submit);
        editText = findViewById(R.id.guessImageTextView);
        img = findViewById(R.id.ImageView);


        handler = new Handler();


        r = new Random();

        list = new ArrayList<>();

        for (int i = 0; i < new ImageDatabase().imageList.length; i++) {
            list.add(new GameModel(new ImageDatabase().imageList[i], new ImageDatabase().clues[i]));
        }


        //Shuffle the data
        Collections.shuffle(list);
        newQuestion(turn);



    }

    private void newQuestion(int number) {

        img.setImageResource(list.get(number - 1).getImage());


    }

    private void image(){

        if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.bed).getConstantState())) {

            if (editText.getText().toString().equalsIgnoreCase("bed") ||
                    editText.getText().toString().equalsIgnoreCase("berth") ||
                    editText.getText().toString().equalsIgnoreCase("billet")||
                    editText.getText().toString().equalsIgnoreCase("kip")){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();
                score = score + 1;
                // addToFirebase();

                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;


//             addToFirebase();
                d();


            }



//            check("bed");
//            check("couch");
//            check("berth");
//            check("billet");
//            check("the sack");
//            check("the hay");
//            check("kip");
//

        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.comb).getConstantState())) {
            if (editText.getText().toString().equalsIgnoreCase("comb") ||
                    editText.getText().toString().equalsIgnoreCase("groom") ||
                    editText.getText().toString().equalsIgnoreCase("untangle")||
                    editText.getText().toString().equalsIgnoreCase("rake")){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();
                isCorrect = true;
                score = score + 1;
                // addToFirebase();


                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {

                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;

                //isCorrect = false;
//                addToFirebase();
                d();


            }


//            check("comb");
//            check("groom");
//            check("untangle");
//            check("disentangle");
//            check("rake");
//            check("tidy");
//



        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.flower).getConstantState())) {

            if (editText.getText().toString().equalsIgnoreCase("flower") ||
                    editText.getText().toString().equalsIgnoreCase("bloom") ||
                    editText.getText().toString().equalsIgnoreCase("blossom")||
                    editText.getText().toString().equalsIgnoreCase("floret")){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();
                isCorrect = true;
                score = score + 1;
                // addToFirebase();


                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;


                d();


            }
            //check("flower");
//            check("bloom");
//            check("blossom");
//            check("floweret");
//            check("floret");




        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.harmonica).getConstantState())) {
            if (editText.getText().toString().equalsIgnoreCase("harmonica") ||
                    editText.getText().toString().equalsIgnoreCase("mouth organ") ||
                    editText.getText().toString().equalsIgnoreCase("mouth harp")||
                    editText.getText().toString().equalsIgnoreCase("Hobo Harp")){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();
                score = score + 1;
                //  addToFirebase();
                isCorrect = true;

                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;

                d();


            }
//            check("harmonica");
//            check("mouth organ");
//            check("mouth harp");
//            check("Hobo Harp");
//            check("French harp");
//            check("Reckless Tram");
//            check("harpoon");
//            check("blues harp");
//            check("Mississippi saxophone");
//            check(" simply harp");






        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.mask).getConstantState())) {
            if (editText.getText().toString().equalsIgnoreCase("mask") ||
                    editText.getText().toString().equalsIgnoreCase("false face") ||
                    editText.getText().toString().equalsIgnoreCase("disguise")||
                    editText.getText().toString().equalsIgnoreCase("vail")){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();
                score = score + 1;
                // addToFirebase();
                isCorrect = true;


                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;

                d();



            }
//            check("mask");
//            check("false face");
//            check("disguise");
//            check("vail");
//            check("domino");
//            check("stocking mask");
//            check("fancy dress");
//            check("visor");
//            check("vizard");
//            check("hide");
//            check("conceal");
//            check("cover up");
//            check("obscure");
//            check("vizard");



        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.pencil).getConstantState())) {
            if (editText.getText().toString().equalsIgnoreCase("pencil") ){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();
                score = score + 1;
                isCorrect = true;
                // addToFirebase();


                nextTurn();

            }else if ( editText.getText().toString().equalsIgnoreCase("write") ||
                    editText.getText().toString().equalsIgnoreCase("write down")||
                    editText.getText().toString().equalsIgnoreCase("jot down")) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;

                d();

            }

            //////////////// Continue adding the words....
//            check("pencil");
//            check("write");if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues()))
//            check("write down");
//            check("jot down");
//            check("take down");


        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.scissors).getConstantState())) {

            if (editText.getText().toString().equalsIgnoreCase("scissors") ||
                    editText.getText().toString().equalsIgnoreCase("shears") ||
                    editText.getText().toString().equalsIgnoreCase("rippers")||
                    editText.getText().toString().equalsIgnoreCase("shears")){
                editText.getText().clear();
                score = score + 1;
                isCorrect = true;
                // addToFirebase();


                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;
                d();


            }
//            check("scissors");

//            check("shears");
//            check("rippers");
//            check("pruner");
//            check("shears");
//            check("snips");
//            check("hedge");
//            check("clippers");


        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.stethoscope).getConstantState())) {
            if (editText.getText().toString().equalsIgnoreCase("stethoscope")){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();
                score = score + 1;
                // addToFirebase();
                isCorrect = true;

                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;


                d();

            }



        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.rattle).getConstantState())) {
            if (editText.getText().toString().equalsIgnoreCase("rattle") ){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();
                score = score + 1;
                isCorrect = true;
                //  addToFirebase();

                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase("clatter") ||
                    editText.getText().toString().equalsIgnoreCase("bang")||
                    editText.getText().toString().equalsIgnoreCase("clang") ||
                    editText.getText().toString().equalsIgnoreCase("clink") ||
                    editText.getText().toString().equalsIgnoreCase("jingle")||
                    editText.getText().toString().equalsIgnoreCase("tinkel")

            ) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;
                d();



            }
//            check("rattle");
//            check("clatter");
//            check("bang");
//            check("clang");
//            check("clink");
//            check("clunk");
//            check("jingle");
//            check("clatter");
//            check("jangle");
//            check("tinkel");


        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.tong).getConstantState())) {
            if (editText.getText().toString().equalsIgnoreCase("tong") ||
                    editText.getText().toString().equalsIgnoreCase("pinchers") ||
                    editText.getText().toString().equalsIgnoreCase("pincers")||
                    editText.getText().toString().equalsIgnoreCase("pliers")){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();
                score = score + 1;
                isCorrect = true;
                // addToFirebase();


                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;



                d();


            }
//            check("tong");
//            check("pinchers");
//            check("pincers")=]
//            check("pliers");
//            check("forceps");
//            check("blacksmith's tongs");


        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.wallet).getConstantState())) {
            if (editText.getText().toString().equalsIgnoreCase("wallet") ||
                    editText.getText().toString().equalsIgnoreCase("billfold") ||
                    editText.getText().toString().equalsIgnoreCase("notecase")||
                    editText.getText().toString().equalsIgnoreCase("purse")){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                editText.getText().clear();
                score = score + 1;
                isCorrect = true;
                //addToFirebase();



                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();



                isCorrect = false;





                wrong = wrong + 1;
                d();


            }
//            check("wallet");
//            check("billfold");
//            check("notecase");
//            check("purse");
//            check("pouch");
//            check("pocketbook");



        }else  if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.whistle).getConstantState())) {
            if (editText.getText().toString().equalsIgnoreCase("whistle")){
                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
                // addToFirebase();
                editText.getText().clear();
                score = score + 1;
                isCorrect = true;
                //addToFirebase();


                nextTurn();

            }else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
                isCorrect = false;
                wrong = wrong + 1;


                d();


                // addToFirebase();

                //   isCorrect = false;

                // delay();






                // addToFirebase();



            }



        }
    }


    private void d(){


        handler.postDelayed(new Runnable() {
            public void run() {


                // yourMethod();
                Toast.makeText(GuessTheImage.this, list.get(turn - 1).getClues(), Toast.LENGTH_LONG).show();

            }
        }, 7000);



        handler.postDelayed(new Runnable() {
            public void run() {



                if (turn < list.size()) {
                    turn++;
                    newQuestion(turn);
                    //  addToFirebase();

                } else {


                    addToFirebase();
                    Intent intent = new Intent(GuessTheImage.this, NamingFingers.class);
                    startActivity(intent);

                }
            }






        }, 20000);




//
//
//        Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_LONG).show();
//
//        handler.postDelayed(new Runnable() {
//            public void run() {
//
//
//                // yourMethod();
//                Toast.makeText(MainActivity.this, list.get(turn - 1).getClues(), Toast.LENGTH_LONG).show();
//
//            }
//        }, 7000);
//
//
//
//            handler.postDelayed(new Runnable() {
//                public void run() {
//
//                    if (turn < list.size()) {
//                        turn++;
//                        newQuestion(turn);
//                       addToFirebase();
//
//                    } else {
//
//
//                        addToFirebase();
//                        Intent intent = new Intent(MainActivity.this, NamingFingers.class);
//                        startActivity(intent);
//
//                    }
//
//
//                }
//
//
//
//            }, 20000);








    }

    public void buttonClicked(View view) {
        image();
    }

    private void nextTurn(){
        if (turn < list.size()) {
            turn++;
            newQuestion(turn);
            addToFirebase();

        } else {
            Toast.makeText(GuessTheImage.this, "You are done", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(MainActivity.this, HigestScoreActivity.class);
//                intent.putExtra("Total Score", score);
//                startActivity(intent);

            addToFirebase();
            Intent intent = new Intent(GuessTheImage.this, NamingFingers.class);
            startActivity(intent);

        }

    }

    private void addToFirebase(){



        Score_1 score_1 = new Score_1(score);
        myRef.child("users").child("Score").child("right").setValue(score_1);



//        Wrong_1 wrong_1 = new Wrong_1(wrong);
//        myRef.child("users").child("Score").child("wrong").setValue(wrong_1);
    }
}
