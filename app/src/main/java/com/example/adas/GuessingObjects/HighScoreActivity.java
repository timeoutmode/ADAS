package com.example.adas.GuessingObjects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.adas.Model.Result;
import com.example.adas.Model.Score_1;
import com.example.adas.Model.Score_2;
import com.example.adas.Model.TotalScore;
import com.example.adas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HighScoreActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private CollectionReference collectionReference;
    private DocumentReference documentReference;

    TextView imageRight, fingersRight,totalRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);


        initialiseFirebase();
        initialiseViews();
        totalRight  = findViewById(R.id.totalscore);
       // loadData();

        Intent intent = getIntent();
        Result result = intent.getParcelableExtra("result");

        int namingScore = result.getNamingScore();

        totalRight.setText(String.valueOf(namingScore));



    }


//    private void loadData(){
//        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//        CollectionReference imageScoreRef = db.collection("users").document(uid).collection("Images_Scores");
//        imageScoreRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                if (e != null){
//                    return;
//                }
//                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
//
//                    Score_1 score1 = documentSnapshot.toObject(Score_1.class);
//                    int imgScore = score1.getImageScroing();
//                    imageRight.setText(Integer.toString(imgScore));
//
//                }
//
//            }
//        });
//
//
//
//        CollectionReference fingerScoreRef = db.collection("users").document(uid).collection("Fingers_Scores");
//        fingerScoreRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                if (e != null){
//                    return;
//                }
//                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
//
//                    Score_2 score2 = documentSnapshot.toObject(Score_2.class);
//                    int finScore = score2.getFingerScoreing();
//                    fingersRight.setText(Integer.toString(finScore));
//
//                }
//
//            }
//        });
//
//
//        //Calculating the total answer
//        CollectionReference totalScoreRef = db.collection("users").document(uid).collection("Total_Scores");
//
//        totalScoreRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                if (e != null){
//                    return;
//                }
//                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
//
//                    TotalScore totalScore = documentSnapshot.toObject(TotalScore.class);
//                    int finScore =totalScore.getTotalScoreing();
//                    totalRight.setText(Integer.toString(finScore));
//
//                }
//
//            }
//        });
//
//
//
//    }

    private void initialiseViews(){
        imageRight = findViewById(R.id.imageright);
        fingersRight = findViewById(R.id.fingersright);
         }

    private void initialiseFirebase(){
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }


}
