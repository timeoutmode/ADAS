package com.example.adas.Orientation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.adas.R;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FragmentTwo extends Fragment {

private RadioGroup radioGroup;
private RadioButton choice1, choice2, choice3, choice4;


    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) { //values of radio buttons


                String monthSeasons[] = {
                        "Winter", "Winter",
                        "Spring", "Spring", "Spring",
                        "Summer", "Summer", "Summer",
                        "Fall", "Fall", "Fall",
                        "Winter"
                };

                java.util.Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH);// gets current month



                    int selectedSeason = radioGroup.getCheckedRadioButtonId();
                    RadioButton radiobutton = radioGroup.findViewById(selectedSeason);
                    String tempAnswer = radiobutton.getText().toString();//gets selecvted answer from radio button
                    String rightAnswer = monthSeasons[month];//gets current season

                    // should be connected to next button to submit answer
                    if(tempAnswer.equals(rightAnswer))// compares picked answer from radiobutton from answer
                    {
                        // score++
                        // next fragment
                    }
                    else
                    {
                        //next fragment
                    }


            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_two, container, false);


    }

}
