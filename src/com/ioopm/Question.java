package com.ioopm;

public class Question {
    private String[] answers;
    private int correctIdx;
    private String text;

    public Question(String text, String[] answers, int correctIdx){
        this.text = text;
        this.answers = answers;
        this.correctIdx = correctIdx;
    }

    public String getText(){
        return text;
    }

    public String getCorrectAnswer(){
        return answers[correctIdx];
    }
}
