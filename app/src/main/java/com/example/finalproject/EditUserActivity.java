package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.User;
import com.example.finalproject.ui.Profile;
import com.google.firebase.auth.FirebaseAuth;

public class EditUserActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView update;
    EditText name,address,email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        update=findViewById(R.id.update);
        mAuth=FirebaseAuth.getInstance();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }
    private void updateProfile(){
        String Name=name.getText().toString();
        String Address=address.getText().toString();
        String Email=email.getText().toString();
        String Phone=phone.getText().toString();
        if(TextUtils.isEmpty(Name)){
            name.setError("Name cannot be empty");
            name.requestFocus();
        }
        else if(TextUtils.isEmpty(Address)){
            address.setError("Address cannot be empty");
            address.requestFocus();
        }
        else if(TextUtils.isEmpty(Email)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }
        else if(TextUtils.isEmpty(Phone)){
            phone.setError("Phone cannot be empty");
            phone.requestFocus();
        }
        else{
            FirebaseData data=new FirebaseData();
            User user=new User("",Name,"",Address,Email,Phone,0,0,"");
            data.updataProfile(mAuth.getCurrentUser().getUid(),user);
            startActivity(new Intent(EditUserActivity.this, Profile.class));
        }
    }
}