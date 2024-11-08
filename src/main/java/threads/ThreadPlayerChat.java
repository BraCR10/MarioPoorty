package threads;

import com.mycompany.proyect2.Message;
import com.mycompany.proyect2.playerChat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


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
