package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    protected Button BT1, LOGOUT;
    private PieChartView pieChartView;
    private ViewPager mViewPager;
    TextView displayUserName;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

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

    private void drawPieChartCorrectWord(){
        pieChartView = findViewById(R.id.ChartCorrectWord);

        ArrayList pieData = new ArrayList<>();
        pieData.add(new SliceValue(8, Color.BLUE));
        pieData.add(new SliceValue(2, Color.RED));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasCenterCircle(true).setCenterText1("8/10").setCenterText1FontSize(27).setCenterText1Color(Color.parseColor("#000000"));
        pieChartView.setPieChartData(pieChartData);
    }

    private void intitView(){
        mViewPager = (ViewPager)findViewById(R.id.ViewPagerHighScore);
        mViewPager.setAdapter(new Adapter_HighScore(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout)findViewById(R.id.TabHighScore);
        tabLayout.setupWithViewPager(mViewPager);
    }
}