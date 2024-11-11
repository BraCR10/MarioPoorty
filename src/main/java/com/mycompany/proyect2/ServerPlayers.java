package com.mycompany.proyect2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerPlayers implements Comparable<ServerPlayers>{
    public Socket socket;
    
    public DataOutputStream playerOut;
    public DataInputStream playerIn;
    
    
    
    public int positionStar = 0;
    public int positionFinish = 0;
    
    public String name = "";
    
    public int Index = 0;
    public int NumberStart;
    
    public ServerPlayers(Socket socket) {
        try {
            
        this.socket = socket;
        this.playerOut = new DataOutputStream(this.socket.getOutputStream());
        this.playerIn = new DataInputStream(this.socket.getInputStream());
        this.positionStar = 0;
        this.positionFinish = 0;
        } 
        catch (IOException e){}

    }   

    @Override
    public int compareTo(ServerPlayers o) {
        return Math.abs(this.Index - this.NumberStart) - Math.abs(o.Index  - this.NumberStart);
    }

 
    
}
