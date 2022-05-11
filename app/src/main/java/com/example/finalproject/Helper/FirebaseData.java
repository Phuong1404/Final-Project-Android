package com.example.finalproject.Helper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseData {
    private String Default_Url="https://androiproject-a386e-default-rtdb.asia-southeast1.firebasedatabase.app/";
    DatabaseReference mDatabase;
    FirebaseDatabase database= FirebaseDatabase.getInstance(Default_Url);
    public DatabaseReference GetData()
    {

        return database.getReference("Products");
    }
    public void SetData()
    {
        mDatabase=database.getReference("Test");
        mDatabase.setValue("Test6");
    }
    public DatabaseReference GetDataCart(String Username)
    {
        return database.getReference("Users").child(Username).child("Cart");
    }
    public DatabaseReference GetDetail(String Username){
        return database.getReference("Detail").child(Username);
    }
    public DatabaseReference GetDataUser(String Username)
    {
        return database.getReference("Users").child(Username);
    }
    public DatabaseReference GetDataProduct(String ProductId){
        return database.getReference("Products").child(ProductId);
    }
    public DatabaseReference GetDataHistory(String Username){
        return database.getReference("Users").child(Username).child("Order");
    }
    public DatabaseReference GetDataHistoryDetail(String Username,String OrderId){
        return database.getReference("Users").child(Username).child("Order").child(OrderId).child("Detail");
    }
}
