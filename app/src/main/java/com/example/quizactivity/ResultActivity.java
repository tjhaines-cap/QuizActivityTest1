package com.example.quizactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private static final String EXTRA_PERCENTAGE = "com.example.quizactivity.percentage";
    private static final String TAG = "QuizActivity";

    private TextView mScore;
    private double mPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mPercentage = getIntent().getDoubleExtra(EXTRA_PERCENTAGE, 0.00);

        Log.d(TAG, "percentage " + mPercentage);
        mScore = (TextView) findViewById(R.id.result_text_view);
        mScore.setText("Score: " + mPercentage);
    }

    public static Intent newIntent(Context packageContext, double percentage){
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_PERCENTAGE, percentage);
        return intent;
    }
}
