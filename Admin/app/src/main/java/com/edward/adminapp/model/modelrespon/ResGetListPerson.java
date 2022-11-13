package com.edward.adminapp.model.modelrespon;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResGetListPerson implements Serializable {
    public Respon _Respon;
    public ArrayList<PersonRes> _PersonRes;

    public ResGetListPerson(Respon _Respon, ArrayList<PersonRes> _PersonRes) {
        this._Respon = _Respon;
        this._PersonRes = _PersonRes;
    }

    public Respon get_Respon() {
        return _Respon;
    }

    public void set_Respon(Respon _Respon) {
        this._Respon = _Respon;
    }

    public List<PersonRes> get_PersonRes() {
        return _PersonRes;
    }

    public void set_PersonRes(ArrayList<PersonRes> _PersonRes) {
        this._PersonRes = _PersonRes;
    }
}
