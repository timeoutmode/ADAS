package com.example.adas.Orientation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.R;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Locale;
import com.example.adas.Orientation.OrientationViewPager;

public class FragmentOneActivity extends Fragment {

    Spinner dayOfWeek;
    View view;
    Button button;
    private int oScore;
    private TextView oScoreView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment_one, container, false);

        button = view.findViewById(R.id.submit);
        dayOfWeek = view.findViewById(R.id.dayOfWeek);
        String text = dayOfWeek.getSelectedItem().toString();

        Calendar nowCalendar = Calendar.getInstance();
        String dayLongName = nowCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        if(text.toLowerCase().equals(dayLongName.toLowerCase())) {

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //removed -1
                int chosenDay = dayOfWeek.getSelectedItemPosition();
                Calendar c = Calendar.getInstance();
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                //int minDay = chosenDay -1;
                //int maxDay = chosenDay +1;

                //if(dayOfWeek >= minDay && dayOfWeek <= maxDay)
                if(dayOfWeek == chosenDay)
                {
                    Toast.makeText(getActivity(),"Correct!",Toast.LENGTH_SHORT).show();
                    oScore ++;
                }

                else
                {
                    Toast.makeText(getActivity(),"Sorry not correct!",Toast.LENGTH_SHORT).show();
                }

            }
        });




        return view;
    }


}
