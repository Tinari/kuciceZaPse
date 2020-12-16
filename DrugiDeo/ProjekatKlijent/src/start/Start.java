/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import form.FrmLogin;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;
import session.Session;

/**
 *
 * @author Win 7
 */
public class Start {
    
    public static void main(String[] args) throws IOException {
        
        Socket socket= new Socket("127.0.0.1",9090);
        Session.getInstance().setSocket(socket);
        JFrame frmLogin= new FrmLogin();
        frmLogin.setVisible(true);
        frmLogin.setLocationRelativeTo(null);
    }
    
}
