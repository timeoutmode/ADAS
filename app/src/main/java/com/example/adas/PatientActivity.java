package com.example.adas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.adas.Adapter.PatientAdapter;
import com.example.adas.Model.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity {
    private static final String TAG = "PatientActivity";
    private ImageView ivAddPatient;
    private EditText etSearch;
    private List<Patient>  patientList;

    // RecyclerView objects
    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        initialiseObjects();
        setOnClickListeners();
    }

    private void initialiseObjects() {
        ivAddPatient = findViewById(R.id.iv_add_patient);
        etSearch = findViewById(R.id.et_search);

        mRecycleView = findViewById(R.id.recylcer_view);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        getPatientList();
    }

    private void getPatientList() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        CollectionReference ref = db.collection("users").document(auth.getCurrentUser().getUid()).collection("patientList");

        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                patientList = new ArrayList<>();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Patient patient = document.toObject(Patient.class);
                        Log.e(TAG, patient.getFirstName());
                        patientList.add(patient);

                    }
                    // set recycler view adapter
                    mAdapter = new PatientAdapter(patientList);
                    mRecycleView.setLayoutManager(mLayoutManager);
                    mRecycleView.setAdapter(mAdapter);

                }
            }
        });

    }

    private void setOnClickListeners() {
        ivAddPatient.setOnClickListener(c -> {
            Intent intent = new Intent(this, AddPatientActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        // refresh the screen to show new vehicles
        getPatientList();
    }

}
