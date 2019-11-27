package com.example.adas.Orientation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adas.R;

import java.util.Calendar;
import java.util.Locale;

public class FragmentSixActivity extends Fragment {

    View view;
    Button button;
    Spinner hour;
    private int oScore;
    private  OrientationViewPager activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment_six, container, false);
        activity = (OrientationViewPager) getActivity();
        button = view.findViewById(R.id.submit6);
        hour = view.findViewById(R.id.hour_spinner);
        String text = hour.getSelectedItem().toString();

        Calendar rightNow = Calendar.getInstance();
        int hours = rightNow.get(Calendar.HOUR_OF_DAY);

        if(text.toLowerCase().equals(rightNow)) {
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chosenHour = hour.getSelectedItemPosition() ;
                Calendar c = Calendar.getInstance();
                int hrs = c.get(Calendar.HOUR_OF_DAY);
                int minHour = chosenHour -1;
                int maxHour = chosenHour +1;

                if( hrs >= minHour && hrs <= maxHour)
                {
                    activity.score++;
                }

                int index = activity.pager.getCurrentItem();
                activity.pager.setCurrentItem(++index);

            }
        });
        return view;

    }



}
