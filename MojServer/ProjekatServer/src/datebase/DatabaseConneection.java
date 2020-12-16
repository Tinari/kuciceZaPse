/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datebase;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Win 7
 */
public class DatabaseConneection {
    
    private Connection connection;
    private static DatabaseConneection instance;

    public DatabaseConneection() throws Exception {
        DatabaseResourse resursi=new DatabaseResourse();
        connection=DriverManager.getConnection(resursi.getUrl(),resursi.getUsername(),resursi.getPassword());
        
    }

    public Connection getConnection() {
        return connection;
    }
    
    
    
    public static DatabaseConneection getIstance() throws Exception{
        
        if(instance==null)
            instance= new DatabaseConneection();
        
        return instance;
    }
    
    
}
