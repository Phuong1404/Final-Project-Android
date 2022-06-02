package com.example.finalproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class DetailUserActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText name,address,email,phone;
    TextView update;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        update=findViewById(R.id.changerole);
        mAuth=FirebaseAuth.getInstance();
        FirebaseData data=new FirebaseData();
        data.GetDataUser(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map singleValue=(Map)snapshot.getValue();
                String Id1=snapshot.getKey();
                String Name1= (String) singleValue.get("name");
                String Phone1= (String) singleValue.get("phone");
                String Address1= (String) singleValue.get("address");
                String Email1=(String) singleValue.get("email");
                name.setText(Name1);
                address.setText(Address1);
                email.setText(Email1);
                phone.setText(Phone1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder=new AlertDialog.Builder(DetailUserActivity.this);
                builder.setTitle("Are you sure you want to set admin rights for this user");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        data.ChangeRole(mAuth.getCurrentUser().getUid());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog=builder.create();
                dialog.show();
            }
        });
    }
}