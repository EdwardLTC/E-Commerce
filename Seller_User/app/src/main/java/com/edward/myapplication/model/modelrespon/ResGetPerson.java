package com.edward.myapplication.model.modelrespon;

import java.io.Serializable;

public class ResGetPerson implements Serializable {
    public Respon _Respon;
    public PersonRes _PersonRes;

    public ResGetPerson(Respon _Respon, PersonRes _PersonRes) {
        this._Respon = _Respon;
        this._PersonRes = _PersonRes;
    }

    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public PersonRes get_PersonRes() {
        return _PersonRes;
    }

    public void set_PersonRes(PersonRes _PersonRes) {
        this._PersonRes = _PersonRes;
    }
}
