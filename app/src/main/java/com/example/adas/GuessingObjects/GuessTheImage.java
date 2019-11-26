package com.example.adas.GuessingObjects;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adas.Model.ImageQuestion;
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
    int counter = 0;
    int length;

    Handler handler;

    private ArrayList<ImageQuestion> imageQuestionArrayList;

    boolean answer = false;




    GameModel gameModel;
    //FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    int sum = 0;
    // FirebaseUser user;

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

//        for (int i = 0; i < new ImageDatabase().imageList.length; i++) {
//            list.add(new GameModel(new ImageDatabase().imageList[i], new ImageDatabase().clues[i]));
//        }


        //Shuffle the data
        Collections.shuffle(list);



        initialiseImages();
        length = imageQuestionArrayList.size();
        newQuestion();


    }


    private void newQuestion() {

       //img.setImageResource(list.get(number - 1).getImage());
        if(counter < length) {
            Log.e("NewQuestion", "Called");
            ImageQuestion temp = imageQuestionArrayList.get(counter);
            img.setImageResource(temp.getImageid());
        } else {
            // message that it's finished
            Log.e("NewQuestion", "Counter > Length");
        }

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

        imageQuestionArrayList = new ArrayList<>();
        imageQuestionArrayList.add(image1);
        imageQuestionArrayList.add(image2);

    }

    private void image() {
        ImageQuestion currentImage = imageQuestionArrayList.get(counter);
        String answer = editText.getText().toString().toLowerCase();
        if(currentImage.checkAnswer(answer)) {
            editText.setText("");
            score++;
            counter++;
            newQuestion();
            handler.removeCallbacksAndMessages(null);
        } else if (currentImage.checkClue(answer)) {
            Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name?", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
            d();
        }

    }

