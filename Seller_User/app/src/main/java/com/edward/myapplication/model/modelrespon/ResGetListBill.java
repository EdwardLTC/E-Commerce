package com.edward.myapplication.model.modelrespon;

import java.util.ArrayList;
import java.util.List;

public class ResGetListBill {
    public Respon _Respon;
    public List<BillRes> _BillRes;

    public ResGetListBill() {
    }

    public ResGetListBill(Respon _Respon, List<BillRes> _BillRes) {
        this._Respon = _Respon;
        this._BillRes = _BillRes;
    }

    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public List<BillRes> get_BillRes() {
        return _BillRes;
    }

    public void set_BillRes(List<BillRes> _BillRes) {
        this._BillRes = _BillRes;
    }
}
