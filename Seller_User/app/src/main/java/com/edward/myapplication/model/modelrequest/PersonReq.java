package com.edward.myapplication.model.modelrequest;

import java.io.Serializable;

public class PersonReq implements Serializable {
    private int id;
    private String name;
    private String mail;
    private String psw;
    private String phoneNum;
    private int role;
    private String imgUrl;
    private String address;


    public PersonReq() {
    }

    public PersonReq(int id, String name, String mail, String psw, String phoneNum, int role, String imgUrl, String address) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.psw = psw;
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

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
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
        return "PersonReq{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", psw='" + psw + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", role=" + role +
                ", imgUrl='" + imgUrl + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
