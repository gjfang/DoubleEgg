package com.example.dell.fder.ObjectClass;

public class Comment {


    private int  authorOfCommentId;
    private String contentOfComment;
    private String dateOfComment;


    public Comment(int authorOfCommentId,String contentOfComment,String dateOfComment){
        this.authorOfCommentId=authorOfCommentId;
        this.contentOfComment=contentOfComment;
        this.dateOfComment = dateOfComment;
    }

    public int getAuthorOfCommentId() {
        return authorOfCommentId;
    }

    public String getContentOfComment() {
        return contentOfComment;
    }

    public String getDateOfComment() {
        return dateOfComment;
    }
}
