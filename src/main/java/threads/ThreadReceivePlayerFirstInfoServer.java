/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

/**
 *
 * @author Brian Ramirez
 */
import com.mycompany.proyect2.GameServer;
import com.mycompany.proyect2.ServerPlayers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadReceivePlayerFirstInfoServer extends Thread {
    private ServerPlayers player;
    GameServer server;
    int position;
    private boolean isRunning;

    public ThreadReceivePlayerFirstInfoServer(ServerPlayers player,int position, GameServer server) {
        this.player = player;
        this.server=server;
        this.position=position;
        this.isRunning = true;
    }

    @Override
    public void run() {
        try {
    
            String newName = player.playerIn.readUTF();
            while ( NameExists(newName)) {//IF THE NAME IS NOT ALLOWED
                player.playerOut.writeBoolean(false); 
                newName = player.playerIn.readUTF();
 
            }
            
            //CONFIRMATION
            player.playerOut.writeBoolean(true); 
            
            server.getPlayers().get(position).name=newName;
            server.console.write("\nNew player added: " + newName);
            
            //SETTINGS TO DEFINE THE ORDER TO PLAY
            server.console.write("\nAsking for number to "+newName);
            server.getPlayers().get(position).NumberStart = server.getNumeroRandom();
            server.getPlayers().get(position).Index = server.getPlayers().get(position).playerIn.readInt();
    

            server.console.write("\n"+newName+" ready to play!");
            stopRunning();
        } catch (IOException e) {
            server.console.write("A player lost the connetion");
        }
    }


    private boolean NameExists(String name) {
        for (ServerPlayers p : server.getPlayers()) {
            if (p.name != null && p.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void stopRunning() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
    
}