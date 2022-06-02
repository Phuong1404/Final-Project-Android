package com.example.finalproject.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.AllProductActivity;
import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.LoginActivity;
import com.example.finalproject.MainActivity;
import com.example.finalproject.MainadminActivity;
import com.example.finalproject.ProductDetailActivity;
import com.example.finalproject.R;
import com.example.finalproject.SearchActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class Home extends Fragment {
    private View view;
    TextView AllProduct,logout;
    FirebaseAuth mAuth;
    EditText searchtext;
    ImageView menubar,search,cart;

    public Home() {
        mAuth=FirebaseAuth.getInstance();
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
        search=view.findViewById(R.id.search);
        searchtext=view.findViewById(R.id.searchtext);
        AllProduct=view.findViewById(R.id.AllProduct);
        logout=view.findViewById(R.id.logout);
        menubar=view.findViewById(R.id.menubar);
        cart=view.findViewById(R.id.menubar);

        FirebaseData data=new FirebaseData();
        data.GetDataUser(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map singleValue=(Map)snapshot.getValue();
                logout.setText((String)singleValue.get("name"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //------------------------------------------------------------------------------
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //------------------------------------------------------------------------------


            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Search=searchtext.getText().toString();
                    if(!Search.equals(""))
                    {
                        Intent i=new Intent(getActivity(), SearchActivity.class);
                        i.putExtra("keyword",Search);
                        startActivity(i);
                    }
                }
            });


        AllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AllProductActivity.class);
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