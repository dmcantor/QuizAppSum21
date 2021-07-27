package com.myhouse.quizappsummer21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    int scoreDisp;
    TextView scoreTXT;
    Intent incomingScoreVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTXT = (TextView) findViewById(R.id.scoreUPT);
        incomingScoreVal = getIntent();
        scoreDisp = incomingScoreVal.getIntExtra("scoreDATA",0);

        //Not Python, you need to convert int to string
        scoreTXT.setText(""+scoreDisp);

    }



}