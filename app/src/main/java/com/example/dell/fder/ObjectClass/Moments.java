package com.example.dell.fder.ObjectClass;
//动态类 设置属性，对其进行修改以适配
public class Moments {

    private String name;
    private String  date;
    private String contentOfMoments;
    private int imageId;

    public Moments(String name, int imageId,String date,String contentOfMoments) {
        this.name = name;
        this.imageId = imageId;
        this.contentOfMoments=contentOfMoments;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getContentOfMoments(){return contentOfMoments;}

}
