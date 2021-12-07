package com.example.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class ActivityResultCorrectWord extends Activity {

    private TextView percentCorrect,totalStar;
    private PieChartView pieChartView;
    private Button BtnBack;
    private ArrayList<HighScoreCorrectWord> listHighScore = new ArrayList<>();
    private String name;
    private int star;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultcorrectword);
        percentCorrect = (TextView)findViewById(R.id.TxtPercentCorrectResultCorrectWord);
        totalStar = (TextView)findViewById(R.id.TxtTotalStar);
        BtnBack = (Button)findViewById(R.id.BtnBackResultCorrectWord);
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        star = packageFromCaller.getInt("totalStar");
        name = packageFromCaller.getString("playerName");
        int correct = packageFromCaller.getInt("totalCorrect");
        int wrong = packageFromCaller.getInt("totalWrong");
        int total = correct+wrong;
        float percent = (float)correct/total*100;
        totalStar.setText(""+star);
        percentCorrect.setText((int)percent+"% Correct");
        drawPieChartCorrectWord(correct,wrong,total);
        runHighScore();
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void drawPieChartCorrectWord(int dung,int sai,int tong){
        pieChartView = findViewById(R.id.ChartCorrectWord);

        ArrayList pieData = new ArrayList<>();
        pieData.add(new SliceValue(dung, Color.BLUE));
        pieData.add(new SliceValue(sai, Color.RED));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasCenterCircle(true).setCenterText1(dung+"/"+tong).setCenterText1FontSize(27).setCenterText1Color(Color.parseColor("#000000"));
        pieChartView.setPieChartData(pieChartData);
    }

    private void saveHighScoreToSQLite(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        for(int i=0 ; i<5;i++){
            db.execSQL("INSERT INTO HighScoreCorrectWord(Top,Name,Star)VALUES(?,?,?)",new String[]{i+1+"",listHighScore.get(i).getName(),listHighScore.get(i).getStar()+""});
        }
        db.close();
    }

    private void readHighScoreFromSQLite(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM HighScoreCorrectWord", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String name = cursor.getString(1);
            int star = cursor.getInt(2);
            listHighScore.add(new HighScoreCorrectWord(star,name));
            cursor.moveToNext();
        }
        db.close();
    }

    private void deleteHighScoreFromSQLite(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        for(int i=1 ; i<=5;i++){
            db.execSQL("DELETE FROM HighScoreCorrectWord WHERE Top = ?",new String[]{i+""});
        }
        db.close();
    }

    private void sortListHighScore(){
        for(int i =0; i<listHighScore.size()-1;i++){
            for(int j = listHighScore.size()-1;j>i;j--){
                if(listHighScore.get(j).getStar()>listHighScore.get(j-1).getStar()){
                    HighScoreCorrectWord tmp = listHighScore.get(j);
                    listHighScore.set(j,listHighScore.get(j-1));
                    listHighScore.set(j-1,tmp);
                }
            }
        }
    }

    private void runHighScore(){
        readHighScoreFromSQLite();
        if(star>listHighScore.get(listHighScore.size()-1).star){
            listHighScore.add(new HighScoreCorrectWord(star,name));
            sortListHighScore();
            listHighScore.remove(listHighScore.size()-1);
            deleteHighScoreFromSQLite();
            saveHighScoreToSQLite();
        }
    }

}
