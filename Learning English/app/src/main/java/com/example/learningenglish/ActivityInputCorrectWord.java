package com.example.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class ActivityInputCorrectWord extends Activity {
    EditText edtName;
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputname_correctword);

        edtName = (EditText)findViewById(R.id.EdtNameCorrectWord);
        btn = (Button)findViewById(R.id.BtnPlayCorrectWord);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInputCorrectWord.this,ActivityCorrectWord.class);
                Bundle bundle = new Bundle();
                bundle.putString("playerName",edtName.getText().toString());
                intent.putExtra("Information",bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}
