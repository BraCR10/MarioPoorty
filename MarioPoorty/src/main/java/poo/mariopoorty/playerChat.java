/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import poo.mariopoorty.threads.ThreadPlayerChat;

/**
 *
 * @author Brian Ramirez
 */
public class playerChat {
    Socket socket;
    private final int CHAT_PORT = 3005;
    public ObjectOutputStream output;
    public DataOutputStream outputData;
    Scanner scanner = new Scanner (System.in);
    String Name;
    public playerChat() {
        try {
            connect();
        } catch (IOException ex) {
            Logger.getLogger(playerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    public void connect() throws IOException{
        socket = new Socket("localhost", CHAT_PORT);
        output=new ObjectOutputStream(socket.getOutputStream());
        outputData=new DataOutputStream(socket.getOutputStream());
        
        ThreadPlayerChat t = new ThreadPlayerChat(socket, this);
        t.start();
        Name=scanner.next();
        outputData.writeUTF(Name);

    
    }
        //test
    public  void send(){
        try {
            String msg=scanner.next();
            this.output.writeObject(new Message(Name,msg ));
        } catch (IOException ex) {
            Logger.getLogger(playerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public static void main(String[] args){
       playerChat p= new playerChat();
        while (true) {            
            p.send();
        }
    }
    

}
