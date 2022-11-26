package com.edward.myapplication.model.modelrequest;

import java.util.ArrayList;

public class ClothesReq {
    public int id;
    public int idseller;
    public int idCategory;
    public String des;
    public String name;
    public ArrayList<String> imgUrls;
    public ArrayList<ClothesPropertyReq> clothesProperties;
}
