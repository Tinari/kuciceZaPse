/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Win 7
 */
public class Server {
    
    public void start() throws IOException{
        
        ServerSocket serverSocket= new ServerSocket(9090);
        Socket soket=serverSocket.accept();
        comunication(soket);
    }

    private void comunication(Socket soket) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
