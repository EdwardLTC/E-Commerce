package com.edward.myapplication.model.modelrespon;

import java.util.ArrayList;

public class ResGetListProperties {
    public Respon _Respon;
    public ArrayList<ClothesPropertiesRes> _ClothesPropertiesRes;

    public ResGetListProperties() {
    }

    public ResGetListProperties(Respon _Respon, ArrayList<ClothesPropertiesRes> _ClothesPropertiesRes) {
        this._Respon = _Respon;
        this._ClothesPropertiesRes = _ClothesPropertiesRes;
    }

    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public ArrayList<ClothesPropertiesRes> get_ClothesPropertiesRes() {
        return _ClothesPropertiesRes;
    }

    public void set_ClothesPropertiesRes(ArrayList<ClothesPropertiesRes> _ClothesPropertiesRes) {
        this._ClothesPropertiesRes = _ClothesPropertiesRes;
    }
}
