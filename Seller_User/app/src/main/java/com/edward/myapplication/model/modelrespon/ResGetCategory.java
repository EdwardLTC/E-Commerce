package com.edward.myapplication.model.modelrespon;

public class ResGetCategory {
    private Respon _Respon;
    private CategoryRes _CategoryRes;

    public ResGetCategory(Respon _Respon, CategoryRes _CategoryRes) {
        this._Respon = _Respon;
        this._CategoryRes = _CategoryRes;
    }

    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public CategoryRes get_CategoryRes() {
        return _CategoryRes;
    }

    public void set_CategoryRes(CategoryRes _CategoryRes) {
        this._CategoryRes = _CategoryRes;
    }
}
