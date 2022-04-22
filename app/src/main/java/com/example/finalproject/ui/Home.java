package com.example.finalproject.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproject.AllProductActivity;
import com.example.finalproject.R;


public class Home extends Fragment {
    private View view;
    TextView AllProduct;
    public Home() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.fragment_home, container, false);
        }
        AllProduct=view.findViewById(R.id.AllProduct);
        //------------------------------------------------------------------------------

        AllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AllProductActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}