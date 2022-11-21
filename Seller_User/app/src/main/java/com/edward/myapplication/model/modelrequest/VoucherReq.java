package com.edward.myapplication.model.modelrequest;

public class VoucherReq {
    public int id;
    public int idseller;
    public int ratio;

    public VoucherReq(int id, int idseller, int ratio) {
        this.id = id;
        this.idseller = idseller;
        this.ratio = ratio;
    }

    public VoucherReq() {
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
