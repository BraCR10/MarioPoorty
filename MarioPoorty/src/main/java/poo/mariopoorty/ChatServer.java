/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import poo.mariopoorty.threads.ConnectionThreadChat;
import poo.mariopoorty.threads.ThreadServerChat;

/**
 *
 * @author Brian Ramirez
 */
public class ChatServer {
    private final int CHAT_PORT = 3005;
    public ServerSocket server;
    public ArrayList<ThreadServerChat> players;
    ConnectionThreadChat threadConnectionsListener;

    public ChatServer() {
        try {
            server = new ServerSocket(CHAT_PORT);//1025-65535
            System.out.println("Waiting");
            players=new ArrayList<>();
            threadConnectionsListener=new ConnectionThreadChat (this);
            threadConnectionsListener.start();
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    

    
    public void broadCoast(Message msj){
        
        for (ThreadServerChat player : players) {
            try {
                player.output.writeObject(msj);
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
}
