package com.edward.adminapp.model.modelrespon;

import java.util.ArrayList;

public class ResGetListCategory {
    private Respon _Respon;
    private ArrayList<CategoryRes> _CategoryRes;

    public ResGetListCategory(Respon _Respon, ArrayList<CategoryRes> _CategoryRes) {
        this._Respon = _Respon;
        this._CategoryRes = _CategoryRes;
    }

    public ResGetListCategory() {
    }

    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public ArrayList<CategoryRes> get_CategoryRes() {
        return _CategoryRes;
    }

    public void set_CategoryRes(ArrayList<CategoryRes> _CategoryRes) {
        this._CategoryRes = _CategoryRes;
    }
}
