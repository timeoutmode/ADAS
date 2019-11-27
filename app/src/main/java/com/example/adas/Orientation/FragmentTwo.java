package com.example.adas.Orientation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.adas.R;

public class FragmentTwo extends Fragment {

    Button button;
    View view;
    Spinner season;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment_two, container, false);
        button = view.findViewById(R.id.submit3);
        season = view.findViewById(R.id.season_spinner);
        String text = season.getSelectedItem().toString();




        return view;

    }


}
