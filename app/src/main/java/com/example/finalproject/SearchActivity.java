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
        List<Product> productList=new ArrayList<>();
        if(Key!=null) {


            data.GetData().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    productList.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Map singleValue = (Map) ds.getValue();
                        String Name = (String) singleValue.get("name");
                        if (Name.contains(Key)) {
                            String Id = ds.getKey();
                            String Price = (String) singleValue.get("price");
                            String Price_Point = (String) singleValue.get("price_Point");
                            String Quantity = (String) singleValue.get("quantity");
                            String Image = (String) singleValue.get("image");
                            Category Category = ds.child("category").getValue(Category.class);
                            productList.add(new Product(Id, Name, Price, Price_Point, Quantity, Image, Category));
                        }
                    }

                    if(productList.size()>0)
                    {
                        keyword.setText("Search results for : "+Key);
                        listProductAdater = new ListProductAdater(SearchActivity.this, productList);
                        listView.setDivider(null);
                        listView.setAdapter(listProductAdater);
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