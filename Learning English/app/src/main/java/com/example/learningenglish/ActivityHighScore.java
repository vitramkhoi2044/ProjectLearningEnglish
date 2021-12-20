package com.example.learningenglish;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import androidx.annotation.Nullable;


public class ActivityHighScore extends Activity {
    private Button btn,btnBack;
    private RadioGroup RG;
    private RadioButton RB_EnglishQuiz,RB_CorrectWord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        btn = (Button)findViewById(R.id.BtnSeeHighScore);
        btnBack = (Button)findViewById(R.id.BtnBackHighScore);
        RG = (RadioGroup)findViewById(R.id.RGHighScore);
        RB_EnglishQuiz = (RadioButton)findViewById(R.id.RB_EnglishQuiz);
        RB_CorrectWord = (RadioButton)findViewById(R.id.RB_CorrectWord);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idCheck = RG.getCheckedRadioButtonId();
                switch (idCheck){
                    case R.id.RB_EnglishQuiz:{
                        Intent intent = new Intent(ActivityHighScore.this,ActivityHighScoreEnglishQuiz.class);
                        startActivity(intent);
                    }break;
                    case R.id.RB_CorrectWord:{
                        Intent intent = new Intent(ActivityHighScore.this,ActivityHighScoreCorrectWord.class);
                        startActivity(intent);
                    }break;
                }
                RG.clearCheck();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
