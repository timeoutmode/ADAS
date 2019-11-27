package com.example.adas.WordRecall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.util.Log;
import android.widget.TextView;

import com.example.adas.IdeationalPraxis.IdeationalPraxisActivity;
import com.example.adas.Model.Result;
import com.example.adas.R;

import java.util.ArrayList;

public class WordRecallAns extends AppCompatActivity {

    ArrayList<Integer> scoreArray = new ArrayList<Integer>();
    ArrayList<String> selected = new ArrayList<String>();
    CheckBox cbBottle, cbPotato, cbGirl, cbTemple, cbStar, cbAnimal, cbOffice, cbForest, cbLake, cbClock;
    CheckBox[] chkGroup;
    Button sbmit;
    TextView txt;
    int count,total=0,trial =0, res1,res2, res3, finalans=0,sum = 0;
    final String TAG = WordRecallAns.class.getSimpleName();

    // tracks the number of correct answer
    private int correctAnswer = 0;
    private ArrayList<String> wordBankArray;
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_recall_ans);


        System.out.println("TAG =" + TAG );

        initialiseObjects();
        initialiseWordBank();
        setOnClickListeners();

        Intent intent = getIntent();
        trial = intent.getIntExtra("trial",0);
        correctAnswer = intent.getIntExtra("correctAnswer", 0);
        if(intent.hasExtra("result")) {
            result = intent.getParcelableExtra("result");
            Log.e("WORDRECALLRESULT", "result");
        }

//        sbmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (trial != 4) {
//                    if (trial == 1) {
//                        res1 = count;
//                        Log.e(TAG, "result 1: " + count);
//                        Intent next = new Intent(WordRecallAns.this, WordRecall.class);
//                        next.putExtra("TRIAL", (trial));
//                        next.putExtra("RES1",(res1));
//                        startActivity(next);
//                    } else if (trial == 2) {
//                        res2 = count;
//                        Log.e(TAG, "result 2: " + count);
//                        Intent next = new Intent(WordRecallAns.this, WordRecall.class);
//                        next.putExtra("TRIAL", (trial));
//                        next.putExtra("RES2",(res2));
//                        startActivity(next);
//                    } else if (trial == 3) {
//                        res3 = count;
//                        Log.e(TAG, "result 3: " + count);
//                        Intent next1 = new Intent(WordRecallAns.this, WordRecallResults.class);
//                        next1.putExtra("TOTAL", (total));
//                        next1.putExtra("TRIAL", (trial));
//                        next1.putExtra("RES3",(res3));
//                        startActivity(next1);
//
//                    }
//                }
//            }
//        });

    }

    private void initialiseObjects() {
        sbmit = findViewById(R.id.btnsub);

        cbBottle = findViewById(R.id.checkBoxBottle);
        cbPotato = findViewById(R.id.checkBoxPotato);
        cbGirl = findViewById(R.id.checkBoxGirl);
        cbTemple = findViewById(R.id.checkBoxTemple);
        cbStar = findViewById(R.id.checkBoxStar);
        cbAnimal = findViewById(R.id.checkBoxAnimal);
        cbOffice = findViewById(R.id.checkBoxOffice);
        cbForest = findViewById(R.id.checkBoxForest);
        cbLake = findViewById(R.id.checkBoxLake);
        cbClock = findViewById(R.id.checkBoxClock);

        chkGroup = new CheckBox[]{
                cbBottle,
                cbPotato,
                cbGirl,
                cbTemple,
                cbStar,
                cbAnimal,
                cbOffice,
                cbForest,
                cbLake,
                cbClock
        };
    }

    private void setOnClickListeners() {
        for(CheckBox cb : chkGroup) {
            cb.setOnClickListener(c -> {
                String word = cb.getText().toString().toLowerCase();
                if(cb.isChecked()) {
                    if(wordBankArray.contains(word)) {
                        correctAnswer++;
                    }
                } else {
                    if(wordBankArray.contains(word)) {
                        // minus score if patients decides to change his/her answer
                        correctAnswer--;
                    }
                }
            });
        }

        sbmit.setOnClickListener(c -> {
            if(trial < 2) {
                Intent intent = new Intent(WordRecallAns.this, WordRecall.class);
                intent.putExtra("correctAnswer", correctAnswer);
                if(result != null) {
                    intent.putExtra("result", result);
                }
                intent.putExtra("trial", ++trial);
                startActivity(intent);
            } else {
                // calculate total score
                int numberOfWordsNotRecalled = 30 - correctAnswer;
                int mean = numberOfWordsNotRecalled/3;

                // go to next activity
                Intent intent = new Intent(WordRecallAns.this, IdeationalPraxisActivity.class);
                if(result != null) {
                    result.setWordRecallScore(mean);
                    intent.putExtra("result", result);
                }
                startActivity(intent);
            }
        });
    }

    private void initialiseWordBank() {
        wordBankArray = new ArrayList<>();
        wordBankArray.add("bottle");
        wordBankArray.add("potato");
        wordBankArray.add("girl");
        wordBankArray.add("temple");
        wordBankArray.add("star");
        wordBankArray.add("animal");
        wordBankArray.add("forest");
        wordBankArray.add("lake");
        wordBankArray.add("clock");
        wordBankArray.add("office");
    }


