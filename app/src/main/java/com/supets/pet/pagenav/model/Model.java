package com.supets.pet.pagenav.model;

public class Model {
    private String name;
    private int iconRes;

    public Model(String name, int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", iconRes=" + iconRes +
                '}';
    }
}
