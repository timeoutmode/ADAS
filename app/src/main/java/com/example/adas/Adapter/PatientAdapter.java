package com.example.adas.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adas.Model.Patient;
import com.example.adas.R;

import java.util.Calendar;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {
    private List<Patient> patientList;

    // constructor
    public PatientAdapter(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvAddress, tvAge;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvAge = itemView.findViewById(R.id.tv_age);
        }
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_patient_item, parent, false);
        PatientViewHolder vh = new PatientViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient currentPatient = patientList.get(position);
        holder.tvName.setText(currentPatient.getFirstName() + " " + currentPatient.getLastName());
        holder.tvAddress.setText(currentPatient.getAddress());

        Calendar dob = Calendar.getInstance();
        dob.setTime(currentPatient.getBirthDate());

        Calendar today = Calendar.getInstance();

        // calculate age
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        holder.tvAge.setText(age + " Years Old");
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
}
