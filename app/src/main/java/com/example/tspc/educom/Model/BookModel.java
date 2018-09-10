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
    String link;
    String rating;

    public BookModel(String image, String BName, String WName, String price, String link, String rating) {
        Image = image;
        this.BName = BName;
        this.WName = WName;
        Price = price;
        this.link = link;
        this.rating = rating;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