//    private void image() {
//
//        if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.bed).getConstantState())) {
//
//            if (editText.getText().toString().equalsIgnoreCase("bed") ||
//                    editText.getText().toString().equalsIgnoreCase("berth") ||
//                    editText.getText().toString().equalsIgnoreCase("billet") ||
//                    editText.getText().toString().equalsIgnoreCase("kip")) {
//
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//                // clicked = true;
//                score = score + 1;
//                answer = true;
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//            } else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
//                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("bed") ||
//                    !editText.getText().toString().equalsIgnoreCase("berth") ||
//                    !editText.getText().toString().equalsIgnoreCase("billet") ||
//                    !editText.getText().toString().equalsIgnoreCase("kip")) {
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//
//
//                d();
//            }
//
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.comb).getConstantState())) {
//
//            if (editText.getText().toString().equalsIgnoreCase("comb") ||
//                    editText.getText().toString().equalsIgnoreCase("groom") ||
//                    editText.getText().toString().equalsIgnoreCase("untangle") ||
//                    editText.getText().toString().equalsIgnoreCase("rake")) {
//
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                // clicked = true;
//                editText.getText().clear();
//                answer = true;
//
//                score = score + 1;
//                // addToFirebase();
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//            } else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
//                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("comb") ||
//                    !editText.getText().toString().equalsIgnoreCase("groom") ||
//                    !editText.getText().toString().equalsIgnoreCase("untangle") ||
//                    !editText.getText().toString().equalsIgnoreCase("rake")) {
//
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//
//                d();
//
//            }
//
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.flower).getConstantState())) {
//
//            if (editText.getText().toString().equalsIgnoreCase("flower") ||
//                    editText.getText().toString().equalsIgnoreCase("bloom") ||
//                    editText.getText().toString().equalsIgnoreCase("blossom") ||
//                    editText.getText().toString().equalsIgnoreCase("floret")) {
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                //clicked = true;
//                editText.getText().clear();
//
//                answer = true;
//                score = score + 1;
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//            } else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
//                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("flower") ||
//                    !editText.getText().toString().equalsIgnoreCase("bloom") ||
//                    !editText.getText().toString().equalsIgnoreCase("blossom") ||
//                    !editText.getText().toString().equalsIgnoreCase("floret")) {
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//
//
//                d();
//
//            }
//
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.harmonica).getConstantState())) {
//
//            if (editText.getText().toString().equalsIgnoreCase("harmonica") ||
//                    editText.getText().toString().equalsIgnoreCase("mouth organ") ||
//                    editText.getText().toString().equalsIgnoreCase("mouth harp") ||
//                    editText.getText().toString().equalsIgnoreCase("Hobo Harp")) {
//
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                //clicked = true;
//                editText.getText().clear();
//                score = score + 1;
//                answer = true;
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//            } else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
//                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("harmonica") ||
//                    !editText.getText().toString().equalsIgnoreCase("mouth organ") ||
//                    !editText.getText().toString().equalsIgnoreCase("mouth harp") ||
//                    !editText.getText().toString().equalsIgnoreCase("Hobo Harp")) {
//
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//
//
//                d();
//
//            }
//
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.mask).getConstantState())) {
//
//            if (editText.getText().toString().equalsIgnoreCase("mask") ||
//                    editText.getText().toString().equalsIgnoreCase("false face") ||
//                    editText.getText().toString().equalsIgnoreCase("disguise") ||
//                    editText.getText().toString().equalsIgnoreCase("vail")) {
//
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                //clicked = true;
//                editText.getText().clear();
//                score = score + 1;
//                answer = true;
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//            } else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
//                Toast.makeText(GuessTheImage.this,"Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("mask") ||
//                    !editText.getText().toString().equalsIgnoreCase("false face") ||
//                    !editText.getText().toString().equalsIgnoreCase("disguise") ||
//                    !editText.getText().toString().equalsIgnoreCase("vail")) {
//
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//
//
//                d();
//
//            }
//
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.pencil).getConstantState())) {
//
//            if (!editText.getText().toString().equalsIgnoreCase("pencil")) {
//
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                // clicked = true;
//                editText.getText().clear();
//                score = score + 1;
//                answer = true;
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//            } else if (editText.getText().toString().equalsIgnoreCase("write") ||
//                    editText.getText().toString().equalsIgnoreCase("write down") ||
//                    editText.getText().toString().equalsIgnoreCase("jot down")) {
//                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (editText.getText().toString().equalsIgnoreCase("pencil")) {
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//
//                d();
//
//
//            }
//
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.scissors).getConstantState())) {
//
//            if (editText.getText().toString().equalsIgnoreCase("scissors") ||
//                    editText.getText().toString().equalsIgnoreCase("shears") ||
//                    editText.getText().toString().equalsIgnoreCase("rippers") ||
//                    editText.getText().toString().equalsIgnoreCase("shears")) {
//
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                // clicked = true;
//                editText.getText().clear();
//
//                score = score + 1;
//                answer = true;
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//            } else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
//                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("scissors") ||
//                    !editText.getText().toString().equalsIgnoreCase("shears") ||
//                    !editText.getText().toString().equalsIgnoreCase("rippers") ||
//                    !editText.getText().toString().equalsIgnoreCase("shears")) {
//
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//
//                d();
//
//
//            }
//
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.stethoscope).getConstantState())) {
//
//            if (editText.getText().toString().equalsIgnoreCase("stethoscope")) {
//
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                // clicked = true;
//                editText.getText().clear();
//                score = score + 1;
//                answer = true;
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//            } else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
//                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("stethoscope")) {
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//
//                d();
//
//
//            }
//
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.rattle).getConstantState())) {
//
//            if (editText.getText().toString().equalsIgnoreCase("rattle")) {
//
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                //clicked = true;
//                editText.getText().clear();
//                answer = true;
//                score = score + 1;
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//
//            } else if (editText.getText().toString().equalsIgnoreCase("clatter") ||
//                    editText.getText().toString().equalsIgnoreCase("bang") ||
//                    editText.getText().toString().equalsIgnoreCase("clang") ||
//                    editText.getText().toString().equalsIgnoreCase("clink") ||
//                    editText.getText().toString().equalsIgnoreCase("jingle") ||
//                    editText.getText().toString().equalsIgnoreCase("tinkel")
//
//            ) {
//                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("rattle")) {
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//                d();
//
//
//            }
////
//
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.tong).getConstantState())) {
//            if (editText.getText().toString().equalsIgnoreCase("tong") ||
//                    editText.getText().toString().equalsIgnoreCase("pinchers") ||
//                    editText.getText().toString().equalsIgnoreCase("pincers") ||
//                    editText.getText().toString().equalsIgnoreCase("pliers")) {
//
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                //clicked = true;
//                // editText.getText().clear();
//                score = score + 1;
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//            } else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
//                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("tong") ||
//                    !editText.getText().toString().equalsIgnoreCase("pinchers") ||
//                    !editText.getText().toString().equalsIgnoreCase("pincers") ||
//                    !editText.getText().toString().equalsIgnoreCase("pliers")) {
//
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//
//                d();
//
//
//            }
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.wallet).getConstantState())) {
//
//            if (editText.getText().toString().equalsIgnoreCase("wallet") ||
//                    editText.getText().toString().equalsIgnoreCase("billfold") ||
//                    editText.getText().toString().equalsIgnoreCase("notecase") ||
//                    editText.getText().toString().equalsIgnoreCase("purse")) {
//
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//                // clicked = true;
//                answer = true;
//                score = score + 1;
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//            } else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
//                Toast.makeText(GuessTheImage.this, "Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("wallet") ||
//                    !editText.getText().toString().equalsIgnoreCase("billfold") ||
//                    !editText.getText().toString().equalsIgnoreCase("notecase") ||
//                    !editText.getText().toString().equalsIgnoreCase("purse")) {
//
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//                //answer = false;
//
//
//                d();
//
//
//            }
//
//
//        } else if (img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.whistle).getConstantState())) {
//            if (editText.getText().toString().equalsIgnoreCase("whistle")) {
//                Toast.makeText(GuessTheImage.this, "Correct", Toast.LENGTH_LONG).show();
//                //  clicked = true;
//                answer = true;
//
//                editText.getText().clear();
//                score = score + 1;
//
//
//                nextTurn();
//                handler.removeCallbacksAndMessages(null);
//
//
//            } else if (editText.getText().toString().equalsIgnoreCase(list.get(turn - 1).getClues())) {
//                Toast.makeText(GuessTheImage.this,"Yes that is the function, but what is the name", Toast.LENGTH_LONG).show();
//
//
//            } else if (!editText.getText().toString().equalsIgnoreCase("whistle")) {
//                Toast.makeText(GuessTheImage.this, "Incorrect", Toast.LENGTH_LONG).show();
//                editText.getText().clear();
//                // answer = false;
//
//
//                d();
//
//            }
//        }
//
//
//    }


    private void d() {


        handler.postDelayed(new Runnable() {
            public void run() {


                // yourMethod();
                //Toast.makeText(GuessTheImage.this, list.get(turn - 1).getClues(), Toast.LENGTH_LONG).show();
                ImageQuestion temp = imageQuestionArrayList.get(counter);
                Toast.makeText(GuessTheImage.this, temp.getClue(), Toast.LENGTH_LONG).show();

                d2();

            }
        }, 3000);


    }


    private void d2() {

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

    private void nextTurn(){
        if (turn < list.size()) {
            turn++;
            newQuestion();
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

    }
}
