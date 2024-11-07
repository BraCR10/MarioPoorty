/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty;

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
