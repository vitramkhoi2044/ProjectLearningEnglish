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

public class ActivityHighScoreCorrectWord extends Activity {
    private TextView txtName1,txtName2,txtName3,txtName4,txtName5,txtStar1,txtStar2,txtStar3,txtStar4,txtStar5;
    private Button btn;
    private ArrayList<HighScoreCorrectWord> listHighScore = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_correctword);
        txtName1 = (TextView)findViewById(R.id.TxtNameCorrectWordTop1);
        txtName2 = (TextView)findViewById(R.id.TxtNameCorrectWordTop2);
        txtName3 = (TextView)findViewById(R.id.TxtNameCorrectWordTop3);
        txtName4 = (TextView)findViewById(R.id.TxtNameCorrectWordTop4);
        txtName5 = (TextView)findViewById(R.id.TxtNameCorrectWordTop5);

        txtStar1 = (TextView)findViewById(R.id.TxtScoreCorrectWordTop1);
        txtStar2 = (TextView)findViewById(R.id.TxtScoreCorrectWordTop2);
        txtStar3 = (TextView)findViewById(R.id.TxtScoreCorrectWordTop3);
        txtStar4 = (TextView)findViewById(R.id.TxtScoreCorrectWordTop4);
        txtStar5 = (TextView)findViewById(R.id.TxtScoreCorrectWordTop5);

        btn = (Button)findViewById(R.id.BtnBackHighScoreCorrectWord);

        readHighScoreFromSQLite();
        display();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void display(){
        txtName1.setText(listHighScore.get(0).getName());
        txtName2.setText(listHighScore.get(1).getName());
        txtName3.setText(listHighScore.get(2).getName());
        txtName4.setText(listHighScore.get(3).getName());
        txtName5.setText(listHighScore.get(4).getName());

        txtStar1.setText(""+listHighScore.get(0).getStar());
        txtStar2.setText(""+listHighScore.get(1).getStar());
        txtStar3.setText(""+listHighScore.get(2).getStar());
        txtStar4.setText(""+listHighScore.get(3).getStar());
        txtStar5.setText(""+listHighScore.get(4).getStar());
    }

    public void readHighScoreFromSQLite(){
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
}
