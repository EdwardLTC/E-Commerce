package com.edward.adminapp.model.modelrespon;

import java.io.Serializable;

public class CategoryRes implements Serializable {
    private int id;
    private String name;

    public CategoryRes() {
    }

    public CategoryRes(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "CategoryRes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
