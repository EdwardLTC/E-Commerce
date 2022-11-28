package com.edward.myapplication.model.modelrequest;

import com.edward.myapplication.model.modelrequest.BillDetailReq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BillReq implements Serializable {
    public int iduser;
    public int idseller;
    public int idvoucher;
    public String status;
    public List<BillDetailReq> listBillDetailReq;

    public BillReq() {
    }

    public BillReq(int iduser, int idseller, int idvoucher, String status, List<BillDetailReq> listBillDetailReq) {
        this.iduser = iduser;
        this.idseller = idseller;
        this.idvoucher = idvoucher;
        this.status = status;
        this.listBillDetailReq = listBillDetailReq;
    }

    public int getIdseller() {
        return idseller;
    }

    public void setIdseller(int idseller) {
        this.idseller = idseller;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdvoucher() {
        return idvoucher;
    }

    public void setIdvoucher(int idvoucher) {
        this.idvoucher = idvoucher;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BillDetailReq> getListBillDetailReq() {
        return listBillDetailReq;
    }

    public void setListBillDetailReq(List<BillDetailReq> listBillDetailReq) {
        this.listBillDetailReq = listBillDetailReq;
    }
}
