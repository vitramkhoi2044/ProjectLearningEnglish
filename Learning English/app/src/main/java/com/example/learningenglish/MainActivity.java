package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
=======
import android.content.Intent;
>>>>>>> 4e002f9328960db898fe4d4fff41f4793b7956ec
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
=======

>>>>>>> 4e002f9328960db898fe4d4fff41f4793b7956ec

import java.util.ArrayList;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class MainActivity extends AppCompatActivity {
    protected Button BT1, LOGOUT;
    private PieChartView pieChartView;
<<<<<<< HEAD
    private ViewPager mViewPager;
    TextView displayUserName;
    String username;
=======
    private Button BtnWord,BtnBack,BtnHighScore;

>>>>>>> 4e002f9328960db898fe4d4fff41f4793b7956ec
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
<<<<<<< HEAD

        setContentView(R.layout.activity_main);
        BT1 = (Button)findViewById(R.id.BtnQuiz);
        LOGOUT = (Button)findViewById((R.id.BtnToLogin));
//        Intent callerIntent=getIntent();
//        Bundle packageFromCaller= callerIntent.getBundleExtra("loginExtra");
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("name", "");


        displayUserName = findViewById((R.id.TextUserName));
        displayUserName.setText("Hi" + " " + username);

        BT1.setOnClickListener(new View.OnClickListener() {
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

=======
        setContentView(R.layout.activity_main);
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
>>>>>>> 4e002f9328960db898fe4d4fff41f4793b7956ec
    }

    private void drawPieChartEnglishQuiz(){
        pieChartView = findViewById(R.id.ChartEnglishQuiz);

        ArrayList pieData = new ArrayList<>();
        pieData.add(new SliceValue(9, Color.BLUE));
        pieData.add(new SliceValue(1, Color.RED));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasCenterCircle(true).setCenterText1("9/10").setCenterText1FontSize(27).setCenterText1Color(Color.parseColor("#000000"));
        pieChartView.setPieChartData(pieChartData);
    }


}