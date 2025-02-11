package com.example.adas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(c -> {
            FirebaseAuth.getInstance().signOut();
        });
    }
}
