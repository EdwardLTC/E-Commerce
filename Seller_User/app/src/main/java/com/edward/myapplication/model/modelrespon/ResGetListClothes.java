package com.edward.myapplication.model.modelrespon;

import java.util.ArrayList;

public class ResGetListClothes {
    public Respon _Respon;
    public ArrayList<ClothesRes> _ClothesRes;

    public ResGetListClothes(Respon _Respon, ArrayList<ClothesRes> _ClothesRes) {
        this._Respon = _Respon;
        this._ClothesRes = _ClothesRes;
    }


    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public ArrayList<ClothesRes> get_ClothesRes() {
        return _ClothesRes;
    }

    public void set_ClothesRes(ArrayList<ClothesRes> _ClothesRes) {
        this._ClothesRes = _ClothesRes;
    }
}
