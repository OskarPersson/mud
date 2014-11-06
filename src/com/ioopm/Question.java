package com.ioopm;

public class Question {
    private String[] answers;
    private int correctIdx;
    private String text;

    /**
     * Creates a question with 1 answer
     * @param text The question
     * @param answers Possible answer
     * @param correctIdx The index of the correct answer
     */

    public Question(String text, String[] answers, int correctIdx){
        this.text = text;
        this.answers = answers;
        this.correctIdx = correctIdx;
    }

    /**
     * Gets the question text
     * @return Question text
     */

    public String getText(){
        return text;
    }

    /**
     * Gets the correct answer to the question
     * @return The correct question answer
     */

    public String getCorrectAnswer(){
        return answers[correctIdx];
    }
}
