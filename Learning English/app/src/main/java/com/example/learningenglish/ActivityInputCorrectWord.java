package com.example.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ActivityInputCorrectWord extends Activity {
    private EditText edtName;
    private Button btn,btn1;
    private TextView txtWarning;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputname_correctword);
        edtName = (EditText)findViewById(R.id.EdtNameCorrectWord);
        btn = (Button)findViewById(R.id.BtnPlayCorrectWord);
        btn1 = (Button)findViewById(R.id.BtnCorrectWordTutorial);
        txtWarning = (TextView)findViewById(R.id.TxtWarningCorrectWord);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().toString().length()>0 && edtName.getText().toString().length()<=10) {
                    Intent intent = new Intent(ActivityInputCorrectWord.this,ActivityCorrectWord.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("playerName",edtName.getText().toString());
                    intent.putExtra("Information",bundle);
                    startActivity(intent);
                    finish();
                }
                else{
                    edtName.setText("");
                    txtWarning.setText("The name only 10 characters and not empty");
                }

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInputCorrectWord.this,ActivityTutorialCorrectWord.class);
                startActivity(intent);
            }
        });
    }
}
