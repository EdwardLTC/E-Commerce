package com.edward.adminapp.model.modelrespon;

import java.io.Serializable;

public class Respon implements Serializable {
    public int respone_code;
    public String status;

    public int getRespone_code() {
        return respone_code;
    }

    public String getStatus() {
        return status;
    }

    public void setRespone_code(int respone_code) {
        this.respone_code = respone_code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Respon(int respon_code, String status) {
        this.respone_code = respon_code;
        this.status = status;
    }
}
