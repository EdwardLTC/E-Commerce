package com.edward.myapplication.model.modelrespon;

public class ResGetClothes {
    public Respon _Respon;
    public ClothesRes _ClothesRes;

    public ResGetClothes() {
    }

    public ResGetClothes(Respon _Respon, ClothesRes _ClothesRes) {
        this._Respon = _Respon;
        this._ClothesRes = _ClothesRes;
    }

    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public ClothesRes get_ClothesRes() {
        return _ClothesRes;
    }

    public void set_ClothesRes(ClothesRes _ClothesRes) {
        this._ClothesRes = _ClothesRes;
    }

    @Override
    public String toString() {
        return "ResGetClothes{" +
                "_Respon=" + _Respon +
                ", _ClothesRes=" + _ClothesRes +
                '}';
    }
}
