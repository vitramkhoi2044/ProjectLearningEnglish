package com.example.learningenglish;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ActivityHighScoreEnglishQuiz extends Activity {
    protected TextView Txtname1,Txtname2,Txtname3,Txtname4,Txtname5,Txtscore1,Txtscore2,Txtscore3,Txtscore4,Txtscore5;
    protected Button Bt;
    protected ArrayList<HighScoreQuiz> Quizhighscore = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_englishquiz);

        Txtname1 = (TextView) findViewById(R.id.TxtNameCorrectWordTop1);
        Txtname2 = (TextView) findViewById(R.id.TxtNameCorrectWordTop2);
        Txtname3 = (TextView) findViewById(R.id.TxtNameCorrectWordTop3);
        Txtname4 = (TextView) findViewById(R.id.TxtNameCorrectWordTop4);
        Txtname5 = (TextView) findViewById(R.id.TxtNameCorrectWordTop5);

        Txtscore1 = (TextView) findViewById(R.id.TxtScoreCorrectWordTop1);
        Txtscore2 = (TextView) findViewById(R.id.TxtScoreCorrectWordTop2);
        Txtscore3 = (TextView) findViewById(R.id.TxtScoreCorrectWordTop3);
        Txtscore4 = (TextView) findViewById(R.id.TxtScoreCorrectWordTop4);
        Txtscore5 = (TextView) findViewById(R.id.TxtScoreCorrectWordTop5);

        Bt = (Button) findViewById(R.id.BtnBackHighScoreCorrectWord);

        readHighscoreWord();
        display();

        Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void readHighscoreWord(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.example.learningenglish/databases/LearngingEnglish.db", null);
        Cursor cursor = db.rawQuery("SELECT * FROM HighScoreEnglishQuiz", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String name = cursor.getString(1);
            int score = cursor.getInt(2);
            Quizhighscore.add(new HighScoreQuiz(name,score));
            cursor.moveToNext();
        }
        db.close();
    }

    public void display(){
        Txtname1.setText(Quizhighscore.get(0).getName());
        Txtname2.setText(Quizhighscore.get(1).getName());
        Txtname3.setText(Quizhighscore.get(2).getName());
        Txtname4.setText(Quizhighscore.get(3).getName());
        Txtname5.setText(Quizhighscore.get(4).getName());

        Txtscore1.setText(Quizhighscore.get(0).getScore());
        Txtscore2.setText(Quizhighscore.get(1).getScore());
        Txtscore3.setText(Quizhighscore.get(2).getScore());
        Txtscore4.setText(Quizhighscore.get(3).getScore());
        Txtscore5.setText(Quizhighscore.get(4).getScore());
    }

}
