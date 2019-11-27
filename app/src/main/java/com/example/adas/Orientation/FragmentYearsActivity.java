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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentYearsActivity extends Fragment {

    View view;
    Button button;
    Spinner year1;
    private int oScore;
    private OrientationViewPager activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment_years, container, false);
        activity = (OrientationViewPager) getActivity();

        button = view.findViewById(R.id.submit7);
        year1 = view.findViewById(R.id.year_spinner);
        String text = year1.getSelectedItem().toString();

        Calendar rightNow = Calendar.getInstance();
        int years = rightNow.get(Calendar.YEAR);

        //Calendar calendar = Calendar.getInstance();
        //int year = calendar.get(Calendar.YEAR);

        //Calendar nowCalendar = Calendar.getInstance();
        //Date dayLongName = nowCalendar.getTime();
        //if(text.toLowerCase().equals(dayLongName)) {
        //}


        Calendar nowCalendar = Calendar.getInstance();
        String dayLongName = nowCalendar.getDisplayName(Calendar.YEAR, Calendar.LONG, Locale.getDefault());
        if(text.equals(dayLongName)) {

        }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat simple = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
                    // year in yyyy format
                    simple = new SimpleDateFormat("yyyy");
                    String strYear = simple.format(new Date());
                    int chosenYear = year1.getSelectedItemPosition() -1;
                    //Calendar c = Calendar.getInstance();
                    //int yrs = c.get(Calendar.YEAR);

                    if (strYear.equals(chosenYear)) {
                        activity.score++;
                    }


                    int index = activity.pager.getCurrentItem();
                    activity.pager.setCurrentItem(++index);

                }
            });

        return view;


    }



}
