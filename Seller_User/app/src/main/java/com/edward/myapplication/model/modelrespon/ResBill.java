package com.edward.myapplication.model.modelrespon;

public class ResBill {
    public Respon _Respon;
    public String _TotolPrice;
    public String _DateCreate;

    public ResBill() {
    }

    public ResBill(Respon _Respon, String _TotolPrice, String _DateCreate) {
        this._Respon = _Respon;
        this._TotolPrice = _TotolPrice;
        this._DateCreate = _DateCreate;
    }

    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public String get_TotolPrice() {
        return _TotolPrice;
    }

    public void set_TotolPrice(String _TotolPrice) {
        this._TotolPrice = _TotolPrice;
    }

    public String get_DateCreate() {
        return _DateCreate;
    }

    public void set_DateCreate(String _DateCreate) {
        this._DateCreate = _DateCreate;
    }
}