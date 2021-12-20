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
    private TextView Txtname1,Txtname2,Txtname3,Txtname4,Txtname5,Txtscore1,Txtscore2,Txtscore3,Txtscore4,Txtscore5;
    private Button Bt;
    private ArrayList<HighScoreQuiz> listHighScore = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_englishquiz);

        Txtname1 = (TextView) findViewById(R.id.TxtNameEnglishQuizTop1);
        Txtname2 = (TextView) findViewById(R.id.TxtNameEnglishQuizTop2);
        Txtname3 = (TextView) findViewById(R.id.TxtNameEnglishQuizTop3);
        Txtname4 = (TextView) findViewById(R.id.TxtNameEnglishQuizTop4);
        Txtname5 = (TextView) findViewById(R.id.TxtNameEnglishQuizTop5);

        Txtscore1 = (TextView) findViewById(R.id.TxtScoreEnglishQuizTop1);
        Txtscore2 = (TextView) findViewById(R.id.TxtScoreEnglishQuizTop2);
        Txtscore3 = (TextView) findViewById(R.id.TxtScoreEnglishQuizTop3);
        Txtscore4 = (TextView) findViewById(R.id.TxtScoreEnglishQuizTop4);
        Txtscore5 = (TextView) findViewById(R.id.TxtScoreEnglishQuizTop5);

        Bt = (Button) findViewById(R.id.BtnBackHighScoreEnglishQuiz);

        readHighScoreFromSQLite();
        display();

        Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void readHighScoreFromSQLite(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM HighScoreEnglishQuiz", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String name = cursor.getString(1);
            int score = cursor.getInt(2);
            listHighScore.add(new HighScoreQuiz(name,score));
            cursor.moveToNext();
        }
        db.close();
    }

    public void display(){
        Txtname1.setText(listHighScore.get(0).getName());
        Txtname2.setText(listHighScore.get(1).getName());
        Txtname3.setText(listHighScore.get(2).getName());
        Txtname4.setText(listHighScore.get(3).getName());
        Txtname5.setText(listHighScore.get(4).getName());

        Txtscore1.setText(listHighScore.get(0).getScore()+"");
        Txtscore2.setText(listHighScore.get(1).getScore()+"");
        Txtscore3.setText(listHighScore.get(2).getScore()+"");
        Txtscore4.setText(listHighScore.get(3).getScore()+"");
        Txtscore5.setText(listHighScore.get(4).getScore()+"");
    }

}
