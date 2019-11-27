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

public class FrgamentThreeActivity extends Fragment {

    Button button;
    Spinner month;
    View view;
    private OrientationViewPager activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_frgament_three, container, false);
        button = view.findViewById(R.id.submit3);
        month = view.findViewById(R.id.month_spinner);
        String text = month.getSelectedItem().toString();

        Calendar nowCalendar = Calendar.getInstance();
        String dayLongName = nowCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        if(text.toLowerCase().equals(dayLongName.toLowerCase())) {
        }

        activity = (OrientationViewPager) getActivity();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chosenMonth = month.getSelectedItemPosition() -1;
                Calendar c = Calendar.getInstance();
                int month = c.get(Calendar.MONTH);

                if(month == chosenMonth)
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
