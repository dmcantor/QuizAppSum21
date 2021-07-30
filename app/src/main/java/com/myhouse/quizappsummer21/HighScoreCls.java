package com.myhouse.quizappsummer21;
import com.google.firebase.database.IgnoreExtraProperties;
import java.util.Comparator;

import javax.xml.namespace.QName;

@IgnoreExtraProperties

public class HighScoreCls {
    public String name, date;
    public int score;

    //default constructor
    public HighScoreCls(){}

    public HighScoreCls(int score, String name, String date){
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "HighScore{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", score=" + score +
                '}';
    }


}


