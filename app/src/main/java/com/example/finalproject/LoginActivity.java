package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView Login;
    TextView ResetPass;
    TextView CreateAccount;
    EditText email,password;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login=(TextView) findViewById(R.id.Login);
        ResetPass=(TextView) findViewById(R.id.ResetPass);
        CreateAccount=(TextView) findViewById(R.id.CreateAccount);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        mAuth=FirebaseAuth.getInstance();

        ResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ResetPassActivity.class);
                startActivity(i);
            }
        });
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void LoginUser(){
        String Email=email.getText().toString();
        String Password=password.getText().toString();
        if(TextUtils.isEmpty(Email)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if(TextUtils.isEmpty(Password)) {
            password.setError("Password cannot be empty");
            password.requestFocus();
        }
        else{
            mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"User logged in successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Log in Error "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
}

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user==null)
        {
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoginUser();
                }
            });
        }
    }
}