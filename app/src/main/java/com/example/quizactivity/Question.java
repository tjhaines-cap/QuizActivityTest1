package com.example.quizactivity;

public class Question {

    private int mTextResId;
    private int mAnswer;

    public Question(int textResId, int answer){
        mTextResId = textResId;
        mAnswer = answer;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public int isAnswerTrue() {
        return mAnswer;
    }

    public void setAnswerTrue(int answer) {
        mAnswer = answer;
    }
}
