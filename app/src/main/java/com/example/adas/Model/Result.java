package com.example.adas.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {
    private int wordRecallScore, namingScore, namingScore1, commandsScore, constructionalPraxisScore,
    ideationalPraxisScore, orientationScore, wordRecognitionTaskScore, rememberingTestInstructionsScore,
    spokenLanguageScore, wordFindingScore, comprehensionScore, executiveFunctionScore, numberCancellationScore;
    private int numberCancellationErrors, numberCancellationTargetHits, numberCancellationTaskReminder;
    public Patient patient;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new Result(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new Result[i];
    }
    };

    public Result() {
    }

    public Result(int wordRecallScore, int namingScore,int namingScore1, int commandsScore, int constructionalPraxisScore, int ideationalPraxisScore, int orientationScore, int wordRecognitionTaskScore, int rememberingTestInstructionsScore, int spokenLanguageScore, int wordFindingScore, int comprehensionScore, int executiveFunctionScore, int numberCancellationScore, int numberCancellationErrors, int numberCancellationTargetHits, int numberCancellationTaskReminder, Patient patient) {
        this.wordRecallScore = wordRecallScore;
        this.namingScore = namingScore;
        this.namingScore1 = namingScore1;
        this.commandsScore = commandsScore;
        this.constructionalPraxisScore = constructionalPraxisScore;
        this.ideationalPraxisScore = ideationalPraxisScore;
        this.orientationScore = orientationScore;
        this.wordRecognitionTaskScore = wordRecognitionTaskScore;
        this.rememberingTestInstructionsScore = rememberingTestInstructionsScore;
        this.spokenLanguageScore = spokenLanguageScore;
        this.wordFindingScore = wordFindingScore;
        this.comprehensionScore = comprehensionScore;
        this.executiveFunctionScore = executiveFunctionScore;
        this.numberCancellationScore = numberCancellationScore;
        this.numberCancellationErrors = numberCancellationErrors;
        this.numberCancellationTargetHits = numberCancellationTargetHits;
        this.numberCancellationTaskReminder = numberCancellationTaskReminder;
        this.patient = patient;
    }

    // Constructor for creating result object from parcel
    public Result(Parcel parcel) {
        this.wordRecallScore = parcel.readInt();
        this.namingScore = parcel.readInt();
        this.namingScore1 = parcel.readInt();
        this.commandsScore = parcel.readInt();
        this.constructionalPraxisScore = parcel.readInt();
        this.ideationalPraxisScore = parcel.readInt();
        this.orientationScore = parcel.readInt();
        this.wordRecognitionTaskScore = parcel.readInt();
        this.rememberingTestInstructionsScore = parcel.readInt();
        this.spokenLanguageScore = parcel.readInt();
        this.wordFindingScore = parcel.readInt();
        this.comprehensionScore = parcel.readInt();
        this.executiveFunctionScore = parcel.readInt();
        this.numberCancellationScore = parcel.readInt();
        this.numberCancellationErrors = parcel.readInt();
        this.numberCancellationTargetHits = parcel.readInt();
        this.numberCancellationTaskReminder = parcel.readInt();
        this.patient = parcel.readParcelable(Patient.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.wordRecallScore);
        parcel.writeInt(this.namingScore);
        parcel.writeInt(this.namingScore1);
        parcel.writeInt(this.commandsScore);
        parcel.writeInt(this.constructionalPraxisScore);
        parcel.writeInt(this.ideationalPraxisScore);
        parcel.writeInt(this.orientationScore);
        parcel.writeInt(this.wordRecognitionTaskScore);
        parcel.writeInt(this.rememberingTestInstructionsScore);
        parcel.writeInt(this.spokenLanguageScore);
        parcel.writeInt(this.wordFindingScore);
        parcel.writeInt(this.comprehensionScore);
        parcel.writeInt(this.executiveFunctionScore);
        parcel.writeInt(this.numberCancellationScore);
        parcel.writeInt(this.numberCancellationErrors);
        parcel.writeInt(this.numberCancellationTargetHits);
        parcel.writeInt(this.numberCancellationTaskReminder);
        parcel.writeParcelable(this.patient, i);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getWordRecallScore() {
        return wordRecallScore;
    }

    public void setWordRecallScore(int wordRecallScore) {
        this.wordRecallScore = wordRecallScore;
    }

    public int getNamingScore() {
        return namingScore;
    }

    public void setNamingScore(int namingScore) {
        this.namingScore = namingScore;
    }

    public int getCommandsScore() {
        return commandsScore;
    }

    public void setCommandsScore(int commandsScore) {
        this.commandsScore = commandsScore;
    }

    public int getConstructionalPraxisScore() {
        return constructionalPraxisScore;
    }

    public void setConstructionalPraxisScore(int constructionalPraxisScore) {
        this.constructionalPraxisScore = constructionalPraxisScore;
    }



    public int getIdeationalPraxisScore() {
        return ideationalPraxisScore;
    }

    public void setIdeationalPraxisScore(int ideationalPraxisScore) {
        this.ideationalPraxisScore = ideationalPraxisScore;
    }

    public int getOrientationScore() {
        return orientationScore;
    }

    public void setOrientationScore(int orientationScore) {
        this.orientationScore = orientationScore;
    }

    public int getWordRecognitionTaskScore() {
        return wordRecognitionTaskScore;
    }

    public void setWordRecognitionTaskScore(int wordRecognitionTaskScore) {
        this.wordRecognitionTaskScore = wordRecognitionTaskScore;
    }

    public int getRememberingTestInstructionsScore() {
        return rememberingTestInstructionsScore;
    }

    public void setRememberingTestInstructionsScore(int rememberingTestInstructionsScore) {
        this.rememberingTestInstructionsScore = rememberingTestInstructionsScore;
    }

    public int getSpokenLanguageScore() {
        return spokenLanguageScore;
    }

    public void setSpokenLanguageScore(int spokenLanguageScore) {
        this.spokenLanguageScore = spokenLanguageScore;
    }

    public int getWordFindingScore() {
        return wordFindingScore;
    }

    public void setWordFindingScore(int wordFindingScore) {
        this.wordFindingScore = wordFindingScore;
    }

    public int getComprehensionScore() {
        return comprehensionScore;
    }

    public void setComprehensionScore(int comprehensionScore) {
        this.comprehensionScore = comprehensionScore;
    }

    public int getExecutiveFunctionScore() {
        return executiveFunctionScore;
    }

    public void setExecutiveFunctionScore(int executiveFunctionScore) {
        this.executiveFunctionScore = executiveFunctionScore;
    }

    public int getNumberCancellationScore() {
        return numberCancellationScore;
    }

    public void setNumberCancellationScore(int numberCancellationScore) {
        this.numberCancellationScore = numberCancellationScore;
    }

    public int getNumberCancellationErrors() {
        return numberCancellationErrors;
    }

    public void setNumberCancellationErrors(int numberCancellationErrors) {
        this.numberCancellationErrors = numberCancellationErrors;
    }

    public int getNumberCancellationTargetHits() {
        return numberCancellationTargetHits;
    }

    public void setNumberCancellationTargetHits(int numberCancellationTargetHits) {
        this.numberCancellationTargetHits = numberCancellationTargetHits;
    }

    public int getNumberCancellationTaskReminder() {
        return numberCancellationTaskReminder;
    }

    public void setNumberCancellationTaskReminder(int numberCancellationTaskReminder) {
        this.numberCancellationTaskReminder = numberCancellationTaskReminder;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
