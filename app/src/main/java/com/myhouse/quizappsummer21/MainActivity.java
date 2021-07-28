package com.myhouse.quizappsummer21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView myQuestion;
    Button myTrue;
    Button myFalse;
    Button myFinish;
    Button myNext;

    //Toast initial values
    String messageText;
    int durationToast;

    //Question instances and other variables
    Question q0,q1,q2,q3,q4,q5, currentQ;
    Question[] questions;
    int currentQindx;

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //initialize origin variables
        myQuestion = (TextView) findViewById(R.id.questionTXT);
        myTrue = (Button) findViewById(R.id.trueBTN);
        myFalse = (Button) findViewById(R.id.falseBTN);
        myFinish = (Button) findViewById(R.id.finishBTN);
        myNext = (Button) findViewById(R.id.nextBTN);
        //message text for toast
        messageText = "";
        score = 0;

        //initialize variables for questions

        //q0 = new Question("Click next question for first question",true);
        q1 = new Question(getString(R.string.q1Txt),true);
        q2 = new Question(getString(R.string.q2Txt),false);
        q3 = new Question(getString(R.string.q3Txt),false);
        q4 = new Question(getString(R.string.q4Txt),true);
        q5 = new Question(getString(R.string.q5Txt),true);
        questions = new Question[] {q1,q2,q3,q4,q5};
        //seems a bit redundant
        currentQindx = 0;
        currentQ = questions[currentQindx];
        //Initialize Question on xml
        myQuestion.setText(q1.getQuestionText());

        /*  Code after version 0.1
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
         */

        myTrue.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          // confirm boolean == true
                                         if (questions[currentQindx].isCorrectAnswer()) {
                                             messageText = getString(R.string.correctMessageTxt);
                                             score += 1;
                                             durationToast = Toast.LENGTH_LONG;
                                         }
                                         else {
                                             messageText = getString(R.string.wrongMessageTxt);
                                             durationToast = Toast.LENGTH_SHORT;

                                         }

                                          Context context = getApplicationContext();

                                          Toast toast = Toast.makeText(context, messageText, durationToast);
                                          toast.show();
                                      }
                                  }
        );


        myFalse.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if (!questions[currentQindx].isCorrectAnswer()) {
                                              messageText = getString(R.string.correctMessageTxt);
                                              score += 1;
                                              durationToast = Toast.LENGTH_LONG;
                                          }
                                          else {
                                              messageText = getString(R.string.wrongMessageTxt);
                                              durationToast = Toast.LENGTH_SHORT;

                                          }



                                          Context context = getApplicationContext();
                                          Toast toast = Toast.makeText(context, messageText, durationToast);
                                          toast.show();

                                      }
                                  }
        );

        myNext.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                        //Something is not correct the questions.length should work with < but is not.
                                        if (currentQindx < questions.length-1)
                                        {
                                            currentQindx++;
                                            currentQ = questions[currentQindx];
                                            myQuestion.setText(currentQ.getQuestionText());
                                        }

                                        else {
                                            //INTENT data type   variable name  = new data type  (from, to)
                                            Intent openScoreTENT = new Intent(MainActivity.this, ScoreActivity.class);
                                            //passes the score data to the new screen
                                            openScoreTENT.putExtra("scoreDATA", score);
                                            startActivity(openScoreTENT);
                                        }
                                        }
                                    }
        );
        myFinish.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                         //INTENT data type   variable name  = new data type  (from, to)
                                         Intent openScoreTENT = new Intent(MainActivity.this, ScoreActivity.class);
                                         //passes the score data to the new screen

                                         openScoreTENT.putExtra("scoreDATA",score);
                                         startActivity(openScoreTENT);
                                      }
                                  }
        );



    }



}