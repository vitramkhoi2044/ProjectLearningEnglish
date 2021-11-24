package com.example.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class ActivityCorrectWord extends Activity {
    ArrayList<Word> ListLevel1 = new ArrayList<>();
    ArrayList<Word> ListLevel2 = new ArrayList<>();
    ArrayList<Word> ListLevel3 = new ArrayList<>();
    ArrayList<Word> ListLevel4 = new ArrayList<>();
    ArrayList<Word> ListLevel5 = new ArrayList<>();
    ArrayList<Word> ListLevel6 = new ArrayList<>();
    ArrayList<Integer> Number1 = new ArrayList<>();
    ArrayList<Integer> Number2 = new ArrayList<>();
    ArrayList<Integer> Number3 = new ArrayList<>();
    ArrayList<Integer> Number4 = new ArrayList<>();
    ArrayList<Integer> Number5 = new ArrayList<>();
    ArrayList<Integer> Number6 = new ArrayList<>();
    TextView TxtQuestion,TxtStar;
    Button BtnSubmit;
    EditText Edt;
    int pos1 = 0;
    int pos2 = 0;
    int pos3 = 0;
    int pos4 = 0;
    int pos5 = 0;
    int pos6 = 0;
    int level = 3;
    int tmpRight = 0;
    int tmpWrong = 0;
    int tmpStar = 0;
    int totalRight = 0;
    int totalWrong = 0;
    int totalStar = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correctwords);

        TxtQuestion = (TextView)findViewById(R.id.TxtQuestion);
        BtnSubmit = (Button)findViewById(R.id.BtnSubmit);
        Edt = (EditText)findViewById(R.id.EdtAnswer);
        TxtStar = (TextView)findViewById(R.id.TxtTotalStar);
        readDataFromSQLiteLevel1();
        readDataFromSQLiteLevel2();
        readDataFromSQLiteLevel3();
        readDataFromSQLiteLevel4();
        readDataFromSQLiteLevel5();
        readDataFromSQLiteLevel6();
        randomNum(ListLevel1,Number1);
        randomNum(ListLevel2,Number2);
        randomNum(ListLevel3,Number3);
        randomNum(ListLevel4,Number4);
        randomNum(ListLevel5,Number5);
        randomNum(ListLevel6,Number6);
        Display(Number3.get(pos3),ListLevel3,Number3.get(pos3));
        TxtStar.setText(""+tmpStar);
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = Edt.getText().toString();
                if(level==1){
                    if(answer.trim().equals(ListLevel1.get(Number1.get(pos1)).key)){
                        tmpRight++;
                        totalRight++;
                        tmpWrong=0;
                        level++;
                        pos1++;
                    }
                    else{
                        tmpWrong++;
                        totalWrong++;
                        tmpRight=0;
                        pos1++;
                    }
                }
                else if(level==2){
                    if(answer.trim().equals(ListLevel2.get(Number2.get(pos2)).key)){
                        tmpRight++;
                        totalRight++;
                        tmpWrong=0;
                        level++;
                        pos2++;
                    }
                    else{
                        tmpWrong++;
                        totalWrong++;
                        tmpRight=0;
                        level--;
                        pos2++;
                    }
                }
                else if(level==3){
                    if(answer.trim().equals(ListLevel3.get(Number3.get(pos3)).key)){
                        tmpRight++;
                        totalRight++;
                        tmpWrong=0;
                        level++;
                        pos3++;
                    }
                    else{
                        tmpWrong++;
                        totalWrong++;
                        tmpRight=0;
                        level--;
                        pos3++;
                    }
                }
                else if(level==4){
                    if(answer.trim().equals(ListLevel4.get(Number4.get(pos4)).key)){
                        tmpRight++;
                        totalRight++;
                        tmpWrong=0;
                        level++;
                        pos4++;
                    }
                    else{
                        tmpWrong++;
                        totalWrong++;
                        tmpRight=0;
                        level--;
                        pos4++;
                    }
                }else if(level==5){
                    if(answer.trim().equals(ListLevel5.get(Number5.get(pos5)).key)){
                        tmpRight++;
                        totalRight++;
                        tmpWrong=0;
                        level++;
                        pos5++;
                    }
                    else{
                        tmpWrong++;
                        totalWrong++;
                        tmpRight=0;
                        level--;
                        pos5++;
                    }
                }else {
                    if (answer.trim().equals(ListLevel6.get(Number6.get(pos6)).key)) {
                        tmpRight++;
                        totalRight++;
                        tmpWrong = 0;
                        pos6++;
                    }
                    else {
                        tmpWrong++;
                        totalWrong++;
                        tmpRight = 0;
                        level--;
                        pos6++;
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

                TxtStar.setText(""+tmpStar);

                if(level==1){
                    Display(Number1.get(pos1),ListLevel1,Number1.get(pos1));
                }
                else if(level==2){
                    Display(Number2.get(pos2),ListLevel2,Number2.get(pos2));
                }
                else if(level==3){
                    Display(Number3.get(pos3),ListLevel3,Number3.get(pos3));
                }
                else if(level==4){
                    Display(Number4.get(pos4),ListLevel4,Number4.get(pos4));
                }else if(level==5){
                    Display(Number5.get(pos5),ListLevel5,Number5.get(pos5));
                }else {
                    Display(Number6.get(pos6),ListLevel6,Number6.get(pos6));
                }

            }
        });
    }

    public void Display(int i,ArrayList<Word>List,int pos){
        TxtQuestion.setText(randomCharacters(List.get(i).word,List,pos));
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
}
