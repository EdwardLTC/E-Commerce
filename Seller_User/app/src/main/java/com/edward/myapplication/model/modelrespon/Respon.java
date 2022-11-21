package com.edward.myapplication.model.modelrespon;

public class Respon {
    public int respone_code;
    public String status;

    public Respon() {
    }

    public Respon(int respone_code, String status) {
        this.respone_code = respone_code;
        this.status = status;
    }

    public int getRespone_code() {
        return respone_code;
    }

    public void setRespone_code(int respone_code) {
        this.respone_code = respone_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
