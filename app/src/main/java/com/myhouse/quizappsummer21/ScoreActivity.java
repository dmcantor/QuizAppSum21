package com.myhouse.quizappsummer21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreActivity extends AppCompatActivity {

    int scoreDisp;
    TextView scoreTXT;
    Button myEmailBTN2;
    Button myRestartBTN;
    Intent incomingScoreVal;

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


        //Writes a value at the database
        //Gets a reference for an object at 'message'
        DatabaseReference myRef = database.getReference("High Score");
        myRef.setValue(scoreDisp);


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