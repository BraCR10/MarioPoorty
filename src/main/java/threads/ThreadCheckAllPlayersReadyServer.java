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


public class ThreadCheckAllPlayersReadyServer extends Thread {

    private GameServer server;
    private boolean isRunning;

    public ThreadCheckAllPlayersReadyServer( GameServer server) {
        this.server=server;
        this.isRunning = true;
    }

    @Override
    public void run() {
            boolean allStop=false;
            while (isRunning) {
                for (int i = 0; i < server.threadsFirstMenuPlayers.size(); i++) {//TO VERIFY EACH THREAD
                    if(server.threadsFirstMenuPlayers.get(i).isRunning()){
                        allStop=false;
                        break;
                    }
                     else allStop=true;
                }
                if(allStop){
                    server.console.write("\nStarting the game!\n");
                    server.setOrder();
                    for (int i = 0; i < server.NumPlayers; i++){
                        server.console.write("["+server.getPlayers().get(i).Index+"] "+server.getPlayers().get(i).name);
                    }
                    //SETINGS TO START THE GAME
                    server.SentGeneralInfo();
                    server.GameLoop();
                    isRunning=false;
                }
                try {
                    sleep(5);
                } catch (InterruptedException ex) {
                    System.out.println("Sleeping!!");
                }
            }
            
    }

}