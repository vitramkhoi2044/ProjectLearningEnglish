package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class MainActivity extends AppCompatActivity {
    private Button EnterEnglishQuiz, LOGOUT;
    private TextView displayUserName;
    private String username;
    private Button BtnWord,BtnBack,BtnHighScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        EnterEnglishQuiz = (Button)findViewById(R.id.BtnEnglishQuiz);
        LOGOUT = (Button)findViewById((R.id.BtnToLogin));
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("name", "");


        displayUserName = findViewById((R.id.TextUserName));
        displayUserName.setText("Hi" + " " + username);

        EnterEnglishQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_nhap.class);
                startActivity(intent);

            }
        });

        LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, activity_Login.class);
                startActivity(intent);
                finish();
            }
        });



        BtnWord = (Button)findViewById(R.id.BtnWord);
        BtnHighScore = (Button)findViewById(R.id.BtnHighscore);
        BtnBack = (Button)findViewById(R.id.BtnBack);
        BtnWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityInputCorrectWord.class);
                startActivity(intent);
            }
        });
        BtnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityHighScore.class);
                startActivity(intent);
            }
        });
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}