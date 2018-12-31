package com.example.dell.fder.ObjectClass;

import java.util.ArrayList;

public class Square {


//    private int imageId;
    private Friend authorOfSquare;
    private String date;
    private String contentOfSquare;
    private int idOfPicture;
    private int likes;
    //comment class

    public Square (Friend authorOfSquare,String date,String contentOfSquare,int idOfPicture ){
        this.authorOfSquare=authorOfSquare;
        this.contentOfSquare=contentOfSquare;
        this.date=date;
        this.idOfPicture=idOfPicture;
    }

    public Friend getAuthorOfSquare() {
        return authorOfSquare;
    }

    public String getContentOfSquare() {
        return contentOfSquare;
    }

    public String getDate() {
        return date;
    }

    public int getIdOfPicture(){
        return idOfPicture;
    }
}
