package com.example.finalproject.Models;

import java.util.Date;

public class Detail1 {
    String Id;
    Product product;
    double Total;
    int Quanlity;
    String OrderRequest;
    public Detail1(){};
    public Detail1(String Id,Product product,double total,int quanlity,String OrderRequest)
    {
        this.Id=Id;
        this.product=product;
        this.Total=total;
        this.Quanlity=quanlity;
        this.OrderRequest=OrderRequest;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getQuanlity() {
        return Quanlity;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public double getTotal() {
        return Total;
    }

    public void setQuanlity(int quanlity) {
        Quanlity = quanlity;
    }
    public String getOrderRequest() {
        return OrderRequest;
    }
    public void setOrderRequest(String orderRequest) {
        OrderRequest = orderRequest;
    }
}
