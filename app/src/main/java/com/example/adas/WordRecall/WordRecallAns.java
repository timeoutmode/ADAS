package com.example.adas.WordRecall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.util.Log;
import android.widget.TextView;
import com.example.adas.R;

import java.util.ArrayList;

public class WordRecallAns extends AppCompatActivity {

    ArrayList<Integer> scoreArray = new ArrayList<Integer>();
    ArrayList<String> selected = new ArrayList<String>();
   // CheckBox CB1, CB2, CB3, CB4, CB5, CB6, CB7, CB8, CB9, CB10;
    Button sbmit;
    TextView txt;
    int count,total=0,trial =0, res1,res2, res3, finalans=0,sum = 0;


     final String TAG = WordRecallAns.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_recall_ans);

        sbmit = findViewById(R.id.btnsub);
        txt = findViewById(R.id.textView3);
        txt.setEnabled(false);
        System.out.println("TAG =" + TAG );

        Intent activity1 = getIntent();
        trial = activity1.getIntExtra("TRIAL",0);
        res1 = activity1.getIntExtra("RES1",0);
        res2 = activity1.getIntExtra("RES2",0);
        res3 = activity1.getIntExtra("RES3",0);


        sbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trial != 4) {
                    if (trial == 1) {
                        res1 = count;
                        Log.e(TAG, "result 1: " + count);
                        Intent next = new Intent(WordRecallAns.this, WordRecall.class);
                        next.putExtra("TRIAL", (trial));
                        next.putExtra("RES1",(res1));
                        startActivity(next);
                    } else if (trial == 2) {
                        res2 = count;
                        Log.e(TAG, "result 2: " + count);
                        Intent next = new Intent(WordRecallAns.this, WordRecall.class);
                        next.putExtra("TRIAL", (trial));
                        next.putExtra("RES2",(res2));
                        startActivity(next);
                    } else if (trial == 3) {
                        res3 = count;
                        Log.e(TAG, "result 3: " + count);
                        Intent next1 = new Intent(WordRecallAns.this, WordRecallResults.class);
                        next1.putExtra("TOTAL", (total));
                        next1.putExtra("TRIAL", (trial));
                        next1.putExtra("RES3",(res3));
                        startActivity(next1);

                    }
                }
            }
        });

    }


    public void correctAns(View view){
        boolean checked = ((CheckBox) view) .isChecked();
            switch (view.getId())
            {
                case R.id.checkBoxBottle:
                    if (checked){
                    selected.add("Bottle");
                    count ++;
                    total = count + 0;}
                    else{
                         selected.remove("Bottle");
                        count --;
                         total = count + 0;
                    }
                    break;
                case R.id.checkBoxPotato:
                if (checked){
                    selected.add("Potato");
                    count ++;
                    total = count + 0;}
                else{
                    selected.remove("Potato");
                    count --;
                    total = count + 0;
                }
                break;
                case R.id.checkBoxGirl:
                    if (checked){
                        selected.add("Girl");
                        count ++;
                        total = count + 0;}
                    else{
                        selected.remove("Girl");
                        count --;
                        total = count + 0;
                    }
                    break;
                case R.id.checkBoxTemple:
                    if (checked){
                        selected.add("Temple");
                        count ++;
                        total = count + 0;}
                    else{
                        selected.remove("Temple");
                        count --;
                        total = count + 0;
                    }
                    break;
                case R.id.checkBoxStar:
                    if (checked){
                        selected.add("Star");
                        count ++;
                        total = count + 0;}
                    else{
                        selected.remove("Star");
                        count --;
                        total = count + 0;
                    }
                    break;
                case R.id.checkBoxAnimal:
                    if (checked){
                        selected.add("Animal");
                        count ++;
                        total = count + 0;}
                    else{
                        selected.remove("Animal");
                        count --;
                        total = count + 0;
                    }
                    break;
                case R.id.checkBoxForest:
                if (checked){
                    selected.add("Forest");
                    count ++;
                    total = count + 0;}
                else{
                    selected.remove("Forest");
                    count --;
                    total = count + 0;
                }
                break;
                case R.id.checkBoxLake:
                    if (checked){
                        selected.add("Lake");
                        count ++;
                        total = count + 0;}
                    else{
                        selected.remove("Lake");
                        count --;
                        total = count + 0;
                    }
                    break;
                case R.id.checkBoxClock:
                    if (checked){
                        selected.add("Clock");
                        count ++;
                        total = count + 0;}
                    else{
                        selected.remove("Clock");
                        count --;
                        total = count + 0;
                    }
                    break;
                case R.id.checkBoxOffice:
                    if (checked){
                        selected.add("Office");
                        count ++;
                        total = count + 0;}
                    else{
                        selected.remove("Office");
                        count --;
                        total = count + 0;
                    }
                    break;


            }


    }
        public void finalAnswer(View view){

        String final_correct_ans = "Score:" + (total);
          txt.setText(total);
          txt.setEnabled(true);

    }
}
