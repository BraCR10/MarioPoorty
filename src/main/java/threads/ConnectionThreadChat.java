package threads;


import com.mycompany.proyect2.ChatServer;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Brian Ramirez
 */
public class ConnectionThreadChat extends Thread{
    private boolean  isRunning=true;
    ChatServer server;


    public ConnectionThreadChat(ChatServer server) {
        this.server = server;
    }
    
    
    public void run(){
        while(isRunning){
            try {
                //server.pantalla.write("waiting players");
                System.out.println("Waiting for players");
                Socket socket = server.server.accept();
                ThreadServerChat t = new ThreadServerChat(socket, server);
                t.start();
                server.players.add(t);

            } catch (IOException ex) {
                Logger.getLogger(ConnectionThreadChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
}
