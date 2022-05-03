package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ProductDetailActivity extends AppCompatActivity {
    String Size,Roas,Grind,Ice;
    double Total;
    int Quanlity;
    TextView Quan1,Name,total;
    ImageView SizeM,SizeL,SizeXL,SmallFire,MediumFire,BigFire,GrindSmall,GrindBig,NoneIce,HalfIce,FullIce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        //region Declare
        total=(TextView)findViewById(R.id.total);
        Name=(TextView)findViewById(R.id.name);
        Quan1=(TextView)findViewById(R.id.Quantity1);
        SizeM=(ImageView)findViewById(R.id.sizeM);
        SizeL=(ImageView)findViewById(R.id.sizeL);
        SizeXL=(ImageView)findViewById(R.id.sizeXL);
        SmallFire=(ImageView)findViewById(R.id.smallfire);
        MediumFire=(ImageView)findViewById(R.id.mediumfire);
        BigFire=(ImageView)findViewById(R.id.bigfire);
        GrindSmall=(ImageView)findViewById(R.id.grindsmall);
        GrindBig=(ImageView)findViewById(R.id.grindbig);
        NoneIce=(ImageView)findViewById(R.id.noneice);
        HalfIce=(ImageView)findViewById(R.id.halfice);
        FullIce=(ImageView)findViewById(R.id.fullice);
        //endregion
        Size="M";Roas="Small";Grind="Small";Ice="Half";Quanlity=1;
        FirebaseData data=new FirebaseData();
        Intent intent = getIntent();
        String id1=intent.getStringExtra("id");
        data.GetDataProduct(id1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Product data1=new Product();
                data1=snapshot.getValue(Product.class);
                Total=Double.parseDouble(data1.getPrice());
                Name.setText(data1.getName());
                total.setText("$ "+data1.getPrice());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                ChangeSize(R.id.sizeM);
                break;
            case R.id.sizeL:
                ChangeSize(R.id.sizeL);
                break;
            case R.id.sizeXL:
                ChangeSize(R.id.sizeXL);
                break;
            case R.id.smallfire:
                ChangeRoast(R.id.smallfire);
                break;
            case R.id.mediumfire:
                ChangeRoast(R.id.mediumfire);
                break;
            case R.id.bigfire:
                ChangeRoast(R.id.bigfire);
                break;
            case R.id.grindsmall:
                ChangeGrind(R.id.grindsmall);
                break;
            case R.id.grindbig:
                ChangeGrind(R.id.grindbig);
                break;
            case R.id.noneice:
                ChangeIce(R.id.noneice);
                break;
            case R.id.halfice:
                ChangeIce(R.id.halfice);
                break;
            case R.id.fullice:
                ChangeIce(R.id.fullice);
                break;

        }
    }
    public void Plus(){
        if(Quanlity<10){
            Total=Total+(Total/Quanlity);
            Quanlity=Quanlity+1;
            Quan1.setText(Integer.toString(Quanlity));

        }
        total.setText("$ "+Double.toString(Total));
    }
    public void Minus(){
        if(Quanlity>1){
            Total=Total-(Total/Quanlity);
            Quanlity=Quanlity-1;
            Quan1.setText(Integer.toString(Quanlity));

        }
        total.setText("$ "+Double.toString(Total));
    }
    public void ChangeSize(int Id){
        if(Id==R.id.sizeM){
            if(Size=="L")
            {
                Total=Total-(2*Quanlity);
            }else if(Size=="XL")
            {
                Total=Total-(4*Quanlity);
            }
            SizeM.setImageResource(R.drawable.coffee);
            SizeL.setImageResource(R.drawable.coffeecup);
            SizeXL.setImageResource(R.drawable.coffeecup);
            Size="M";
        }
        else if(Id==R.id.sizeL){
            if(Size=="M")
            {
                Total=Total+(2*Quanlity);
            }else if(Size=="XL")
            {
                Total=Total-(2*Quanlity);
            }
            SizeL.setImageResource(R.drawable.coffee);
            SizeM.setImageResource(R.drawable.coffeecup);
            SizeXL.setImageResource(R.drawable.coffeecup);
            Size="L";
        }
        else{
            if(Size=="L")
            {
                Total=Total+(2*Quanlity);
            }else if(Size=="M")
            {
                Total=Total+(4*Quanlity);
            }
            SizeXL.setImageResource(R.drawable.coffee);
            SizeM.setImageResource(R.drawable.coffeecup);
            SizeL.setImageResource(R.drawable.coffeecup);
            Size="XL";
        }
        total.setText("$ "+Double.toString(Total));
    }
    public void ChangeRoast(int Id){
        if(Id==R.id.smallfire)
        {
            SmallFire.setImageResource(R.drawable.fire);
            MediumFire.setImageResource(R.drawable.fire1);
            BigFire.setImageResource(R.drawable.fire1);
            Roas="Small";
        }
        else if(Id==R.id.mediumfire){
            SmallFire.setImageResource(R.drawable.fire1);
            MediumFire.setImageResource(R.drawable.fire);
            BigFire.setImageResource(R.drawable.fire1);
            Roas="Medium";
        }
        else{
            SmallFire.setImageResource(R.drawable.fire1);
            MediumFire.setImageResource(R.drawable.fire1);
            BigFire.setImageResource(R.drawable.fire);
            Roas="Big";
        }
    }
    public void ChangeGrind(int Id){
        if(Id==R.id.grindsmall){
            GrindSmall.setImageResource(R.drawable.coffeebean);
            GrindBig.setImageResource(R.drawable.coffeeb1);
            Grind="Small";
        }
        else{
            GrindSmall.setImageResource(R.drawable.coffeeb1);
            GrindBig.setImageResource(R.drawable.coffeebean);
            Grind="Big";
        }
    }
    public void ChangeIce(int Id){
        if(Id==R.id.noneice){
            NoneIce.setImageResource(R.drawable.ice1);
            HalfIce.setImageResource(R.drawable.icecubes);
            FullIce.setImageResource(R.drawable.icecubes);
            Ice="None";
        }
        else if(Id==R.id.halfice){
            NoneIce.setImageResource(R.drawable.ice);
            HalfIce.setImageResource(R.drawable.icecubes1);
            FullIce.setImageResource(R.drawable.icecubes);
            Ice="Half";
        }
        else{
            NoneIce.setImageResource(R.drawable.ice);
            HalfIce.setImageResource(R.drawable.icecubes);
            FullIce.setImageResource(R.drawable.icecubes1);
            Ice="Full";
        }
    }
}