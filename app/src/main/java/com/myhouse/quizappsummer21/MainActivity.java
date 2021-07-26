package com.myhouse.quizappsummer21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView myQuestion;
    Button myTrue;
    Button myFalse;
    Button myFinish;

    ToggleButton myToggle;

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myQuestion = (TextView) findViewById(R.id.questionTXT);
        myTrue = (Button) findViewById(R.id.trueBTN);
        myFalse = (Button) findViewById(R.id.falseBTN);
        myFinish = (Button) findViewById(R.id.finishBTN);
        myToggle = (ToggleButton) findViewById(R.id.changeTGL);
        score = 0;

        myTrue.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Context context = getApplicationContext();
                                          CharSequence text = "You are correct this is a question!";
                                          int duration = Toast.LENGTH_LONG;
                                          score +=1;

                                          Toast toast = Toast.makeText(context, text, duration);
                                          toast.show();
                                      }
                                  }
        );

        myFalse.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Context context = getApplicationContext();
                                          CharSequence text = "Sorry, this is not correct!";
                                          int duration = Toast.LENGTH_SHORT;

                                          Toast toast = Toast.makeText(context, text, duration);
                                          toast.show();

                                      }
                                  }
        );



    }
}