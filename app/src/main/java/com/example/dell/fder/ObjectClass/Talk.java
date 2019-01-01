package com.example.dell.fder.ObjectClass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Talk {
    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    public static void main(String[] args) {
        Talk newTalk = new Talk();
        while(true){
            newTalk.startConnection();
        }
    }

    public void startConnection(){
        Scanner input = null;
        try{
            // create a socket to the server
            Socket socket = new Socket("106.14.1.81", 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
        try {
            input = new Scanner(System.in);
            // send the message to the server
            String message = input.nextLine();
            System.out.println(message);
            toServer.writeUTF(message);
            toServer.flush();
            // get message from the server
            String messageGet = fromServer.readUTF();
            System.out.println("服务器回给你一个:" + messageGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
