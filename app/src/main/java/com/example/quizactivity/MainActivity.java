package com.example.quizactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //TAG for the log.d method call to output things to the logcat
    //TAG is used as the first parameter which identifies the source of the message, in this case the app QuizActivity
    private static final String TAG = "QuizActivity";
    //String to be the key for saving the mCurrentIndex in Bundle
    private static final String KEY_INDEX = "index";
    private static final String KEY_QUESTIONS = "correct questions";

    private CheckBox mACheckBox;
    private CheckBox mBCheckBox;
    private CheckBox mCCheckBox;
    private CheckBox mDCheckBox;
    private Button mrandom2;
    //make changes
    private Button mNextButton;
    //Testing how to change things
    private Button mCheckAnswerButton;
    private Button mShowAnswerButton;
    private TextView mQuestionTextView;
    private int mUserPressed;
    //So now im in checkout as what
    private Button mrandom;


    private Answer[] mAnswerBank = new Answer[]{
            new Answer(R.string.answer_alanguageS1, R.string.answer_blanguageS1, R.string.answer_clanguageS1, R.string.answer_dlanguageS1),
            new Answer(R.string.answer_arecursive1, R.string.answer_brecursive1, R.string.answer_crecursive1, R.string.answer_drecursive1),
            new Answer(R.string.answer_alc1, R.string.answer_blc1, R.string.answer_clc1, R.string.answer_dlc1),
            new Answer(R.string.answer_alS2, R.string.answer_blS2, R.string.answer_clS2, R.string.answer_dlS2),
            new Answer(R.string.answer_aPal, R.string.answer_bPal, R.string.answer_cPal, R.string.answer_dPal),
            new Answer(R.string.answer_aLS3, R.string.answer_bLS3, R.string.answer_cLS3, R.string.answer_dLS3),
            new Answer(R.string.answer_aLS4, R.string.answer_bLS4, R.string.answer_cLS4, R.string.answer_dLS4)
    };

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_languageS1, 2),
            new Question(R.string.question_recursive1, 3),
            new Question(R.string.question_language_contains1, 0),
            new Question(R.string.question_languageS2, 1),
            new Question(R.string.question_pal, 1),
            new Question(R.string.question_LS3, 2),
            new Question(R.string.question_LS4, 3)
    };

    private int mCurrentIndex = 0;
    private int mCorrectQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mCorrectQuestions = savedInstanceState.getInt(KEY_QUESTIONS, 0);
        }


        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        mACheckBox = (CheckBox) findViewById(R.id.a_check);
        mACheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBCheckBox.setChecked(false);
                mCCheckBox.setChecked(false);
                mDCheckBox.setChecked(false);
                mUserPressed = 0;
            }
        });

        mBCheckBox = (CheckBox) findViewById(R.id.b_check);
        mBCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mACheckBox.setChecked(false);
                mCCheckBox.setChecked(false);
                mDCheckBox.setChecked(false);
                mUserPressed = 1;
            }
        });

        mCCheckBox = (CheckBox) findViewById(R.id.c_check);
        mCCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mACheckBox.setChecked(false);
                mBCheckBox.setChecked(false);
                mDCheckBox.setChecked(false);
                mUserPressed = 2;
            }
        });

        mDCheckBox = (CheckBox) findViewById(R.id.d_check);
        mDCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mACheckBox.setChecked(false);
                mBCheckBox.setChecked(false);
                mCCheckBox.setChecked(false);
                mUserPressed = 3;
            }
        });



        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(mUserPressed);
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putInt(KEY_QUESTIONS, mCorrectQuestions);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        mACheckBox.setText(mAnswerBank[mCurrentIndex].getAnswers()[0]);
        mBCheckBox.setText(mAnswerBank[mCurrentIndex].getAnswers()[1]);
        mCCheckBox.setText(mAnswerBank[mCurrentIndex].getAnswers()[2]);
        mDCheckBox.setText(mAnswerBank[mCurrentIndex].getAnswers()[3]);
        mACheckBox.setChecked(false);
        mBCheckBox.setChecked(false);
        mCCheckBox.setChecked(false);
        mDCheckBox.setChecked(false);
    }

    //Integer 0-3 passed in corresponding to a character. 0=a, 1=b, 2=c, 3=d
    private void checkAnswer(int userPressedChar) {
        int answerIsCorrect = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if (userPressedChar == answerIsCorrect) {
            mCorrectQuestions++;
        }

        checkCompleted(mCurrentIndex);
    }

    private void checkCompleted(int questionNumber) {
        if (questionNumber == mQuestionBank.length - 1) {
            Log.d(TAG, "in if statement");
            double percentage = ((double)mCorrectQuestions/mQuestionBank.length) * 100;
            Intent intent = ResultActivity.newIntent(MainActivity.this, percentage);
            startActivity(intent);
            mCorrectQuestions = 0;
        }
    }
}
