package com.edward.myapplication.model.modelrequest;

import com.edward.myapplication.model.modelrequest.BillDetailReq;

import java.util.ArrayList;

public class BillReq {
    public int iduser;
    public int idvoucher;
    public String status;
    public ArrayList<BillDetailReq> listBillDetailReq;
}
