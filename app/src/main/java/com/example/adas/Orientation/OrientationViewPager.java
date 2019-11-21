package com.example.adas.Orientation;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.adas.R;

import java.util.ArrayList;
import java.util.List;

public class OrientationViewPager extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private Button mainButton;
    private int counter = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_viewpager);
        setContentView(R.layout.activity_viewpager);

        List<Fragment> list = new ArrayList <>();
        list.add(new FragmentOneActivity());
        list.add(new FragmentTwo());
        list.add(new FrgamentThreeActivity());
        list.add(new FragmentFourActivity());
        list.add(new FragmentFiveActivity());
        list.add(new FragmentSixActivity());

        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(pagerAdapter);

        mainButton = findViewById(R.id.mainButton);
        mainButton.setOnClickListener(c -> {
            pager.setCurrentItem(counter++);
        });


    }
}
