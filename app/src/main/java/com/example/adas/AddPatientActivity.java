package com.example.adas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.adas.Constructional_Praxis.DrawingActivity;
import com.example.adas.Helper.Helpers;
import com.example.adas.Model.Patient;
import com.example.adas.Model.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class AddPatientActivity extends AppCompatActivity {
    private static final String TAG = "AddPatientActivity";
    private static final int REQUEST_CODE = 2019;
    private EditText etFirstName, etLastName, etAddress,etBirthDate;
    private Spinner etEthnicity, etGender;
    private Button btnAddPatient;
    private ProgressBar progressBar;
    private Date birthDate;
    private boolean isAssessment = false;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        initialiseObjects();
        setOnClickListeners();

        if(getIntent().hasExtra("assessment")) {
            isAssessment = true;
        }
    }

    private void initialiseObjects() {
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etAddress = findViewById(R.id.tv_address);
        etEthnicity = findViewById(R.id.et_ethnicicty);
        etGender = findViewById(R.id.et_gender);
        etBirthDate = findViewById(R.id.et_birth_date);
        progressBar = findViewById(R.id.progressBar);
        btnAddPatient = findViewById(R.id.btn_add_patient);

        // set lists of string for dropdowns
        String[] ethnicityList = getResources().getStringArray(R.array.ethnicity);
        String[] genderList = getResources().getStringArray(R.array.gender);

        etEthnicity.setAdapter(createAdapter(ethnicityList));
        etGender.setAdapter(createAdapter(genderList));


        // initialise database objects
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // initialise Places SDK
        Places.initialize(getApplicationContext(), getResources().getString(R.string.places_api_key));
    }

    private void setOnClickListeners() {
        etAddress.setOnClickListener(v -> {
            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)).setCountry("NZ").setTypeFilter(TypeFilter.ADDRESS).build(this);
            startActivityForResult(intent, REQUEST_CODE);
        });

        etBirthDate.setOnClickListener(c -> {
            setDate((EditText)c);
        });

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override public void onNothingSelected(AdapterView<?> adapterView) { }
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0) {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                } else {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.GRAY);
                }
            }
        };
        etGender.setOnItemSelectedListener(itemSelectedListener);
        etEthnicity.setOnItemSelectedListener(itemSelectedListener);

        btnAddPatient.setOnClickListener(c -> {
            String userId = firebaseAuth.getUid();
            DocumentReference ref = db.collection("users").document(userId).collection("patientList").document();

            // create a new patient
            Patient patient = new Patient();
            patient.setPatientID(ref.getId());
            patient.setFirstName(etFirstName.getText().toString());
            patient.setLastName(etLastName.getText().toString());
            patient.setAddress(etAddress.getText().toString());
            patient.setEthnicity(etEthnicity.getSelectedItem().toString());
            patient.setGender(etGender.getSelectedItem().toString());
            patient.setBirthDate(birthDate);

            ref.set(patient)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                            if(isAssessment) {
                                Result result = new Result();
                                result.setPatient(patient);
                                Intent intent = new Intent(AddPatientActivity.this, DrawingActivity.class);
                                intent.putExtra("result", result);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(AddPatientActivity.this, PatientActivity.class);
                                startActivity(intent);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                etAddress.setText(Objects.requireNonNull(place.getAddress()).replace(", New Zealand", ""));
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, Objects.requireNonNull(status.getStatusMessage()));
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private void setDate(EditText et) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(AddPatientActivity.this), createOnDateListener(et), year, month, day);
        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    // create new OnDateSetListener by supplying the text view you want to set
    private DatePickerDialog.OnDateSetListener createOnDateListener(EditText et) {

        return (DatePicker datePicker, int year, int month, int day) -> {
            String date = day + "/" + (month + 1) + "/" + year;

            // derive date from calendar data
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            Date tempDate = c.getTime();

            if (et == etBirthDate) {
                if (tempDate.before(Calendar.getInstance().getTime())) {
                    birthDate = tempDate;
                    et.setText(date);
                } else {
                    Helpers.showToast(getApplicationContext(), "Invalid issue date. Cannot be a future date.");
                    et.setText("");
                }
            }
        };
    }

    // creates spinner adapter for drowpdown menus
    private SpinnerAdapter createAdapter(String[] list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getBaseContext()), android.R.layout.simple_spinner_dropdown_item,list ) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }

            @Override
            public boolean isEnabled(int position) {
                // disables hint
                return position != 0;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
