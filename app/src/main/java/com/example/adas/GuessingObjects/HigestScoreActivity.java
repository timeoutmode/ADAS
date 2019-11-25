package com.example.adas.GuessingObjects;

import android.os.Bundle;
import android.widget.TextView;

import com.example.adas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HigestScoreActivity extends AppCompatActivity {

    int highscore = 0;

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higest_score);

        final TextView imageRight = findViewById(R.id.imageright);
        final TextView imageWrong = findViewById(R.id.imagewrong);
        final TextView fingersRight = findViewById(R.id.fingersright);
        final TextView fingersWrong = findViewById(R.id.fingerswrong);
        final TextView totalRight  = findViewById(R.id.totalscore);
        final TextView totalwrong = findViewById(R.id.totalwrong);

//        Intent intent = getIntent();
//        int score = intent.getIntExtra("Total Score", 0);

        //txtCurrentScore.setText("Your Score: "+  score);

        //child("users").child("Score").setValue(score);

        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();


        Query query1 = myRef.child("users");
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String score_1 = dataSnapshot.child("Score").child("imageScroing").getValue().toString();
                String score_2 = dataSnapshot.child("Score_2").child("fingerScore").getValue().toString();
                String imgWrong = dataSnapshot.child("Score").child("wrong").child("answerWrong_1:").getValue().toString();
                String fingWrong  = dataSnapshot.child("Score_2").child("wrong").child("answerWrong").getValue().toString();

                //  myRef.child("users").child("Score_2").child("wrong").setValue(wrong_2);
                imageRight.setText(score_1);
                imageWrong.setText(imgWrong);
                fingersRight.setText(score_2);
                fingersWrong.setText(fingWrong);

                totalRight.setText(score_1 + score_2);
                totalwrong.setText(imgWrong + fingWrong);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

                //child("users").child("Score").setValue(score);


    }


}
