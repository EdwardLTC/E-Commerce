package com.edward.myapplication.model.modelrespon;

import java.util.ArrayList;

public class ClothesRes {
    public int id;
    public int idseller;
    public int idCategory;
    public String des;
    public String name;
    public ArrayList<String> imgsUrl;
    public int quantily;

    public ClothesRes() {
    }

    public ClothesRes(int id, int idseller, int idCategory, String des, String name, ArrayList<String> imgsUrl) {
        this.id = id;
        this.idseller = idseller;
        this.idCategory = idCategory;
        this.des = des;
        this.name = name;
        this.imgsUrl = imgsUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdseller() {
        return idseller;
    }

    public void setIdseller(int idseller) {
        this.idseller = idseller;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getImgsUrl() {
        return imgsUrl;
    }

    public void setImgsUrl(ArrayList<String> imgsUrl) {
        this.imgsUrl = imgsUrl;
    }

    public int getQuantily() {
        return quantily;
    }

    public void setQuantily(int quantily) {
        this.quantily = quantily;
    }

    @Override
    public String toString() {
        return "ClothesRes{" +
                "id=" + id +
                ", idseller=" + idseller +
                ", idCategory=" + idCategory +
                ", des='" + des + '\'' +
                ", name='" + name + '\'' +
                ", imgsUrl=" + imgsUrl +
                '}';
    }
}
