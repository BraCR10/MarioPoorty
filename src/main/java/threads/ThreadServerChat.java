/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import com.mycompany.proyect2.GameServer;
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
    private GameServer server;
    public ObjectOutputStream output;
    public DataInputStream inputData;
    public ObjectInputStream input;
    String playerName;
    private  boolean  isRunning;

    public ThreadServerChat(Socket socket, GameServer server) {
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

       
        while (isRunning) {
            try {
                
                msg = (Message) input.readObject();
                server.broadCoast(msg);
                
            } catch (IOException ex) {
                //a player lost connection
            } catch (ClassNotFoundException ex) {
                //a player lost connection
            }
            
        }
    }
    
    
    
    
    
}
