/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import poo.mariopoorty.Message;
import poo.mariopoorty.playerChat;

/**
 *
 * @author Brian Ramirez
 */
public class ThreadPlayerChat extends Thread{
    private boolean isRunning;
    private Socket socket;
    private playerChat player;
    private ObjectInputStream input;

    public ThreadPlayerChat(Socket socket, playerChat player) {
        this.socket = socket;
        this.player = player;
        isRunning=true;
        try {
            input=new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadPlayerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Message msg;
        while(isRunning){
            try {
                
                msg = (Message) input.readObject();
                //playerChat
                System.out.println(msg.toString());
            } catch (IOException ex) {
                Logger.getLogger(ThreadPlayerChat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadPlayerChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
