package com.example.finalproject.Helper;

import com.example.finalproject.Models.Cart;
import com.example.finalproject.Models.Delivery;
import com.example.finalproject.Models.Detail;
import com.example.finalproject.Models.Detail1;
import com.example.finalproject.Models.Order;
import com.example.finalproject.Models.User;
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
    public DatabaseReference GetDataCart(String Username)
    {
        return database.getReference("Users").child(Username).child("card");
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
        return database.getReference("Users").child(Username).child("order");
    }
    public DatabaseReference GetDataHistoryDetail(String Username,String OrderId){
        return database.getReference("Users").child(Username).child("order").child(OrderId).child("detail");
    }
    public DatabaseReference GetDataOrder(String Username,String OrderId){
        return database.getReference("Users").child(Username).child("order").child(OrderId);
    }
    public void CreateNewUser(User user){
        database.getReference("Users").child(user.getId()).setValue(user);
    }
    public void AddCard(String UserId, Cart card){
        database.getReference("Users").child(UserId).child("card").child(card.getId()).setValue(card);
    }
    public void AddOrder(String UserId, Order order)
    {
        database.getReference("Users").child(UserId).child("order").child(order.getId()).setValue(order);
    }
    public void AddOrderTemp(String UserId, Order order)
    {
        database.getReference("Temp").child(UserId).child(order.getId()).setValue(order);
    }
    public void AddOrderDetail1(String UserId, String OrderId, Detail1 detail1)
    {
        database.getReference("Users").child(UserId).child("order").child(OrderId).child("detail").child(detail1.getId()).setValue(detail1);
    }
    public void AddOrderDetailTemp(String UserId, String OrderId, Detail1 detail1)
    {
        database.getReference("Temp").child(UserId).child(OrderId).child("detail").child(detail1.getId()).setValue(detail1);
    }
    public DatabaseReference GetOrderDetailTemp(String UserId, String OrderId){
        return database.getReference("Temp").child(UserId).child(OrderId);
    }
    public void AddOrderDetail(String UserId, String OrderId, Detail detail)
    {
        database.getReference("Detail").child(UserId).child(detail.getId()).setValue(detail);
    }
    public void deleteCart(String UserId,String CartId)
    {
        database.getReference("Users").child(UserId).child("card").child(CartId).removeValue();
    }
    public void deleteTemp(String UserId)
    {
        database.getReference("Temp").child(UserId).removeValue();
    }
    public void AddDetail(String UserId,Detail detail)
    {
        database.getReference("Detail").child(UserId).child(detail.getId()).setValue(detail);
    }
    public void updataPoint(String Userid, int giftPoint,int Point){
        database.getReference("Users").child(Userid).child("giftPoint").setValue(giftPoint);
        database.getReference("Users").child(Userid).child("accumulatedPoint").setValue(Point);
    }
    public void updataProfile(String Userid, User user){
        database.getReference("Users").child(Userid).child("name").setValue(user.getName());
        database.getReference("Users").child(Userid).child("address").setValue(user.getAddress());
        database.getReference("Users").child(Userid).child("email").setValue(user.getAddress());
        database.getReference("Users").child(Userid).child("phone").setValue(user.getAddress());
    }
    public void AddDelivery(String Userid, Delivery delivery){
        database.getReference("Users").child(Userid).child("delivery").setValue(delivery);
    }
}

