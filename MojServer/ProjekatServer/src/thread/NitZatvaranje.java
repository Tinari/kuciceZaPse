/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Win 7
 */
public class NitZatvaranje extends Thread{
    
    ServerSocket socket;
    ServerThread nitServer;

    public NitZatvaranje(ServerSocket socket, ServerThread nitServer) {
        this.socket = socket;
        this.nitServer = nitServer;
    }
    
    

    @Override
    public void run() {
        
        
        while(true){
            if(nitServer.isInterrupted()){
                try {
                    socket.close();
                    JOptionPane.showMessageDialog(null, "Server je zaustavljen");
                } catch (IOException ex) {
                    Logger.getLogger(NitZatvaranje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
        
    }
    
    
    
}
