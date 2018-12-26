package com.example.dell.fder.ObjectClass;

public class Friend {

   private   String nameOfFriend;
   private   String signature;
   private int headId;


   public Friend(String nameOfFriend,String signature,int headId){
       this.nameOfFriend=nameOfFriend;
       this.signature=signature;
       this.headId=headId;

   }

    public String getNameOfFriend() {
        return nameOfFriend;
    }

    public String getSignature() {
        return signature;
    }

    public int getHeadId() {
        return headId;
    }
}
