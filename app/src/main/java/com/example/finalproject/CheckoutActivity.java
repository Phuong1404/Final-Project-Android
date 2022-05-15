package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class CheckoutActivity extends AppCompatActivity {
    TextView price,total;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        price=(TextView) findViewById(R.id.price);
        total=(TextView) findViewById(R.id.total);
        mAuth=FirebaseAuth.getInstance();
        Intent intent = getIntent();
        String id1=intent.getStringExtra("id");
        FirebaseData data= new FirebaseData();
        data.GetDataOrder(mAuth.getCurrentUser().getUid(),id1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               Order order= snapshot.getValue(Order.class);
               price.setText("$"+Double.toString(order.getTotal()));
               total.setText("$"+Double.toString(order.getTotal()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}