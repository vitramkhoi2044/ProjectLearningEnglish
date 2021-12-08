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
    ArrayList<HighScoreQuiz> Quizhighscore = new ArrayList<>();
    int Score;

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
        BT = (Button)findViewById(R.id.BtnBackResultEnglishQuiz);
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_ResultEnglishQuiz.this, MainActivity.class);
                startActivity(intent);

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

    private void saveHighscore(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.example.learningenglish/databases/LearngingEnglish.db", null);
        for (int i=0;i<5;i++){
            db.execSQL("INSERT INTO HighScoreEnglishQuiz(Top,Name,SCore) VALUES (?,?,?)",
                    new String[]{(i+1)+"",Quizhighscore.get(i).getName(),Quizhighscore.get(i).getScore()+""});
        }
        db.close();
    }

    private void readHighScore(){
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

    private void deleteHighScore(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/data/com.example.learningenglish/databases/LearngingEnglish.db", null);
        for (int i=1;i<=5;i++){
            db.execSQL("DELETE FROM HighScoreEnglishQuiz WHERE Top = ?",new String[]{i+""});
        }
        db.close();
    }

    private void sortHighScore(){
        for (int i=0;i<Quizhighscore.size()-1;i++){
            for (int j=Quizhighscore.size()-1;j>1;j--){
                if (Quizhighscore.get(j).getScore()>Quizhighscore.get(j-1).getScore()){
                    HighScoreQuiz tmp = Quizhighscore.get(j);
                    Quizhighscore.set(j,Quizhighscore.get(j-1));
                    Quizhighscore.set(j-1,tmp);
                }
            }
        }
    }

    private void runQuizHighScore(){
        readHighScore();
        if (Score > Quizhighscore.get(Quizhighscore.size()-1).Score){
            Quizhighscore.add(new HighScoreQuiz(Name,Score));
            sortHighScore();
            Quizhighscore.remove(Quizhighscore.size()-1);
            deleteHighScore();
            saveHighscore();
        }
    }
}
