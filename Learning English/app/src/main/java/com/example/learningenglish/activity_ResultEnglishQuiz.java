package com.example.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultenglishquiz);

        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("MyPackage");
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

}
