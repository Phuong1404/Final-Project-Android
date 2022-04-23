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
}
