package com.edward.myapplication.model.modelrequest;

import com.edward.myapplication.model.modelrequest.BillDetailReq;

import java.io.Serializable;
import java.util.ArrayList;

public class BillReq implements Serializable {
    public int iduser;
    public int idvoucher;
    public String status;
    public ArrayList<BillDetailReq> listBillDetailReq;
}
