package com.example.adas.SpeechComprehension;

import com.example.adas.R;

public class QuestionLibrary {

    // Array of questions go here
    private String mQuestions[] = {
            "Question 1?",
            "Question 2?",
            "Question 3?",
            "Question 4?"
    };
    private String mChoices[][] = {
            {"Yes", "No", "Maybe", "Idk"},
            {"Yes", "No", "Maybe", "Idk"},
            {"Yes", "No", "Maybe", "Idk"},

            {"Yes", "No", "Maybe", "Idk"}
    };

    private String mCorrectAnswers[] = {"Yes","No","Maybe","Idk"};

    public String getQuestion(int n){
         String question = mQuestions[n];
        return question;
    }

    public String getChoice1(int n){
        String choice0 = mChoices[n][0];
        return choice0;

    }

    public String getChoice2(int n){
        String choice1 = mChoices[n][1];
        return choice1;
    }

    public String getChoice3(int n){
        String choice2 = mChoices[n][2];
        return choice2;
    }

    public String getChoice4(int n){
        String choice3 = mChoices[n][3];
        return choice3;
    }

    public String getCorrectAnswer(int n)
    {
        String answer = mCorrectAnswers[n];
        return answer;
    }
}
