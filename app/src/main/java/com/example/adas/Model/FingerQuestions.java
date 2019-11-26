package com.example.adas.Model;

public class FingerQuestions {
   private int imageId;
   private String clue;
   private String[] ansList;


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public String[] getAnsList() {
        return ansList;
    }

    public void setAnsList(String[] andList) {
        this.ansList = andList;
    }


    public boolean checkAnswer(String answer){
        for (String ans: ansList){
            if (answer.toLowerCase().equals(ans.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public boolean checkClue(String answer) {
        return clue.toLowerCase().equals(answer.toLowerCase());
    }
}
