package com.edward.myapplication.model.modelrequest;

import java.io.Serializable;

public class BillDetailReq implements Serializable {
    public int idclothes;
    public String size;
    public int quantily;

    public BillDetailReq(int idclothes, String size, int quantily) {
        this.idclothes = idclothes;
        this.size = size;
        this.quantily = quantily;
    }

    public BillDetailReq() {
    }

    public int getIdclothes() {
        return idclothes;
    }

    public void setIdclothes(int idclothes) {
        this.idclothes = idclothes;
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
}
