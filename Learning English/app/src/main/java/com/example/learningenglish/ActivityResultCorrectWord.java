package com.example.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultcorrectword);
        percentCorrect = (TextView)findViewById(R.id.TxtPercentCorrectResultCorrectWord);
        totalStar = (TextView)findViewById(R.id.TxtTotalStar);
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        totalStar.setText(packageFromCaller.getInt("totalStar")+"");
        int correct = packageFromCaller.getInt("totalCorrect");
        int wrong = packageFromCaller.getInt("totalWrong");
        int total = correct+wrong;
        float percent = (float)correct/total*100;
        percentCorrect.setText((int)percent+"% Correct");
        drawPieChartCorrectWord(correct,wrong,total);
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

}
