package com.edward.myapplication.model.modelrequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClothesReq implements Serializable {
    public int id;
    public int idseller;
    public int idCategory;
    public String des;
    public String name;
    public List<String> imgUrls;
    public List<ClothesPropertyReq> clothesProperties;

    public ClothesReq() {
    }

    public ClothesReq(int id, int idseller, int idCategory, String des, String name, List<String> imgUrls, List<ClothesPropertyReq> clothesProperties) {
        this.id = id;
        this.idseller = idseller;
        this.idCategory = idCategory;
        this.des = des;
        this.name = name;
        this.imgUrls = imgUrls;
        this.clothesProperties = clothesProperties;
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

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<ClothesPropertyReq> getClothesProperties() {
        return clothesProperties;
    }

    public void setClothesProperties(List<ClothesPropertyReq> clothesProperties) {
        this.clothesProperties = clothesProperties;
    }
}
