package com.example.adas.WordRecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.adas.DelayedRecall.DelayedRecallAns;
import com.example.adas.DelayedRecall.DelayedRecallResults;
import com.example.adas.R;

import java.util.ArrayList;

public class WordRecogAns extends AppCompatActivity {

    ArrayList<String> selected = new ArrayList<String>();

    Button sbmit;
    TextView txt;
    int count = 0, total = 0;

    final String TAG = WordRecogAns.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_recog_ans);

        sbmit = findViewById(R.id.btnsub);
        txt = findViewById(R.id.textView3);
        txt.setEnabled(false);
        System.out.println("TAG =" + TAG);

        sbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finalAnswer(v);
                Log.e(TAG, "running " + total);
                Intent next = new Intent(WordRecogAns.this, DelayedRecallResults.class);
                next.putExtra("COUNT", String.valueOf(total));
                startActivity(next);
            }
        });


    }

    public void correctAns(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            //This is INCLUDED in the previous list
            case R.id.checkBoxCost:
                if (checked) {
                    selected.add("COST");
                    count++;
                    total = count + 0;
                } else {
                    selected.remove("COST");
                    count--;
                    total = count + 0;
                }
                break;
                //This is NOT included in the previous list
            case R.id.checkBoxNation:
                if (checked) {
                    selected.add("NATION");
                    count--;
                    total = count + 0;
                } else {
                    selected.remove("NATION");
                    count++;
                    total = count + 0;
                }
                break;
            //This is INCLUDED in the previous list
            case R.id.checkBoxChimney:
                if (checked) {
                    selected.add("CHIMNEY");
                    count++;
                    total = count + 0;
                } else {
                    selected.remove("CHIMNEY");
                    count--;
                    total = count + 0;
                }
                break;
            //This is NOT included in the previous list
            case R.id.checkBoxSparrow:
                if (checked) {
                    selected.add("SPARROW");
                    count--;
                    total = count + 0;
                } else {
                    selected.remove("SPARROW");
                    count++;
                    total = count + 0;
                }
                break;
            //This is INCLUDED in the previous list
            case R.id.checkBoxDamages:
                if (checked) {
                    selected.add("DAMAGES");
                    count++;
                    total = count + 0;
                } else {
                    selected.remove("DAMAGES");
                    count--;
                    total = count + 0;
                }
                break;
            //This is NOT included in the previous list
            case R.id.checkBoxTraffic:
                if (checked) {
                    selected.add("TRAFFIC");
                    count--;
                    total = count + 0;
                } else {
                    selected.remove("TRAFFIC");
                    count++;
                    total = count + 0;
                }
                break;
            //This is INCLUDED in the previous list
            case R.id.checkBoxSandwich:
                if (checked) {
                    selected.add("SANDWICH");
                    count++;
                    total = count + 0;
                } else {
                    selected.remove("SANDWICH");
                    count--;
                    total = count + 0;
                }
                break;
            //This is NOT included in the previous list
            case R.id.checkBoxService:
                if (checked) {
                    selected.add("SERVICE");
                    count--;
                    total = count + 0;
                } else {
                    selected.remove("SERVICE");
                    count++;
                    total = count + 0;
                }
                break;
            //This is NOT included in the previous list
            case R.id.checkBoxShell:
                if (checked) {
                    selected.add("SHELL");
                    count--;
                    total = count + 0;
                } else {
                    selected.remove("SHELL");
                    count++;
                    total = count + 0;
                }
                break;
            //This is INCLUDED in the previous list
            case R.id.checkBoxSolutions:
                if (checked) {
                    selected.add("SOLUTION");
                    count++;
                    total = count + 0;
                } else {
                    selected.remove("SOLUTION");
                    count--;
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