package com.example.adas.Orientation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
    public int oScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        //oScoreView = (TextView) findViewById(R.id.score1);

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

        mainButton = findViewById(R.id.mainButton);
        mainButton.setOnClickListener(c -> {
            pager.setCurrentItem(counter++);
        });

        //spinner = findViewById(R.id.dayOfWeek);

        //DayOfWeek monday = DayOfWeek.MONDAY;


    }
}
