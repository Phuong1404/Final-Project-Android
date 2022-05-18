package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.Cart;
import com.example.finalproject.Models.Detail;
import com.example.finalproject.Models.Detail1;
import com.example.finalproject.Models.Order;
import com.example.finalproject.Models.Product;
import com.example.finalproject.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class CheckoutActivity extends AppCompatActivity {
    TextView price,total,Accept;
    FirebaseAuth mAuth;
    List<Detail1> detailList;
    User user;
    double Total;
    FirebaseData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        price=(TextView) findViewById(R.id.price);
        total=(TextView) findViewById(R.id.total);
        Accept=(TextView)findViewById(R.id.Accept);
        mAuth=FirebaseAuth.getInstance();
        user=new User();
        detailList=new ArrayList<>();
        Intent intent = getIntent();
        String id1=intent.getStringExtra("id");
        data= new FirebaseData();
        data.GetDataUser(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map singleValue=(Map)snapshot.getValue();
                String Id1=snapshot.getKey();
                String Name1= (String) singleValue.get("name");
                String Phone1= (String) singleValue.get("phone");
                //String Address1= (String) singleValue.get("address");
                String Email1=(String) singleValue.get("email");
                int GiftPoint= ((Long) singleValue.get("giftPoint")).intValue();
                int Point1=((Long) singleValue.get("accumulatedPoint")).intValue();
                user=new User(Id1,Name1,"","",Email1,Phone1,GiftPoint,Point1,"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //------------------------------------------------------------------------------------------
        data.GetOrderDetailTemp(mAuth.getCurrentUser().getUid(),id1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                detailList.clear();
               Order order= snapshot.getValue(Order.class);
               Total=0;
               for(DataSnapshot ds:snapshot.child("detail").getChildren())
               {
                   Map singleValue=(Map)ds.getValue();
                   int Quantity=((Long) singleValue.get("quanlity")).intValue();
                   String OrderRequest=(String)singleValue.get("orderRequest");
                   Product product= ds.child("product").getValue(Product.class);
                   double total=((Long) singleValue.get("total")).doubleValue();
                   String CartId=(String)singleValue.get("orderRequest");
                   Total=Total+total;
                   detailList.add(new Detail1(ds.getKey(),product,total,Quantity,OrderRequest,CartId));
               }
                if(order!=null){
                    price.setText("$"+Double.toString(order.getTotal()));
                    total.setText("$"+Double.toString(order.getTotal()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddOrder(user,detailList);
            }
        });
    }
    public void AddOrder(User user, List<Detail1>listDetail){
        Date date=new Date();
        String Id=getMd5(user.getAddress()+user.getName()+user.getPhoneNumber()+"Confirmation"+date.toString());
        Order order=new Order(Id,user.getAddress(),user.getName(),user.getPhoneNumber(),"Confirmation",new SimpleDateFormat("MM/dd/yyyy").format(date),Total);
        data.AddOrder(mAuth.getCurrentUser().getUid(),order);
        int giftpoint=user.getGiftPoint();
        int point=user.getAccumulatedPoint();
        for(Detail1 dt:listDetail){
            Detail1 detail1=new Detail1(dt.getId(),dt.getProduct(), dt.getTotal(),dt.getQuanlity(),dt.getOrderRequest(),dt.getIdCart());
            data.AddOrderDetailTemp(mAuth.getCurrentUser().getUid(),Id,detail1);
            data.AddDetail(mAuth.getCurrentUser().getUid(),new Detail(dt.getId(),dt.getProduct(),new SimpleDateFormat("MM/dd/yyyy").format(date)));
            giftpoint=giftpoint+250;
        }
        data.updataPoint(mAuth.getCurrentUser().getUid(),giftpoint,point+1);
        data.deleteTemp(mAuth.getCurrentUser().getUid());
        startActivity(new Intent(CheckoutActivity.this,SuccessOrderActivity.class));

    }
    static String getMd5(String input)
    {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}