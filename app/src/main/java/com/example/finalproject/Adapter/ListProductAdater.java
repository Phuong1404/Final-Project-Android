package com.example.finalproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.finalproject.Models.Product;
import com.example.finalproject.ProductDetailActivity;
import com.example.finalproject.R;

import java.util.List;

public class ListProductAdater extends ArrayAdapter<Product> {
    Context context;
    List<Product>productList;
    TextView Btn;
    public ListProductAdater(@NonNull Context context,List<Product>dataproduct){
        super(context, R.layout.product_item,dataproduct);
        this.context=context;
        this.productList=dataproduct;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,null,true);
        TextView name=view.findViewById(R.id.Name);
        TextView category=view.findViewById(R.id.Category);
        TextView price=view.findViewById(R.id.Price);

        name.setText(productList.get(position).getName());
        category.setText(productList.get(position).getCategory().getName());
        price.setText(productList.get(position).getPrice());
        Btn=(TextView)view.findViewById(R.id.BtnBuy);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data=productList.get(position).getName();
                view.getContext().startActivity(new Intent(context, ProductDetailActivity.class));
            }
        });
        return view;
    }
}
