package threads;

import com.mycompany.proyect2.Message;
import com.mycompany.proyect2.Player;
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
    private Player player;
    private ObjectInputStream input;

    public ThreadPlayerChat(Socket socket, Player player) {
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
                String oldText=player.board.getJtaChatSpace().getText();
                player.board.getJtaChatSpace().setText(oldText+"\n"
                        +"--------------------------------------------------------"
                        +"\n"+msg.toString());
            } catch (IOException ex) {
                Logger.getLogger(ThreadPlayerChat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadPlayerChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
