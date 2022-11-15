package com.edward.adminapp.model.modelrespon;

import java.io.Serializable;

public class PersonRes implements Serializable {
    public int id;
    public String name;
    public String mail;
    public String phoneNum;
    public int role;
    public String imgUrl;
    public String address;

    public PersonRes(int id, String name, String mail, String phoneNum, int role, String imgUrl, String address) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phoneNum = phoneNum;
        this.role = role;
        this.imgUrl = imgUrl;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonRes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", role=" + role +
                ", imgUrl='" + imgUrl + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
