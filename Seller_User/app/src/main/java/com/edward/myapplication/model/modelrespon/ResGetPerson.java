package com.edward.myapplication.model.modelrespon;

import java.io.Serializable;

public class ResGetPerson implements Serializable {
    public Respon _Respon;
    public PersonRes _PersonRes;

    public ResGetPerson(Respon _Respon, PersonRes _PersonRes) {
        this._Respon = _Respon;
        this._PersonRes = _PersonRes;
    }
}
