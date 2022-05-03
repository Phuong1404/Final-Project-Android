package com.example.finalproject.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.AllProductActivity;
import com.example.finalproject.LoginActivity;
import com.example.finalproject.MainActivity;
import com.example.finalproject.ProductDetailActivity;
import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;


public class Home extends Fragment {
    private View view;
    TextView AllProduct,logout;
    FirebaseAuth mAuth;
    ImageView menubar;

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
        AllProduct=view.findViewById(R.id.AllProduct);
        logout=view.findViewById(R.id.logout);
        menubar=view.findViewById(R.id.menubar);

        //------------------------------------------------------------------------------

        AllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AllProductActivity.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
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
    public void OpenDetail(View view){
        int id=view.getId();
        if(id==R.id.item1){
            Intent intent=new Intent(getActivity(), ProductDetailActivity.class);
            intent.putExtra("ProductId","Pro01");
        }
    }
}