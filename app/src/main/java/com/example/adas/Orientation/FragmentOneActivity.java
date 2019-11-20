package com.example.adas.Orientation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.example.adas.R;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Locale;

public class FragmentOneActivity extends Fragment {
    Spinner dayOfWeek;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment_one, container, false);

        dayOfWeek = view.findViewById(R.id.dayOfWeek);
        String text = dayOfWeek.getSelectedItem().toString();

        Calendar nowCalendar = Calendar.getInstance();
        String dayLongName = nowCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        if(text.toLowerCase().equals(dayLongName.toLowerCase())) {

        }




        return view;
    }

}
