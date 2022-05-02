package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailActivity extends AppCompatActivity {
    String Size,Roas,Grind,Ice;
    double Total;
    int Quanlity;
    TextView Quan1;
    ImageView SizeM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        //region Declare
        Quan1=(TextView)findViewById(R.id.Quantity1);
        SizeM=(ImageView)findViewById(R.id.sizeM);
        //endregion
        Size="M";Roas="Small";Grind="Small";Ice="Half";Quanlity=1;

    }
    public void OnClick(View view){
        int id=view.getId();
        switch (id){
            case R.id.buttonminus:
                Minus();
                break;
            case R.id.buttonplus:
                Plus();
                break;
            case R.id.imageminus:
                Minus();
                break;
            case R.id.imageplus:
                Plus();
                break;
            case R.id.sizeM:
                ChangeSize();
                break;
        }
    }
    public void Plus(){
        if(Quanlity<10){
            Quanlity=Quanlity+1;
            Quan1.setText(Integer.toString(Quanlity));
        }
    }
    public void Minus(){
        if(Quanlity>1){
            Quanlity=Quanlity-1;
            Quan1.setText(Integer.toString(Quanlity));
        }
    }
    public void ChangeSize(){
        SizeM.setImageResource(R.drawable.coffee);
    }
}