//    public void correctAns(View view){
//        boolean checked = ((CheckBox) view) .isChecked();
//            switch (view.getId())
//            {
//                case R.id.checkBoxBottle:
//                    if (checked){
//                    selected.add("Bottle");
//                    count ++;
//                    total = count + 0;}
//                    else{
//                         selected.remove("Bottle");
//                        count --;
//                         total = count + 0;
//                    }
//                    break;
//                case R.id.checkBoxPotato:
//                if (checked){
//                    selected.add("Potato");
//                    count ++;
//                    total = count + 0;}
//                else{
//                    selected.remove("Potato");
//                    count --;
//                    total = count + 0;
//                }
//                break;
//                case R.id.checkBoxGirl:
//                    if (checked){
//                        selected.add("Girl");
//                        count ++;
//                        total = count + 0;}
//                    else{
//                        selected.remove("Girl");
//                        count --;
//                        total = count + 0;
//                    }
//                    break;
//                case R.id.checkBoxTemple:
//                    if (checked){
//                        selected.add("Temple");
//                        count ++;
//                        total = count + 0;}
//                    else{
//                        selected.remove("Temple");
//                        count --;
//                        total = count + 0;
//                    }
//                    break;
//                case R.id.checkBoxStar:
//                    if (checked){
//                        selected.add("Star");
//                        count ++;
//                        total = count + 0;}
//                    else{
//                        selected.remove("Star");
//                        count --;
//                        total = count + 0;
//                    }
//                    break;
//                case R.id.checkBoxAnimal:
//                    if (checked){
//                        selected.add("Animal");
//                        count ++;
//                        total = count + 0;}
//                    else{
//                        selected.remove("Animal");
//                        count --;
//                        total = count + 0;
//                    }
//                    break;
//                case R.id.checkBoxForest:
//                if (checked){
//                    selected.add("Forest");
//                    count ++;
//                    total = count + 0;}
//                else{
//                    selected.remove("Forest");
//                    count --;
//                    total = count + 0;
//                }
//                break;
//                case R.id.checkBoxLake:
//                    if (checked){
//                        selected.add("Lake");
//                        count ++;
//                        total = count + 0;}
//                    else{
//                        selected.remove("Lake");
//                        count --;
//                        total = count + 0;
//                    }
//                    break;
//                case R.id.checkBoxClock:
//                    if (checked){
//                        selected.add("Clock");
//                        count ++;
//                        total = count + 0;}
//                    else{
//                        selected.remove("Clock");
//                        count --;
//                        total = count + 0;
//                    }
//                    break;
//                case R.id.checkBoxOffice:
//                    if (checked){
//                        selected.add("Office");
//                        count ++;
//                        total = count + 0;}
//                    else{
//                        selected.remove("Office");
//                        count --;
//                        total = count + 0;
//                    }
//                    break;
//
//
//            }
//
//
//    }
//        public void finalAnswer(View view){
//
//        String final_correct_ans = "Score:" + (total);
//          txt.setText(total);
//          txt.setEnabled(true);
//
//    }
}
