package com.example.adas.IdeationalPraxis;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adas.R;

public class IdeationalPraxisActivity extends AppCompatActivity {

    private ImageView helpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideational_praxis);

        initialiseObjects();
    }

    private void initialiseObjects()
    {
        helpBtn = findViewById(R.id.instruction_btn);
    }

    private void setOnClickListeners()
    {
        helpBtn.setOnClickListener(v -> {

        });
    }
}