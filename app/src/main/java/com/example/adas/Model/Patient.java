package com.example.adas.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Patient implements Parcelable {
    private String patientID, firstName, lastName, address, ethnicity, gender;
    private Date birthDate;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new Patient(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new Patient[i];
        }
    };

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

    // Constructor for creating result object from parcel
    public Patient(Parcel parcel) {
        this.patientID = parcel.readString();
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
        this.address = parcel.readString();
        this.ethnicity = parcel.readString();
        this.gender = parcel.readString();
        if(parcel.readLong() != -1L) {
            this.birthDate =  new Date(parcel.readLong());
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.patientID);
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.address);
        parcel.writeString(this.ethnicity);
        parcel.writeString(this.gender);
        parcel.writeLong(this.birthDate != null ? birthDate.getTime() : -1L);
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
