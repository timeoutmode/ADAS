package com.example.adas.Orientation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adas.Model.User;
import com.example.adas.R;
import com.example.adas.SpeechComprehension.SpeechTask;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FragmentFiveActivity extends Fragment {

    Button button;
    View view;
    TextView place;
    private OrientationViewPager activity;
    FirebaseAuth firebaseAuth;
    private User user;
    FirebaseFirestore db;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_fragment_five, container, false);
        activity = (OrientationViewPager) getActivity();
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        button = view.findViewById(R.id.submit5);
        place = view.findViewById(R.id.tv_place);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String place1 = place.getText().toString();

                String actualPlace = "";
                if(user != null) {
                    actualPlace = user.getAssessmentPlace();
                }
                if(place1.equals( "test"))
                {
                    activity.score++;
                }

                Intent intent = new Intent(getActivity(), SpeechTask.class);
                if(activity.result != null) {
                    activity.result.setOrientationScore(activity.score);
                    intent.putExtra("result", activity.result);
                }
                startActivity(intent);

            }
        });
        return view;

    }

    private void initialiseCurrentUser() {
        DocumentReference ref = db.collection("users").document(firebaseAuth.getUid());
        ref.get().addOnSuccessListener(documentSnapshot -> {
            user =  documentSnapshot.toObject(User.class);
        });
    }


}
