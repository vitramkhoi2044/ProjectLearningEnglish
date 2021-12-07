package com.example.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class activity_nhap extends Activity {
    Button BT1;
    EditText Name, NumberOfQuestions;
    String playerName;
    String noQuestion;
    int finalNoQuestion ;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap);
        BT1 = (Button)findViewById(R.id.BtnPlayEnglishQuiz);
        Name = (EditText) findViewById(R.id.EdtPlayerName);
        NumberOfQuestions = (EditText) findViewById(R.id.EdtTheNumber);

        playerName = Name.getText().toString();
        noQuestion = NumberOfQuestions.getText().toString();
//        finalNoQuestion = Integer.parseInt(noQuestion);
        BT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_nhap.this, activity_EnglishQuiz.class);

                Bundle bundle = new Bundle();
                bundle.putString("name", Name.getText().toString());
                bundle.putString("noQuestion", NumberOfQuestions.getText().toString());
                intent.putExtra("MyInfoEnglishQuiz", bundle);
                startActivity(intent);
                Log.v("EditText", NumberOfQuestions.getText().toString());
            }
        });
    }
}
