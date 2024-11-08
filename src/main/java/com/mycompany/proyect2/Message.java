package com.mycompany.proyect2;


import java.io.Serializable;

/**
 *
 * @author Brian Ramirez
 */
public class Message implements Serializable  {
    public String sender;
    public String message;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public String toString() {
        return sender + " said : " + message ;
    }
            
    
    

    
    
    
}
