package com.edward.myapplication.model.modelrequest;

public class FavoriteReq {
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
