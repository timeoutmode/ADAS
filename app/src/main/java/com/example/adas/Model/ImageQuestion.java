package com.example.adas.Model;

public class ImageQuestion {
    private int imageid;
    private String clue;
    private String[] answerList, functionList;

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String[] getAnswerList() {
        return answerList;
    }

    public void setAnswerList(String[] answerList) {
        this.answerList = answerList;
    }


    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public String[] getFunctionList() {
        return functionList;
    }

    public void setFunctionList(String[] functionList) {
        this.functionList = functionList;
    }


    public boolean checkAnswer(String answer) {
        for(String ans : answerList) {
            if(answer.toLowerCase().equals(ans.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public boolean checkFunction(String answer) {
        for(String ans : functionList) {
            if(answer.toLowerCase().equals(ans.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public boolean checkClue(String answer) {
        return clue.toLowerCase().equals(answer.toLowerCase());
    }
}
