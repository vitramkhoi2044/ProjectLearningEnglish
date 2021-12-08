package com.example.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.jar.Attributes;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class activity_ResultEnglishQuiz extends Activity {
    String kq;
    private PieChartView pieChartView;
    Button BT;
    TextView percent;
    int ketQua = 0;
    int soCau = 0;
    String Name;
    ArrayList<HighScoreQuiz> listHighScore = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultenglishquiz);

        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("MyPackage");
        Name = packageFromCaller.getString("name");
        ketQua = packageFromCaller.getInt("KQ");
        soCau = packageFromCaller.getInt("Socau");
        float percentCorrect = (float)ketQua/soCau * 100;
        kq = ketQua + "/" + soCau;
        drawPieChartEnglishQuiz(kq, ketQua, soCau);
        percent = (TextView) findViewById(R.id.TxtPercentCorrectResultEnglishQuiz);
        percent.setText((int)percentCorrect + "% correct");
        runQuizHighScore();
        BT = (Button)findViewById(R.id.BtnBackResultEnglishQuiz);
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void drawPieChartEnglishQuiz(String txt, int soCauDung, int soCau){
        pieChartView = findViewById(R.id.ChartEnglishQuiz);

        ArrayList pieData = new ArrayList<>();
        pieData.add(new SliceValue(soCauDung, Color.BLUE));
        pieData.add(new SliceValue(soCau - soCauDung, Color.RED));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasCenterCircle(true).setCenterText1(txt).setCenterText1FontSize(27).setCenterText1Color(Color.parseColor("#000000"));
        pieChartView.setPieChartData(pieChartData);
    }
    private void saveHighScoreToSQLite(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        for(int i=0 ; i<5;i++){
            db.execSQL("INSERT INTO HighScoreEnglishQuiz(Top,Name,Score)VALUES(?,?,?)",new String[]{i+1+"",listHighScore.get(i).getName(),listHighScore.get(i).getScore()+""});
        }
        db.close();
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

    private void deleteHighScoreFromSQLite(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        for(int i=1 ; i<=5;i++){
            db.execSQL("DELETE FROM HighScoreEnglishQuiz WHERE Top = ?",new String[]{i+""});
        }
        db.close();
    }

    private void sortListHighScore(){
        for(int i =0; i<listHighScore.size()-1;i++){
            for(int j = listHighScore.size()-1;j>i;j--){
                if(listHighScore.get(j).getScore()>listHighScore.get(j-1).getScore()){
                    HighScoreQuiz tmp = listHighScore.get(j);
                    listHighScore.set(j,listHighScore.get(j-1));
                    listHighScore.set(j-1,tmp);
                }
            }
        }
    }
    private void runQuizHighScore(){
        readHighScoreFromSQLite();
        if (ketQua > listHighScore.get(listHighScore.size()-1).getScore()){
            listHighScore.add(new HighScoreQuiz(Name,ketQua));
            sortListHighScore();
            listHighScore.remove(listHighScore.size()-1);
            deleteHighScoreFromSQLite();
            saveHighScoreToSQLite();
        }
    }
}
