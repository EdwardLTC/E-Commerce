package com.edward.myapplication.model.modelrequest;

import java.io.Serializable;

public class ClothesPropertyReq implements Serializable {
    public String size;
    public int quantily;
    public String price;

    public ClothesPropertyReq() {
    }

    public ClothesPropertyReq(String size, int quantily, String price) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
