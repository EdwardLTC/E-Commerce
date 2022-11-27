package com.edward.myapplication.model.modelrespon;

import java.io.Serializable;

public class ClothesPropertiesRes implements Serializable {
    public String size;
    public int quantily;
    public String price;

    public ClothesPropertiesRes() {
    }

    public ClothesPropertiesRes(String size, int quantily, String price) {
        this.size = size;
        this.quantily = quantily;
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantily() {
        return quantily;
    }

    public void setQuantily(int quantily) {
        this.quantily = quantily;
    }

//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
