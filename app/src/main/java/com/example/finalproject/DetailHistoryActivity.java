package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.finalproject.Adapter.HistoryDetailAdater;
import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.Cart;
import com.example.finalproject.Models.Detail1;
import com.example.finalproject.Models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailHistoryActivity extends AppCompatActivity {

    HistoryDetailAdater adater;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        List<Detail1> listDetail1=new ArrayList<>();
        FirebaseData data=new FirebaseData();
        listView=(ListView) findViewById(R.id.listview);
        listView.setEnabled(false);
        data.GetDataHistoryDetail("User01","Order01").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDetail1.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    Map singleValue=(Map)ds.getValue();
                    String Id=ds.getKey();
                    String Quantity= (String) singleValue.get("Quantity");
                    String Total= (String) singleValue.get("Total");
                    Product product=ds.child("product").getValue(Product.class);
                    listDetail1.add(new Detail1(Id,product,Total,Quantity));
                }
                listView.getLayoutParams().height=382*listDetail1.size();
                adater=new HistoryDetailAdater(DetailHistoryActivity.this,listDetail1);
                listView.setDivider(null);
                listView.setAdapter(adater);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}