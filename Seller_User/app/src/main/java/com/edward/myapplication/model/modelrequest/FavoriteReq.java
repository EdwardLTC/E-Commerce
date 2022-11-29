package com.edward.myapplication.model.modelrequest;

import java.io.Serializable;

public class FavoriteReq implements Serializable {
    private int idclothes;
    private int iduser;

    public FavoriteReq(int idclothes, int iduser) {
        this.idclothes = idclothes;
        this.iduser = iduser;
    }

    public int getIdclothes() {
        return idclothes;
    }

    public void setIdclothes(int idclothes) {
        this.idclothes = idclothes;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
