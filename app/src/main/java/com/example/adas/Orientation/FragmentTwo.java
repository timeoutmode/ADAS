package com.example.adas.Orientation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adas.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentTwo extends Fragment {

//    Button button;
    View view;
//    Spinner season;
   private int oScore;

    private RadioGroup radioGroup;
    private RadioButton choice1, choice2, choice3, choice4;

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

          view = inflater.inflate(R.layout.activity_fragment_two, container, false);
//        Button = view.findViewById(R.id.submit2);
          radioGroup = view.findViewById(R.id.selectedSeason);
//        season = view.findViewById(R.id.season_spinner);
//        String text = season.getSelectedItem().toString();
//
//        Calendar nowCalendar = Calendar.getInstance();
//        String dayLongName = nowCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        if(text.toLowerCase().equals(dayLongName.toLowerCase())) {
//
//        }

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //removed -1
//                int chosenDay = season.getSelectedItemPosition();
//                Calendar c = Calendar.getInstance();
//                int dayOfWeek = c.get(Calendar.MONTH);
//                //int minDay = chosenDay -1;
//                //int maxDay = chosenDay +1;
//
//                //if(dayOfWeek >= minDay && dayOfWeek <= maxDay)
//                switch (chosenDay) {
//                    case 12:
//                    case 1:
//                    case 2:
//                        System.out.println("WINTER");
//                        break;
//                    case 3:
//                    case 4:
//                    case 5:
//                        System.out.println("SPRING");
//                        break;
//                    case 6:
//                    case 7:
//                    case 8:
//                        System.out.println("SUMMER");
//                        break;
//                    case 9:
//                    case 10:
//                    case 11:
//                        System.out.println("AUTUMN");
//                        break;
//                    default:
//                }
//                if(chosenDay == dayOfWeek)
//                {
//                    Toast.makeText(getActivity(),"Correct!",Toast.LENGTH_SHORT).show();
//                    oScore ++;
//                }
//
//                else
//                {
//                    Toast.makeText(getActivity(),"Sorry not correct!",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        return view;
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
                String tempAnswer = radiobutton.getText().toString();//gets selected answer from radio button
                String rightAnswer = monthSeasons[month];//gets current season

                // should be connected to next button to submit answer
                if(tempAnswer.equals(rightAnswer))// compares picked answer from radiobutton from answer
                {
                    Toast.makeText(getActivity(),"Correct!",Toast.LENGTH_SHORT).show();
                    oScore++;
                }
                else
                {
                    //Toast.makeText(getActivity(),"Sorry not correct!",Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;

        return inflater.inflate(R.layout.activity_fragment_two, container, false);



    }


}
