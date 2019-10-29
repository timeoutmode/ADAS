package com.example.adas.Model;

import java.util.Date;

public class Patient {
    private String patientID, firstName, lastName, address, ethnicity, gender;
    private Date birthDate;

    public Patient() {}



    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public Patient(String patientID, String firstName, String lastName, String address, String ethnicity, String gender, Date birthDate) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.ethnicity = ethnicity;
        this.gender = gender;
        this.birthDate = birthDate;
    }


    public String getPatientID() {
        return patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
