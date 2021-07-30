package com.myhouse.quizappsummer21;
import com.google.firebase.database.IgnoreExtraProperties;
import java.lang.Comparable;


import javax.xml.namespace.QName;

@IgnoreExtraProperties

public class HighScoreCls implements Comparable<HighScoreCls> {
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
    public int compareTo(HighScoreCls o) {
        return this.score - ((HighScoreCls)o).getScore();
            }

    public boolean equals(HighScoreCls o) {
        return this.compareTo(o)==0 && this.name.equals(((HighScoreCls)o).getName())
                && this.date.equals(((HighScoreCls)o).getDate());
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


