package com.myhouse.quizappsummer21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startScreen extends AppCompatActivity {
    Button myStartBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        myStartBTN = (Button) findViewById(R.id.startBTN);

        myStartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //INTENT data type   variable name  = new data type  (from, to)
                Intent returnTENT = new Intent(startScreen.this, MainActivity.class);
                //passes the score data to the new screen

                startActivity(returnTENT);


            }
        });
    }
}