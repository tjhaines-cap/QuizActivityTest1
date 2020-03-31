package com.example.quizactivity;

public class Answer {

    private int[] mAnswers = new int[4];

    public Answer(int a, int b, int c, int d){
        mAnswers[0] = a;
        mAnswers[1] = b;
        mAnswers[2] = c;
        mAnswers[3] = d;
    }

    public int[] getAnswers() {
        return mAnswers;
    }


}
