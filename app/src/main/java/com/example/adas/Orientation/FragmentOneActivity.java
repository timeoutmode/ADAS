package com.example.adas.Orientation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.Model.Result;
import com.example.adas.R;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Locale;
import com.example.adas.Orientation.OrientationViewPager;
import com.google.android.material.tabs.TabLayout;

public class FragmentOneActivity extends Fragment {

    Spinner dayOfWeek;
    View view;
    Button button;
    Result result;
    OrientationViewPager activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment_one, container, false);

        initialiseObjects();
        setOnClickListeners();


//        String text = dayOfWeek.getSelectedItem().toString();
//
//        Calendar nowCalendar = Calendar.getInstance();
//        String dayLongName = nowCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        if(text.toLowerCase().equals(dayLongName.toLowerCase())) {
//
//        }


        return view;
    }

    private void initialiseObjects() {
        button = view.findViewById(R.id.submit);
        dayOfWeek = view.findViewById(R.id.dayOfWeek);

        //get activity
        activity = (OrientationViewPager) getActivity();
    }

    private void setOnClickListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //removed -1
                int chosenDay = dayOfWeek.getSelectedItemPosition();
                Calendar c = Calendar.getInstance();
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

                if(chosenDay == dayOfWeek) {
                    activity.score++;
                }

                // move to next fragment
                int index = activity.pager.getCurrentItem();
                activity.pager.setCurrentItem(++index);
            }
        });
    }
}
