package com.example.dell.fder.ObjectClass;

public class Square {


//    private int imageId;
    private Friend authorOfSquare;
    private String date;
    private String contentOfSquare;
    private int likes;
    //comment class

    public Square (Friend authorOfSquare,String date,String contentOfSquare ){
        this.authorOfSquare=authorOfSquare;
        this.contentOfSquare=contentOfSquare;
        this.date=date;
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
}
