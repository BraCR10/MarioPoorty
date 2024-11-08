/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import com.mycompany.proyect2.ChatServer;
import com.mycompany.proyect2.Message;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Brian Ramirez
 */
public class ThreadServerChat extends Thread{
    public Socket socket;
    private ChatServer server;
    public ObjectOutputStream output;
    public DataInputStream inputData;
    public ObjectInputStream input;
    String playerName;
    private  boolean  isRunning;

    public ThreadServerChat(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
        isRunning=true;
        try {
            output=new ObjectOutputStream(socket.getOutputStream());
            inputData= new DataInputStream(socket.getInputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void run() {
        Message msg;
        try {
            playerName= inputData.readUTF();
            System.out.println(playerName+" was connected!");
            
            // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        } catch (IOException ex) {
            Logger.getLogger(ThreadServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        while (isRunning) {
            try {
                
                msg = (Message) input.readObject();
                System.out.println(msg.toString());
                server.broadCoast(msg);
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadServerChat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadServerChat.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
    
    
    
}
