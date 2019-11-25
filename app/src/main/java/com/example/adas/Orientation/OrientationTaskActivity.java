package com.example.adas.Orientation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.adas.R;

public class OrientationTaskActivity extends AppCompatActivity {
    Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_orientation);

        // Spinner Drop Down
        //Spinner spinner = findViewById(R.id.et_week);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        //      R.array.week, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        mainButton = findViewById(R.id.mainButton);


    }


    public void SelectFrag(View view) {

        Fragment fr;

        if (view == findViewById(R.id.mainButton))

            fr = new FragmentOneActivity();

        else
            fr = new FragmentTwo();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fr);
        transaction.commit();

    }
}
