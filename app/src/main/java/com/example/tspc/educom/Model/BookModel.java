package com.example.tspc.educom.Model;

/**
 * Created by cr4ck3r on 8/30/18.
 * Copyright (c) 2018
 */
public class BookModel {
    String Image;
    String BName;
    String WName;
    String Price;


    public BookModel(String image, String BName, String WName, String price) {
        Image = image;
        this.BName = BName;
        this.WName = WName;
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getBName() {
        return BName;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public String getWName() {
        return WName;
    }

    public void setWName(String WName) {
        this.WName = WName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}

