package com.example.finalproject.Fragment.AllProduct;

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
import com.example.finalproject.Models.Product;
import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment3 extends Fragment {
    private View view;
    ListView listView;
    ListProductAdater listProductAdater;
    public CategoryFragment3() {
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
            view=inflater.inflate(R.layout.fragment_category3, container, false);
        }
        listView=(ListView) view.findViewById(R.id.listview);
        data.GetData().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    Product product=ds.getValue(Product.class);
                    if(product.getCategory().getId().equals("2"))
                    {
                        productList.add(ds.getValue(Product.class));
                    }
                }
                listProductAdater=new ListProductAdater(getActivity(),productList);
                listView.setDivider(null);
                listView.setAdapter(listProductAdater);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getActivity().getApplication(),"Click",Toast.LENGTH_SHORT).show();
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