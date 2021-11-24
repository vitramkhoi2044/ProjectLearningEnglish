package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class MainActivity extends AppCompatActivity {
    private PieChartView pieChartView;
    private ViewPager mViewPager;
    protected Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button)findViewById(R.id.BtnWord);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityCorrectWord.class);
                startActivity(intent);
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