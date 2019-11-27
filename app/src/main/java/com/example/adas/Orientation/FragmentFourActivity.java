package com.example.adas.Orientation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.R;

import java.util.Calendar;

public class FragmentFourActivity extends Fragment {

    Button button;
    View view;
    TextView firstName, lastName;
    private int oScore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment_four, container, false);
        button = view.findViewById(R.id.submit4);
        firstName = view.findViewById(R.id.tv_name);
        lastName = view.findViewById(R.id.tv_name1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = firstName.getText().toString();
                String name2 = lastName.getText().toString();

                //test is only a place holder
                if(name1.equals( "test") && name2.equals("test"))
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
