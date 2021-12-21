package com.example.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.jar.Attributes;

public class activity_EnglishQuiz extends Activity {
    private TextView Cauhoi;
    private RadioGroup RG;
    private Button BT;
    private RadioButton A,B,C,D;
    private ImageView Img;
    private Chronometer chronometer;
    private int counter = 20;
    private TextView TxtSoCau;
    private String name;


    private int pos=0;
    private int kq=0;
    private int size;
    private String soCau;

    private ArrayList<QuestionNare> L = new ArrayList();
    private Set<Integer> H = new LinkedHashSet<Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Cauhoi = (TextView) findViewById(R.id.TxtCauhoi);
        RG = (RadioGroup) findViewById(R.id.RG_QuizGroup);
        BT = (Button) findViewById(R.id.BtnToSubmit);
        A = (RadioButton) findViewById(R.id.RB_AnswerA);
        B = (RadioButton) findViewById(R.id.RB_AnswerB);
        C = (RadioButton) findViewById(R.id.RB_AnswerC);
        D = (RadioButton) findViewById(R.id.RB_AnswerD);
        Img = (ImageView) findViewById((R.id.TxtQuizImage)) ;
        chronometer = (Chronometer) findViewById(R.id.ChronoTimer);
        TxtSoCau = (TextView) findViewById(R.id.TxtQuestion);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("MyInfoEnglishQuiz");
        soCau = packageFromCaller.getString("noQuestion");
        TxtSoCau.setText(pos+1+"/"+soCau);
        name = packageFromCaller.getString("name");

//        Read Data From database

        ReadDataEnglishQuiz();
        size = L.size();

        //  Handle Random Numbers, use Hash to put everything in order
        Random rng = new Random();
        while (H.size() < Integer.parseInt(soCau))
        {
            Integer next = rng.nextInt(size) + 1;

            H.add(next);
        }
//        Convert LinkHashedSet to ArrayList
        List<Integer> Q = new ArrayList<>();
        Q.addAll(H);
        Display(Q.get(0));
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (counter < 0){
                    counter = 20;
                    int idCheck = RG.getCheckedRadioButtonId();
                    switch (idCheck)
                    {
                        case R.id.RB_AnswerA:{
                            if (L.get(Q.get(pos)) .Answer.compareTo("A")==0){
                                MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.correct);
                                song.start();
                                kq = kq+1;
                            }
                            else{
                                MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.wrong);
                                song.start();
                            }
                            break;
                        }
                        case R.id.RB_AnswerB:{
                            if (L.get(Q.get(pos)) .Answer.compareTo("B")==0){
                                MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.correct);
                                song.start();
                                kq = kq+1;
                            }
                            else{
                                MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.wrong);
                                song.start();
                            }
                            break;
                        }
                        case R.id.RB_AnswerC:{
                            if (L.get(Q.get(pos)) .Answer.compareTo("C")==0) {
                                MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.correct);
                                song.start();
                                kq = kq+1;
                            }
                            else{
                                MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.wrong);
                                song.start();
                            }
                            break;
                        }
                        case R.id.RB_AnswerD:{
                            if (L.get(Q.get(pos)) .Answer.compareTo("D")==0){
                                MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.correct);
                                song.start();
                                kq = kq+1;
                            }
                            else{
                                MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.wrong);
                                song.start();
                            }
                            break;
                        }
                        default:{
                            MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.wrong);
                            song.start();
                        }
                    }
                    pos++;

                    if (pos >= Integer.parseInt(soCau))
                    {
                        Intent intent = new Intent(activity_EnglishQuiz.this,activity_ResultEnglishQuiz.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("Socau", pos);
                        bundle.putString("name", name);
                        intent.putExtra("MyPackage", bundle);
                        startActivity(intent);
                        finish();

                    }
                    else     Display(Q.get(pos));

                }
                else {
                    BT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int idCheck = RG.getCheckedRadioButtonId();
                            switch (idCheck)
                            {
                                case R.id.RB_AnswerA:{
                                    if (L.get(Q.get(pos)) .Answer.compareTo("A")==0){
                                        MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.correct);
                                        song.start();
                                        kq = kq+1;
                                    }
                                    else{
                                        MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.wrong);
                                        song.start();
                                    }
                                    break;
                                }
                                case R.id.RB_AnswerB:{
                                    if (L.get(Q.get(pos)) .Answer.compareTo("B")==0){
                                        MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.correct);
                                        song.start();
                                        kq = kq+1;
                                    }
                                    else{
                                        MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.wrong);
                                        song.start();
                                    }
                                    break;
                                }
                                case R.id.RB_AnswerC:{
                                    if (L.get(Q.get(pos)) .Answer.compareTo("C")==0) {
                                        MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.correct);
                                        song.start();
                                        kq = kq+1;
                                    }
                                    else{
                                        MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.wrong);
                                        song.start();
                                    }
                                    break;
                                }
                                case R.id.RB_AnswerD:{
                                    if (L.get(Q.get(pos)) .Answer.compareTo("D")==0){
                                        MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.correct);
                                        song.start();
                                        kq = kq+1;
                                    }
                                    else{
                                        MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.wrong);
                                        song.start();
                                    }
                                    break;
                                }
                                default:{
                                    MediaPlayer song = MediaPlayer.create(activity_EnglishQuiz.this,R.raw.wrong);
                                    song.start();
                                }
                            }
                            pos++;

                            if (pos >= Integer.parseInt(soCau))
                            {
                                Intent intent = new Intent(activity_EnglishQuiz.this,activity_ResultEnglishQuiz.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("KQ", kq);
                                bundle.putInt("Socau", pos);
                                bundle.putString("name", name);
                                intent.putExtra("MyPackage", bundle);
                                startActivity(intent);
                                finish();

                            }
                            else     Display(Q.get(pos));
                            counter = 20;
                        }
                    });
                    chronometer.setText(""+counter);
                    counter--;
                }

                TxtSoCau.setText(pos+1+"/"+soCau);
            }
        });

    }

    public void Display(int i)
    {
        Cauhoi.setText(L.get(i).Q);
        A.setText(L.get(i).AnswerA);
        B.setText(L.get(i).AnswerB);
        C.setText(L.get(i).AnswerC);
        D.setText(L.get(i).AnswerD);
        if(L.get(i).Image != null){
            Img.setVisibility(View.VISIBLE);
            int src = this.getResources().getIdentifier(L.get(i).Image, "drawable",  getPackageName());
            Img.setImageResource(src);
        } else if(L.get(i).Image == null){
            Img.setImageResource(0);
            Img.setVisibility(View.GONE);
        }

        RG.clearCheck();
    }

    public void ReadDataEnglishQuiz()
    {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db", null);
        Cursor cursor = db.rawQuery("Select * FROM EnglishQuiz", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int ID= cursor.getInt(0);
            String Q = cursor.getString(1);
            String AnswerA = cursor.getString(2);
            String AnswerB = cursor.getString(3);
            String AnswerC = cursor.getString(4);
            String AnswerD= cursor.getString(5);
            String Result = cursor.getString(6);
            String Image = cursor.getString(7);
            L.add(new QuestionNare(ID,Q,AnswerA,AnswerB, AnswerC, AnswerD, Result, Image));
            cursor.moveToNext();
        }
        cursor.close();
    }

}
