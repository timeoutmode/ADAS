package com.example.adas.Orientation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.Model.Patient;
import com.example.adas.Model.Result;
import com.example.adas.R;

import java.util.Calendar;

public class FragmentFourActivity extends Fragment {

    Button button;
    View view;
    TextView firstName, lastName;
    private int oScore;
    private OrientationViewPager activity;
    private Patient patient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment_four, container, false);
        activity = (OrientationViewPager) getActivity();
        button = view.findViewById(R.id.submit4);
        firstName = view.findViewById(R.id.tv_name);
        lastName = view.findViewById(R.id.tv_name1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = firstName.getText().toString().toLowerCase().trim();
                String name2 = lastName.getText().toString().toLowerCase().trim();


                if(activity.result instanceof Result) {
                    if(activity.result.patient instanceof Patient) {
                        patient = activity.result.getPatient();
                        if(name1.equals(patient.getFirstName().toLowerCase().trim()) && name2.equals(patient.getLastName().toLowerCase().trim()))
                        {
                            activity.score++;
                        }
                    }
                }
                int index = activity.pager.getCurrentItem();
                activity.pager.setCurrentItem(++index);
            }
        });
        return view;
    }


}
