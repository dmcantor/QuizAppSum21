package com.myhouse.quizappsummer21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    int scoreDisp;
    TextView scoreTXT;
    Button myEmailBTN2;
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
        myEmailBTN2 = (Button) findViewById(R.id.sendEmailBTN2);

        myEmailBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addresses = new String[]{"mervoCS@gmail.com"};
                String subject = getString(R.string.emailMessageTxt);
                String body = getString(R.string.emailMessageTxt) + " " + scoreDisp +"\n\n\t\t- " + getString(R.string.app_name);

                composeEmail(addresses, subject, body);
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