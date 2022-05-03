package com.example.finalproject.Fragment.AllProduct;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.finalproject.Adapter.ListProductAdater;
import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.Category;
import com.example.finalproject.Models.Product;
import com.example.finalproject.ProductDetailActivity;
import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CategoryFragment1 extends Fragment {
    private View view;
    ListView listView;
    ListProductAdater listProductAdater;
    public CategoryFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Product> productList=new ArrayList<>();
        FirebaseData data=new FirebaseData();
        if(view==null){
            view=inflater.inflate(R.layout.fragment_category1, container, false);
        }
        listView=(ListView) view.findViewById(R.id.listview);
        data.GetData().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    Map singleValue=(Map)ds.getValue();
                    Category Category=ds.child("Category").getValue(Category.class);
                    if(Category.getId().equals("Cate01"))
                    {
                        String Id=ds.getKey();
                        String Name=(String) singleValue.get("Name");
                        String Price=(String)singleValue.get("Price");
                        String Price_Point=(String)singleValue.get("Price_Point");
                        String Quantity=(String)singleValue.get("Quantity");
                        String Image=(String)singleValue.get("Image");
                        productList.add(new Product(Id,Name,Price,Price_Point,Quantity,Image,Category));
                    }
                }
                listProductAdater=new ListProductAdater(getActivity(),productList);
                listView.setDivider(null);
                listView.setAdapter(listProductAdater);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent(getActivity(),ProductDetailActivity.class);
                        intent.putExtra("id",productList.get(i).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}