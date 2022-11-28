package com.edward.myapplication.model.modelrespon;

import java.util.ArrayList;

public class ResGetListBill {
    public Respon _Respon;
    public ArrayList<BillRes> _BillRes;

    public ResGetListBill() {
    }

    public ResGetListBill(Respon _Respon, ArrayList<BillRes> _BillRes) {
        this._Respon = _Respon;
        this._BillRes = _BillRes;
    }

    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public ArrayList<BillRes> get_BillRes() {
        return _BillRes;
    }

    public void set_BillRes(ArrayList<BillRes> _BillRes) {
        this._BillRes = _BillRes;
    }
}
