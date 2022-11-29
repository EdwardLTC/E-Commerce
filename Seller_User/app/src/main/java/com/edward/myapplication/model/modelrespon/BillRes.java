package com.edward.myapplication.model.modelrespon;

import java.io.Serializable;

public class BillRes implements Serializable {
    public int id;
    public int iduser;
    public int idseller;
    public int idvoucher;
    public String dateCreate;
    public String status;
    public String sellerName;
    public String userAddress;

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }



    public BillRes(int id, int iduser, int idseller, int idvoucher, String dateCreate, String status) {
        this.id = id;
        this.iduser = iduser;
        this.idseller = idseller;
        this.idvoucher = idvoucher;
        this.dateCreate = dateCreate;
        this.status = status;
    }

    public BillRes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdseller() {
        return idseller;
    }

    public void setIdseller(int idseller) {
        this.idseller = idseller;
    }

    public int getIdvoucher() {
        return idvoucher;
    }

    public void setIdvoucher(int idvoucher) {
        this.idvoucher = idvoucher;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BillRes{" +
                "id=" + id +
                ", iduser=" + iduser +
                ", idseller=" + idseller +
                ", idvoucher=" + idvoucher +
                ", dateCreate='" + dateCreate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
