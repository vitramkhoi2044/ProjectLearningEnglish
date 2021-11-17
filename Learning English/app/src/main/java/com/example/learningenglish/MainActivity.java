package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class MainActivity extends AppCompatActivity {
    private PieChartView pieChartView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correctwords);

        //intitView();
        //drawPieChartEnglishQuiz();
        //drawPieChartCorrectWord();
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