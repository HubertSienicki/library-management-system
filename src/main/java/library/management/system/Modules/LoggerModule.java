/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.Modules;

/**
 *
 * @author kneiv
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import library.management.system.Database.ManageLoginStatus;
import library.management.system.Scripts.CreateSessionID;
import library.management.system.Scripts.ReturnTimestamp;

//TODO: Fix logger substituting lines in log.txt file

public class LoggerModule {
    private final String LOGS_DIR = "C:\\Users\\kneiv\\Documents\\NetBeansProjects\\library-management-system\\log.txt";
    private final String timestamp;
    private final String ssid;  
    
    public LoggerModule() {
        ReturnTimestamp ts = new ReturnTimestamp();
        timestamp = ts.returnTimestamp();
        ssid = new CreateSessionID().retrieveSessionID();
    }
    
    public void Logger_Login_Accepted(ManageLoginStatus status){
        String text = this.timestamp + "| User: " + 
                status.getUsername() + " Succesfully logged into the application. | SSID: " + this.ssid;
        
        //Tries to input logs into a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGS_DIR))) {
            writer.newLine();
            writer.write(text);
            writer.close();
        }catch(IOException e){
             JOptionPane.showMessageDialog(null, "There was a problem loggin data from the application, "
                     + "please contact the administrator for further details" + e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    //Tries to input logs into a file
    public void Logger_Logout_Accepted(ManageLoginStatus status){
        String tempUsername = this.TranslateSSID();
        
        String text = this.timestamp + "| User: " + 
                tempUsername + " Succesfully logged out of the application. | SSID: " + this.ssid;
        
        /*
        Because my solution to logging out was to create a temporary object to use its method 
        (Could change it to abstract method or interface in the future), 
        we need to retrieve the username of currently logged in user from a database in order to succesfully log the username
        */
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGS_DIR))) {
            writer.newLine();
            writer.write(text);
            writer.close();
        }catch(IOException e){
             JOptionPane.showMessageDialog(null, "There was a problem loggin data from the application, "
                     + "please contact the administrator for further details" + e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
    private String TranslateSSID(){
        String username = "";
        for (int i = 15; i < this.ssid.length(); i++) {
            username += this.ssid.charAt(i);
        }
        return username;
    }
}
