package com.example.adas.Orientation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.adas.Model.Result;
import com.example.adas.R;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class OrientationViewPager extends AppCompatActivity {

    public ViewPager pager;
    private PagerAdapter pagerAdapter;
    private Button mainButton;
    private Spinner spinner;
    private int counter = 1;
    public TextView oScoreView;
    public int score = 0;
    private static final String TAG = "OrientationActivity";
    public Result result;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        List<Fragment> list = new ArrayList <>();
        list.add(new FragmentOneActivity());
        list.add(new FrgamentThreeActivity());
        list.add(new FragmentYearsActivity());
        list.add(new FragmentSixActivity());
        list.add(new FragmentTwo());
        list.add(new FragmentFourActivity());
        list.add(new FragmentFiveActivity());


        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(pagerAdapter);


        pager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });


        // get intent
        Intent intent = getIntent();
        if(intent.hasExtra("result")) {
            result = intent.getParcelableExtra("result");
        }


    }
}
