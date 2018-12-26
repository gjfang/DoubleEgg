package com.example.dell.fder.ObjectClass;

public class Message {
    private int headerOfMessageId;
    private String authorOfMessage;
    private String contentOfMessage;
    private String dateOfMessage;


    public Message (int headerOfMessageId,String authorOfMessage,String contentOfMessage,String dateOfMessage){
        this.headerOfMessageId=headerOfMessageId;
        this.authorOfMessage=authorOfMessage;
        this.contentOfMessage=contentOfMessage;
        this.dateOfMessage=dateOfMessage;

    }

    public int getHeaderOfMessageId() {
        return headerOfMessageId;
    }

    public String getAuthorOfMessage() {
        return authorOfMessage;
    }

    public String getContentOfMessage() {
        return contentOfMessage;
    }

    public String getDateOfMessage() {
        return dateOfMessage;
    }
}

