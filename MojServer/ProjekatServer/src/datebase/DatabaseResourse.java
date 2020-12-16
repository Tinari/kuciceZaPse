/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 *
 * @author Win 7
 */
public class DatabaseResourse {
    
    Properties properties;

    public DatabaseResourse() throws Exception {
        properties=new Properties();
        FileInputStream in= new FileInputStream("settings.properties");
        properties.load(in);
    }
    
    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
    
    public void setUrl(String url) {
        properties.setProperty("url", url);
    }
    
    public void setUsername(String username) {
        properties.setProperty("username", username);
    }
    
    public void setPassword(String password) {
        properties.setProperty("password", password);
    }
    
    
    
}
