package com.example.finalproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finalproject.Adapter.AcceptItemAdater;
import com.example.finalproject.Adapter.HistoryDetailAdater;
import com.example.finalproject.DetailHistoryActivity;
import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.LoginActivity;
import com.example.finalproject.Models.Detail1;
import com.example.finalproject.Models.Product;
import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailOrderAcceptActivity extends AppCompatActivity {

    ListView listView;
    FirebaseAuth mAuth;
    AcceptItemAdater adater;
    TextView total1;
    TextView subtotal1;
    TextView Accept, Cancel;
    Double Total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_accept);
        List<Detail1> listDetail1=new ArrayList<>();
        FirebaseData data=new FirebaseData();
        listView=(ListView) findViewById(R.id.listview);
        mAuth= FirebaseAuth.getInstance();
        listView.setEnabled(false);
        if(mAuth.getCurrentUser().getUid()==null)
        {
            startActivity(new Intent(DetailOrderAcceptActivity.this, LoginActivity.class));
        }
        Intent intent=getIntent();
        String Idbill= intent.getStringExtra("id");
        total1=findViewById(R.id.total);
        subtotal1=findViewById(R.id.subtotal);
        Accept=findViewById(R.id.accept);
        Cancel=findViewById(R.id.cancel);
        data.GetOneData(Idbill).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDetail1.clear();
                Total=0.0;
                    for(DataSnapshot ds:snapshot.child("detail").getChildren())
                    {
                        Map singleValue=(Map)ds.getValue();
                        int Quantity=((Long) singleValue.get("quanlity")).intValue();
                        String OrderRequest=(String)singleValue.get("orderRequest");
                        Product product= ds.child("product").getValue(Product.class);
                        double total=((Long) singleValue.get("total")).doubleValue();
                        String CartId=(String)singleValue.get("idCart");
                        listDetail1.add(new Detail1(ds.getKey(),product,total,Quantity,OrderRequest,CartId));
                    }
                listView.getLayoutParams().height=230*listDetail1.size();
                adater=new AcceptItemAdater(DetailOrderAcceptActivity.this,listDetail1);
                listView.setDivider(null);
                listView.setAdapter(adater);
                subtotal1.setText("$"+Total);
                total1.setText("$"+Total);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               data.AcceptOrder(mAuth.getCurrentUser().getUid(),Idbill);
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.CancelOrder(mAuth.getCurrentUser().getUid(),Idbill);
            }
        });
    }
}