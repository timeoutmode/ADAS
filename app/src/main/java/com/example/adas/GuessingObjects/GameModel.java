package com.example.adas.GuessingObjects;

public class
GameModel {
    private static final String TAG = "FlagQuiz Activity";
    // String name;
    boolean isCorrect = false;
    int image;
    String clues;


    public GameModel(int image, String clues) {
        this.image = image;
        this.clues = clues;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isCorrect(){
        return isCorrect;
    }

    public int getImage() {
        return image;
    }

    public String getClues() {
        return clues;
    }
}
