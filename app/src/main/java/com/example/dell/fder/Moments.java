package com.example.dell.fder;
//动态类 设置属性，对其进行修改以适配
public class Moments {

    private String name;

    private int imageId;

    public Moments(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

}
