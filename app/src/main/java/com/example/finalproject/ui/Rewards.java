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
import com.example.finalproject.Adapter.ListRewardAdater;
import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.MainActivity;
import com.example.finalproject.Models.Detail;
import com.example.finalproject.Models.Product;
import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Rewards extends Fragment {

    private View view;
    ListView listView;
    ListRewardAdater adater;
    private ImageView BtnPre1;
    ImageView menubar;
    public Rewards() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.fragment_rewards, container, false);
        }
        FirebaseData data=new FirebaseData();
        List<Detail> listDetail=new ArrayList<>();
        listView=(ListView) view.findViewById(R.id.listview_Reward);
        listView.setEnabled(false);
        data.GetDetail("User01").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDetail.clear();
                //Product product=new Product();
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    Map singleValue=(Map)ds.getValue();
                    String Id=ds.getKey();
                    Product product= (Product) ds.child("product").getValue(Product.class);
                    String date= (String) singleValue.get("date");
                    listDetail.add(new Detail(Id,product,date));
                }
                listView.getLayoutParams().height=350*listDetail.size();
                adater=new ListRewardAdater(getActivity(),listDetail);
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