/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect2;

/**
 *
 * @author Brian Ramirez
 */
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadCatchNameServer extends Thread {
    private ServerPlayers player;
    GameServer server;
    private boolean isRunning;

    public ThreadCatchNameServer(ServerPlayers player, GameServer server) {
        this.player = player;
        this.server=server;
        this.isRunning = true;
    }

    @Override
    public void run() {
        try {
            String newName = characterNamesManager(player);

            while (isRunning && NameExists(newName)) {
                player.playerOut.writeBoolean(false);  
                newName = characterNamesManager(player); 
            }

            player.playerOut.writeBoolean(true); 
            player.name = newName;
            server.console.write("New player added: " + newName);

        } catch (IOException e) {
            Logger.getLogger(ThreadCatchNameServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Método para manejar la selección del nombre del jugador
    private String characterNamesManager(ServerPlayers player) {
        String name = server.getCharacterNames().get(0);
        try {
            
            player.playerOutObj.writeObject(server.getCharacterNames());
            name = player.playerIn.readUTF();

            

        } catch (IOException ex) {
            Logger.getLogger(ThreadCatchNameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    // Método para verificar si el nombre ya existe entre los jugadores
    private boolean NameExists(String name) {
        synchronized (players) {
            for (ServerPlayers p : players) {
                if (p.name != null && p.name.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Detener el hilo si es necesario
    public void stopRunning() {
        isRunning = false;
    }
}