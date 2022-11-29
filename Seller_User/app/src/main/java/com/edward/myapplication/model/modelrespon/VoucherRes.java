package com.edward.myapplication.model.modelrespon;

import java.io.Serializable;

public class VoucherRes implements Serializable {
    private int id;
    private int idseller;
    private int ratio;

    public VoucherRes(int id, int idseller, int ratio) {
        this.id = id;
        this.idseller = idseller;
        this.ratio = ratio;
    }

    public VoucherRes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdseller() {
        return idseller;
    }

    public void setIdseller(int idseller) {
        this.idseller = idseller;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
}
