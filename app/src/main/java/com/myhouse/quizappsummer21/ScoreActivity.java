package com.myhouse.quizappsummer21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class ScoreActivity extends AppCompatActivity {

    int scoreDisp;
    TextView scoreTXT;
    Button myEmailBTN2;
    Button myRestartBTN;
    Intent incomingScoreVal;
    HighScoreCls highscore,sortHighscore;

    //Its late I am brute forcing this
    TextView scorer1,scorer2,scorer0;
    TextView score1,score2,score0;

    private ArrayList<HighScoreCls> myHighScores,SortHighScores;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //Makes a connection to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        //Get score from other activity for use in this activity
        scoreTXT = (TextView) findViewById(R.id.scoreUPT);
        incomingScoreVal = getIntent();
        scoreDisp = incomingScoreVal.getIntExtra("scoreDATA", 0);

        //This is NOT Python, you need to convert int to string
        scoreTXT.setText("" + scoreDisp);
        myEmailBTN2 = (Button) findViewById(R.id.sendEmailBTN2);
        myRestartBTN = (Button) findViewById(R.id.restartBTN);

        //Yes. this should be an array but I am too tired to think
        scorer0 = (TextView) findViewById(R.id.scoreN1);
        scorer1 = (TextView) findViewById(R.id.scoreN2);
        scorer2 = (TextView) findViewById(R.id.scoreN3);
        score0 = (TextView) findViewById(R.id.score1);
        score1 = (TextView) findViewById(R.id.score2);
        score2 = (TextView) findViewById(R.id.score3);


        //Writes a value at the database
        //Gets a reference for an object at 'message'
        //Get an automatically generated "key" for the customer

        //get the date for the High Score table -- not used at the moment
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
        String currentDate = sdf.format(new Date());

        //create instance of HighScore class and create arraylist to store data
        highscore=new HighScoreCls(scoreDisp,"test name",currentDate);
        myHighScores = new ArrayList<HighScoreCls>();

        DatabaseReference myRef = database.getReference("High Score");
        String key = myRef.push().getKey();

// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                // int value = dataSnapshot.getValue(Integer.class);
               // Log.d("STUPIDLOGCOMMENT", "Value is: " + value);
                // Iterate through all the children in the snapshot, this should be
               // all the children in the "customers" object
                for (DataSnapshot highScoresSnapShot : dataSnapshot.getChildren()) {
                    //From our snapshot, get the value of our key/value pair. This value
                    //contains a customer object
                    HighScoreCls myHighScore = highScoresSnapShot.getValue(HighScoreCls.class);
                    myHighScores.add(myHighScore);
                    //Log.d("onDataChange()", myHighScore.toString());
                }


                highscore=myHighScores.get(myHighScores.size()-1);
                score0.setText("" +highscore.getScore());
                scorer0.setText("" +highscore.getName());
                highscore=myHighScores.get(myHighScores.size()-2);
                score1.setText(""+highscore.getScore());
                scorer1.setText(""+highscore.getName());
                highscore=myHighScores.get(myHighScores.size()-3);
                score2.setText(""+highscore.getScore());
                scorer2.setText(""+highscore.getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FAILEDTOREAD", "Failed to read value.", error.toException());
            }
        });

        myEmailBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addresses = new String[]{"mervoCS@gmail.com"};
                String subject = getString(R.string.emailMessageTxt);
                String body = getString(R.string.emailMessageTxt) + " " + scoreDisp + "\n\n\t\t- " + getString(R.string.app_name);

                composeEmail(addresses, subject, body);
            }
        });

        myRestartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //INTENT data type   variable name  = new data type  (from, to)
                Intent returnTENT = new Intent(ScoreActivity.this, MainActivity.class);
                //passes the score data to the new screen

                startActivity(returnTENT);
            }
        });

    }

    private void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }




}