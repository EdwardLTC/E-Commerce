package com.edward.myapplication.model.modelrespon;

import java.util.ArrayList;

public class ResGetListVoucher {
    public Respon _Respon;
    public ArrayList<VoucherRes> _VoucherRes;

    public ResGetListVoucher() {
    }

    public ResGetListVoucher(Respon _Respon, ArrayList<VoucherRes> _VoucherRes) {
        this._Respon = _Respon;
        this._VoucherRes = _VoucherRes;
    }

    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public ArrayList<VoucherRes> get_VoucherRes() {
        return _VoucherRes;
    }

    public void set_VoucherRes(ArrayList<VoucherRes> _VoucherRes) {
        this._VoucherRes = _VoucherRes;
    }
}
