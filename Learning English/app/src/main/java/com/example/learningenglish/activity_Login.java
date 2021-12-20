package com.example.learningenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_Login extends AppCompatActivity {
    private EditText username,password;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    private TextView createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.EditLoginEmail);
        password = findViewById((R.id.EditLoginPwd));
        loginBtn = findViewById((R.id.BtnToLogin));
        createAccount = (TextView) findViewById(R.id.textAlreadyHaveAccount);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_Login.this, activity_Register.class);
                startActivity(intent);
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(username.getText().toString().isEmpty()){
                    username.setError("Email is missing!");
                    return;
               }
                if(password.getText().toString().isEmpty()){
                    password.setError("Email is missing!");
                    return;
                }
                mAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnSuccessListener((new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                            //Login successfully
                        Intent intent = new Intent(activity_Login.this,MainActivity.class);



                        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name",username.getText().toString());
                        editor.apply();
                        startActivity(intent);
                        finish();
                    }
                })).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity_Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(activity_Login.this, MainActivity.class);
             startActivity(intent);
             finish();
        }
    }

}