package com.example.learningenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_Register extends AppCompatActivity {
    EditText registerEmail, registerPwd, registerConfirmPwd;
    Button signUpBtn;
    TextView BackToLogin;
    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerEmail =(EditText)findViewById(R.id.EditRegisterEmail);
        registerPwd  =(EditText)findViewById(R.id.EditRegisterPwd);
        registerConfirmPwd  =(EditText)findViewById(R.id.EditRegisterConfirmPwd);

        signUpBtn = (Button)findViewById(R.id.BtnToSignUp);
        BackToLogin = (TextView) findViewById(R.id.textAlreadyHaveAccountRegister);

        fAuth = FirebaseAuth.getInstance();
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String email = registerEmail.getText().toString();
              String password = registerPwd.getText().toString();
              String confPwd = registerConfirmPwd.getText().toString();
              if(email.isEmpty()){
                  registerEmail.setError("Email is required!!");
                  return;
              }
                if(password.isEmpty()){
                    registerPwd.setError("Password is required!!");
                    return;
                }
                if(confPwd.isEmpty()){
                    registerConfirmPwd.setError("Confirmed password is required!!");
                    return;
                }
                if(!password.equals(confPwd)){
                    registerConfirmPwd.setError("Password doesn't match.");
                }
                Toast.makeText(activity_Register.this, "Data validated", Toast.LENGTH_SHORT).show();
                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(activity_Register.this, MainActivity.class);
                        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name",registerEmail.getText().toString());
                        editor.apply();
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity_Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        BackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_Register.this, activity_Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}