package com.edward.myapplication.model;

public class NewBill {
    private int idCustomer;
    private int idClothes;
    private String nameClothes;
    private String imgUrl;
    private String size;
    private int quantity;
    private String price;

    public NewBill() {
    }

    public NewBill(int idCustomer, int idClothes, String nameClothes, String imgUrl, String size, int quantity, String price) {
        this.idCustomer = idCustomer;
        this.idClothes = idClothes;
        this.nameClothes = nameClothes;
        this.imgUrl = imgUrl;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdClothes() {
        return idClothes;
    }

    public void setIdClothes(int idClothes) {
        this.idClothes = idClothes;
    }

    public String getNameClothes() {
        return nameClothes;
    }

    public void setNameClothes(String nameClothes) {
        this.nameClothes = nameClothes;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
