package com.example.adas.Orientation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.R;

public class FragmentFiveActivity extends Fragment {

    Button button;
    View view;
    TextView place;
    private int oScore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment_five, container, false);

        button = view.findViewById(R.id.submit5);
        place = view.findViewById(R.id.tv_place);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String place1 = place.getText().toString();

                //test is only a place holder
                if(place1.equals( "test"))
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
