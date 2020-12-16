/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import form.FrmServer;
import java.io.IOException;
import java.util.List;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TableModelRadnik;

/**
 *
 * @author Win 7
 */
public class ServerThread extends Thread {

    FrmServer formSever;
    ServerSocket socket;
    List<ClientThread> prijavljeRadnici;

    public ServerThread(int port) throws IOException {
        socket = new ServerSocket(port);
        prijavljeRadnici = new ArrayList<>();
    }

    public ServerThread(int port, FrmServer formServer) throws IOException {
        socket = new ServerSocket(port);
        prijavljeRadnici = new ArrayList<>();
        this.formSever = formServer;
    }

    public List<ClientThread> getPrijavljeRadnici() {
        return prijavljeRadnici;
    }

    @Override
    public void run() {

        while (!isInterrupted()) {
            try {
                NitZatvaranje nitZatvaranje = new NitZatvaranje(socket, this);
                nitZatvaranje.start();
                Socket socketKlijent= socket.accept();
                System.out.println("dosao klijent");
                ClientThread nitKlijent = new ClientThread(socketKlijent,this);
                nitKlijent.start();
                System.out.println("nit za klijenta je pokrenuta");

            } catch (IOException ex) {
                //Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    void dodajRadnika(ClientThread client) {
       prijavljeRadnici.add(client);
        TableModelRadnik model= (TableModelRadnik) formSever.getTable().getModel();
        model.dodaj(client.getUlogovaniRanik());
        
    }

}
