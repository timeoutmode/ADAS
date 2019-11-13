package com.example.adas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.libraries.places.api.Places;

import java.util.Objects;


public class OrientationActivity extends AppCompatActivity {

    private static final String TAG = "OrientationTaskActivity";
    private EditText etPersonName, etPlaceName, etTime,etDate, etYear;
    private Spinner etWeek, etSeason, etMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);

        initialiseObjects();
        setOnClickListeners();

        // Spinner Drop Down
        //Spinner spinner = findViewById(R.id.et_week);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
          //      R.array.week, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }

    private void initialiseObjects() {
        etWeek = findViewById(R.id.et_week);
        etMonth = findViewById(R.id.et_month);
        etSeason = findViewById(R.id.et_season);
        etPersonName = findViewById(R.id.et_person_name);
        etPlaceName = findViewById(R.id.et_place_name);
        etTime = findViewById(R.id.et_time_of_day);
        etDate = findViewById(R.id.et_date);
        etYear = findViewById(R.id.et_year);

        // set lists of string for dropdowns
        String[] weekList = getResources().getStringArray(R.array.week);
        String[] seasonList = getResources().getStringArray(R.array.season);
        String[] monthList = getResources().getStringArray(R.array.month);


        etMonth.setAdapter(createAdapter(monthList));
        etSeason.setAdapter(createAdapter(seasonList));
        etWeek.setAdapter(createAdapter(weekList));

        // initialise Places SDK
        Places.initialize(getApplicationContext(), "AIzaSyDTdQwtiqUknkl9HkEU_YdvuGjyGNDlChY");

    }

    private void setOnClickListeners() {
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override public void onNothingSelected(AdapterView<?> adapterView) { }
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0) {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                } else {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.GRAY);
                }
            }
        };
        etMonth.setOnItemSelectedListener(itemSelectedListener);
        etSeason.setOnItemSelectedListener(itemSelectedListener);
        etWeek.setOnItemSelectedListener(itemSelectedListener);
    }

    // creates a spinner adapter for  the drop down menus
    private SpinnerAdapter createAdapter(String[] list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getBaseContext()), android.R.layout.simple_spinner_dropdown_item,list ) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }

            @Override
            public boolean isEnabled(int position) {
                // disables hint
                return position != 0;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }



//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}