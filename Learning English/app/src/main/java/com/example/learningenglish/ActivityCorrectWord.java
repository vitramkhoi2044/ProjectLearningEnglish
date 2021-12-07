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
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class ActivityCorrectWord extends Activity {
    private ArrayList<Word> ListLevel1 = new ArrayList<>();
    private ArrayList<Word> ListLevel2 = new ArrayList<>();
    private ArrayList<Word> ListLevel3 = new ArrayList<>();
    private ArrayList<Word> ListLevel4 = new ArrayList<>();
    private ArrayList<Word> ListLevel5 = new ArrayList<>();
    private ArrayList<Word> ListLevel6 = new ArrayList<>();
    private ArrayList<Word> ListLevel7 = new ArrayList<>();
    private ArrayList<Word> ListLevel8 = new ArrayList<>();
    private ArrayList<Word> ListLevel9 = new ArrayList<>();
    private ArrayList<Word> ListLevel10 = new ArrayList<>();
    private ArrayList<Integer> Number1 = new ArrayList<>();
    private ArrayList<Integer> Number2 = new ArrayList<>();
    private ArrayList<Integer> Number3 = new ArrayList<>();
    private ArrayList<Integer> Number4 = new ArrayList<>();
    private ArrayList<Integer> Number5 = new ArrayList<>();
    private ArrayList<Integer> Number6 = new ArrayList<>();
    private ArrayList<Integer> Number7 = new ArrayList<>();
    private ArrayList<Integer> Number8 = new ArrayList<>();
    private ArrayList<Integer> Number9 = new ArrayList<>();
    private ArrayList<Integer> Number10 = new ArrayList<>();
    private TextView TxtQuestion,TxtStar,TxtTmpRight,TxtTmpWrong,TxtLevel;
    private Button BtnSubmit;
    private EditText Edt;
    private Chronometer chronometer;
    private int pos1 = 0;
    private int pos2 = 0;
    private int pos3 = 0;
    private int pos4 = 0;
    private int pos5 = 0;
    private int pos6 = 0;
    private int pos7 = 0;
    private int pos8 = 0;
    private int pos9 = 0;
    private int pos10 = 0;
    private int level = 3;
    private int tmpRight = 0;
    private int tmpWrong = 0;
    private int tmpStar = 0;
    private int totalRight = 0;
    private int totalWrong = 0;
    private int totalStar = 0;
    private int counter = 30;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correctwords);

        TxtQuestion = (TextView)findViewById(R.id.TxtQuestion);
        BtnSubmit = (Button)findViewById(R.id.BtnSubmit);
        Edt = (EditText)findViewById(R.id.EdtAnswer);
        TxtStar = (TextView)findViewById(R.id.TxtTotalStar);
        TxtTmpRight = (TextView)findViewById(R.id.TxtTmpRight);
        TxtTmpWrong = (TextView)findViewById(R.id.TxtTmpWrong);
        TxtLevel = (TextView)findViewById(R.id.TxtLevel);
        chronometer = (Chronometer)findViewById(R.id.Chrono);

        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("Information");
        name = packageFromCaller.getString("playerName");


        readDataFromSQLiteLevel1();
        readDataFromSQLiteLevel2();
        readDataFromSQLiteLevel3();
        readDataFromSQLiteLevel4();
        readDataFromSQLiteLevel5();
        readDataFromSQLiteLevel6();
        readDataFromSQLiteLevel7();
        readDataFromSQLiteLevel8();
        readDataFromSQLiteLevel9();
        readDataFromSQLiteLevel10();

        randomNum(ListLevel1,Number1);
        randomNum(ListLevel2,Number2);
        randomNum(ListLevel3,Number3);
        randomNum(ListLevel4,Number4);
        randomNum(ListLevel5,Number5);
        randomNum(ListLevel6,Number6);
        randomNum(ListLevel7,Number7);
        randomNum(ListLevel8,Number8);
        randomNum(ListLevel9,Number9);
        randomNum(ListLevel10,Number10);

        Display(Number3.get(pos3),ListLevel3);
        TxtStar.setText(""+tmpStar);
        TxtTmpRight.setText("Correct: "+tmpRight);
        TxtTmpWrong.setText("Wrong: "+tmpWrong);
        TxtLevel.setText("Level " +level);
        chronometer.setText(""+counter);
        doStart();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(counter<0){
                    counter = 30;
                    runCorrectWord();
                }
                else{
                    BtnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            doStop();
                            counter = 30;
                            runCorrectWord();
                            doStart();
                        }
                    });
                    chronometer.setText(""+counter);
                    counter--;
                }
            }
        });
    }

    public void Display(int i,ArrayList<Word>List){
        TxtQuestion.setText(randomCharacters(List.get(i).word,List,i));
    }

    public void readDataFromSQLiteLevel1(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM CorrectWord WHERE Level = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Word = cursor.getString(1);
            String Key = cursor.getString(2);
            int Level = cursor.getInt(3);
            ListLevel1.add(new Word(ID,Word,Key,Level));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void readDataFromSQLiteLevel2(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM CorrectWord WHERE Level = 2", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Word = cursor.getString(1);
            String Key = cursor.getString(2);
            int Level = cursor.getInt(3);
            ListLevel2.add(new Word(ID,Word,Key,Level));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void readDataFromSQLiteLevel3(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM CorrectWord WHERE Level = 3", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Word = cursor.getString(1);
            String Key = cursor.getString(2);
            int Level = cursor.getInt(3);
            ListLevel3.add(new Word(ID,Word,Key,Level));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void readDataFromSQLiteLevel4(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM CorrectWord WHERE Level = 4", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Word = cursor.getString(1);
            String Key = cursor.getString(2);
            int Level = cursor.getInt(3);
            ListLevel4.add(new Word(ID,Word,Key,Level));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void readDataFromSQLiteLevel5(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM CorrectWord WHERE Level = 5", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Word = cursor.getString(1);
            String Key = cursor.getString(2);
            int Level = cursor.getInt(3);
            ListLevel5.add(new Word(ID,Word,Key,Level));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void readDataFromSQLiteLevel6(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM CorrectWord WHERE Level = 6", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Word = cursor.getString(1);
            String Key = cursor.getString(2);
            int Level = cursor.getInt(3);
            ListLevel6.add(new Word(ID,Word,Key,Level));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void readDataFromSQLiteLevel7(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM CorrectWord WHERE Level = 7", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Word = cursor.getString(1);
            String Key = cursor.getString(2);
            int Level = cursor.getInt(3);
            ListLevel7.add(new Word(ID,Word,Key,Level));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void readDataFromSQLiteLevel8(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM CorrectWord WHERE Level = 8", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Word = cursor.getString(1);
            String Key = cursor.getString(2);
            int Level = cursor.getInt(3);
            ListLevel8.add(new Word(ID,Word,Key,Level));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void readDataFromSQLiteLevel9(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM CorrectWord WHERE Level = 9", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Word = cursor.getString(1);
            String Key = cursor.getString(2);
            int Level = cursor.getInt(3);
            ListLevel9.add(new Word(ID,Word,Key,Level));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void readDataFromSQLiteLevel10(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.learningenglish/databases/LearningEnglish.db",null);
        Cursor cursor = db.rawQuery("SELECT * FROM CorrectWord WHERE Level = 10", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Word = cursor.getString(1);
            String Key = cursor.getString(2);
            int Level = cursor.getInt(3);
            ListLevel10.add(new Word(ID,Word,Key,Level));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public String randomCharacters(String a, ArrayList<Word> List,int pos){
        String []b;
        b=a.split(" ");
        String characters =""+ (List.get(pos).word);
        while(characters.trim().equals(List.get(pos).word)){
            ArrayList<Integer> num = new ArrayList();
            Random r = new Random();
            int count = 0;
            int value;
            boolean flag;
            while(count < b.length){
                flag = false;
                value = r.nextInt(b.length);
                for (int i=0; i<num.size();i++){
                    if(((Integer)num.get(i))==value){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    num.add(value);
                    count++;
                }
            }
            characters = "";
            for(int tmp:num){
                characters+=b[tmp]+" ";
            }
        }
        return characters.trim();
    }

    public void randomNum(ArrayList<Word> List,ArrayList<Integer> num){
        Random r = new Random();
        int count = 0;
        int value;
        boolean flag;
        while(count < List.size()){
            flag = false;
            value = r.nextInt(List.size());
            for (int i=0; i<num.size();i++){
                if(((Integer)num.get(i))==value){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                num.add(value);
                count++;
            }
        }
    }

    public void runCorrectWord(){
        String answer = Edt.getText().toString();
        if(level==1){
            if(answer.trim().equals(ListLevel1.get(Number1.get(pos1)).key)){
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.correct);
                song.start();
                tmpRight++;
                totalRight++;
                tmpWrong=0;
                level++;
                pos1++;
            }
            else{
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.wrong);
                song.start();
                tmpWrong++;
                totalWrong++;
                tmpRight=0;
                pos1++;
            }
        }
        else if(level==2){
            if(answer.trim().equals(ListLevel2.get(Number2.get(pos2)).key)){
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.correct);
                song.start();
                tmpRight++;
                totalRight++;
                tmpWrong=0;
                level++;
                pos2++;
            }
            else{
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.wrong);
                song.start();
                tmpWrong++;
                totalWrong++;
                tmpRight=0;
                level--;
                pos2++;
            }
        }
        else if(level==3){
            if(answer.trim().equals(ListLevel3.get(Number3.get(pos3)).key)){
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.correct);
                song.start();
                tmpRight++;
                totalRight++;
                tmpWrong=0;
                level++;
                pos3++;
            }
            else{
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.wrong);
                song.start();
                tmpWrong++;
                totalWrong++;
                tmpRight=0;
                level--;
                pos3++;
            }
        }
        else if(level==4){
            if(answer.trim().equals(ListLevel4.get(Number4.get(pos4)).key)){
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.correct);
                song.start();
                tmpRight++;
                totalRight++;
                tmpWrong=0;
                level++;
                pos4++;
            }
            else{
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.wrong);
                song.start();
                tmpWrong++;
                totalWrong++;
                tmpRight=0;
                level--;
                pos4++;
            }
        }
        else if(level==5){
            if(answer.trim().equals(ListLevel5.get(Number5.get(pos5)).key)){
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.correct);
                song.start();
                tmpRight++;
                totalRight++;
                tmpWrong=0;
                level++;
                pos5++;
            }
            else{
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.wrong);
                song.start();
                tmpWrong++;
                totalWrong++;
                tmpRight=0;
                level--;
                pos5++;
            }
        }
        else if(level==6){
            if (answer.trim().equals(ListLevel6.get(Number6.get(pos6)).key)) {
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.correct);
                song.start();
                tmpRight++;
                totalRight++;
                tmpWrong = 0;
                level++;
                pos6++;
            }
            else {
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.wrong);
                song.start();
                tmpWrong++;
                totalWrong++;
                tmpRight = 0;
                level--;
                pos6++;
            }
        }
        else if(level==7){
            if (answer.trim().equals(ListLevel7.get(Number7.get(pos7)).key)) {
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.correct);
                song.start();
                tmpRight++;
                totalRight++;
                tmpWrong = 0;
                level++;
                pos7++;
            }
            else {
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.wrong);
                song.start();
                tmpWrong++;
                totalWrong++;
                tmpRight = 0;
                level--;
                pos7++;
            }
        }
        else if(level==8){
            if (answer.trim().equals(ListLevel8.get(Number8.get(pos8)).key)) {
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.correct);
                song.start();
                tmpRight++;
                totalRight++;
                tmpWrong = 0;
                level++;
                pos8++;
            }
            else {
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.wrong);
                song.start();
                tmpWrong++;
                totalWrong++;
                tmpRight = 0;
                level--;
                pos8++;
            }
        }
        else if(level==9){
            if (answer.trim().equals(ListLevel9.get(Number9.get(pos9)).key)) {
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.correct);
                song.start();
                tmpRight++;
                totalRight++;
                tmpWrong = 0;
                level++;
                pos9++;
            }
            else {
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.wrong);
                song.start();
                tmpWrong++;
                totalWrong++;
                tmpRight = 0;
                level--;
                pos9++;
            }
        }
        else {
            if (answer.trim().equals(ListLevel10.get(Number10.get(pos10)).key)) {
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.correct);
                song.start();
                tmpRight++;
                totalRight++;
                tmpWrong = 0;
                pos10++;
            }
            else {
                MediaPlayer song = MediaPlayer.create(ActivityCorrectWord.this,R.raw.wrong);
                song.start();
                tmpWrong++;
                totalWrong++;
                tmpRight = 0;
                level--;
                pos10++;
            }
        }

        Edt.setText("");

        if(tmpRight==3){
            tmpStar++;
            totalStar++;
            tmpRight=0;
        }
        else if(tmpWrong==3){
            if(tmpStar!=0){
                tmpStar--;
                tmpWrong=0;
            }
            else{
                Intent intent = new Intent(ActivityCorrectWord.this,ActivityResultCorrectWord.class);
                Bundle bundle = new Bundle();
                bundle.putInt("totalCorrect",totalRight);
                bundle.putInt("totalWrong",totalWrong);
                bundle.putInt("totalStar",totalStar);
                bundle.putString("playerName",name);
                intent.putExtra("MyPackage",bundle);
                startActivity(intent);
                finish();
            }
        }

        if(pos1==Number1.size())
            pos1=0;
        else if (pos2==Number2.size())
            pos2=0;
        else if (pos3==Number3.size())
            pos3=0;
        else if (pos4==Number4.size())
            pos4=0;
        else if (pos5==Number5.size())
            pos5=0;
        else if (pos6==Number6.size())
            pos6=0;
        else if (pos7==Number7.size())
            pos7=0;
        else if (pos8==Number8.size())
            pos8=0;
        else if (pos9==Number9.size())
            pos9=0;
        else if (pos10==Number10.size())
            pos10=0;

        TxtStar.setText(""+tmpStar);
        TxtTmpRight.setText("Correct: "+tmpRight);
        TxtTmpWrong.setText("Wrong: "+tmpWrong);
        TxtLevel.setText("Level " +level);

        if(level==1){
            Display(Number1.get(pos1),ListLevel1);
        }
        else if(level==2){
            Display(Number2.get(pos2),ListLevel2);
        }
        else if(level==3){
            Display(Number3.get(pos3),ListLevel3);
        }
        else if(level==4){
            Display(Number4.get(pos4),ListLevel4);
        }
        else if(level==5){
            Display(Number5.get(pos5),ListLevel5);
        }
        else if(level==6) {
            Display(Number6.get(pos6),ListLevel6);
        }
        else if(level==7) {
            Display(Number7.get(pos7),ListLevel7);
        }
        else if(level==8) {
            Display(Number8.get(pos8),ListLevel8);
        }
        else if(level==9) {
            Display(Number9.get(pos9),ListLevel9);
        }
        else {
            Display(Number10.get(pos10),ListLevel10);
        }
    }

    public void doStart(){
        chronometer.start();
    }
    public void doStop(){
        chronometer.stop();
    }

}
