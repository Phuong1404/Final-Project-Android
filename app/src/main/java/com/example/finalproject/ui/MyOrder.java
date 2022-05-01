package com.example.finalproject.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.finalproject.Adapter.ListOrderAdater;
import com.example.finalproject.Adapter.ListProductAdater;
import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.MainActivity;
import com.example.finalproject.Models.Cart;
import com.example.finalproject.Models.Product;
import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyOrder extends Fragment {
    private View view;
    ListView listView;
    ListOrderAdater adater;
    private ImageView BtnPre1;
    ImageView menubar;
    public MyOrder() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.fragment_my_order, container, false);
        }
        List<Cart>listCart=new ArrayList<>();
        FirebaseData data=new FirebaseData();
        listView=(ListView) view.findViewById(R.id.Lisview_Order);
        data.GetDataCart("User01").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listCart.clear();
                Product product=new Product();
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    Map singleValue=(Map)ds.getValue();
                    int Quantity=((Long) singleValue.get("Quantity")).intValue();
                    String OrderRequest=(String)singleValue.get("OrderRequest");
                    double Total=((Long) singleValue.get("Total")).doubleValue();
                    listCart.add(new Cart(Quantity,product,OrderRequest,Total));
                }
                adater=new ListOrderAdater(getActivity(),listCart);
                listView.setDivider(null);
                listView.setAdapter(adater);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        BtnPre1=view.findViewById(R.id.BtnPre1);
        menubar=view.findViewById(R.id.menubar);
        BtnPre1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });
        menubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).open();
            }
        });
        return view;
    }
}