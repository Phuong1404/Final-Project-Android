package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finalproject.Adapter.ListProductAdater;
import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.Category;
import com.example.finalproject.Models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    ListView listView;
    TextView keyword;
    String Key;
    List<Product> productList;
    ListProductAdater listProductAdater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView=findViewById(R.id.list_search);
        keyword=findViewById(R.id.keyword);
        FirebaseData data=new FirebaseData();
        Intent intent=getIntent();
        Key=intent.getStringExtra("keyword");
        if(Key!=null) {


            data.GetData().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    productList.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Map singleValue = (Map) ds.getValue();
                        String Name = (String) singleValue.get("Name");
                        if (Name.contains(Key)) {
                            String Id = ds.getKey();
                            String Price = (String) singleValue.get("Price");
                            String Price_Point = (String) singleValue.get("Price_Point");
                            String Quantity = (String) singleValue.get("Quantity");
                            String Image = (String) singleValue.get("Image");
                            Category Category = ds.child("Category").getValue(Category.class);
                            productList.add(new Product(Id, Name, Price, Price_Point, Quantity, Image, Category));
                        }
                    }
                    listProductAdater = new ListProductAdater(SearchActivity.this, productList);
                    listView.setDivider(null);
                    listView.setAdapter(listProductAdater);
                    if(productList.size()>0)
                    {
                        keyword.setText("Search results for : "+Key);
                    }
                    else
                    {
                        keyword.setText("No search results for : "+Key);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}