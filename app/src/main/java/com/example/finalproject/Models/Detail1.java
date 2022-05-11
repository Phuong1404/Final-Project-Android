package com.example.finalproject.Models;

import java.util.Date;

public class Detail1 {
    String Id;
    Product product;
    String Total;
    String Quanlity;
    public Detail1(){};
    public Detail1(String Id,Product product,String total,String quanlity)
    {
        this.Id=Id;
        this.product=product;
        this.Total=total;
        this.Quanlity=quanlity;
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

    public String getQuanlity() {
        return Quanlity;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getTotal() {
        return Total;
    }

    public void setQuanlity(String quanlity) {
        Quanlity = quanlity;
    }
}
