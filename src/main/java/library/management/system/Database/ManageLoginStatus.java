
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import library.management.system.Modules.LoggerModule;
import library.management.system.Scripts.CreateSessionID;
import library.management.system.Scripts.ReturnTimestamp;

/**
 *
 * @author kneiv
 */
public class ManageLoginStatus {
    private String ssid;
    private final RetrieveEmployeeData emp;
    private final ReturnTimestamp timestamp;
    private final String Timestamp;
    private final CreateSessionID temp;
    private final String username;
    private final LoggerModule logger;

    public ManageLoginStatus(String username) {
        emp = new RetrieveEmployeeData();
        timestamp = new ReturnTimestamp(); // Returns the timestamp of logging in
        Timestamp = timestamp.returnTimestamp();
        temp = new CreateSessionID(Timestamp, username);
        this.username = username;
        logger = new LoggerModule();
    }
    
    public String getUsername(){
        return this.username;
    }
  
    
    public boolean LogInUser() throws IOException{
        this.ssid = temp.returnSessionID(); // Current SSID
        String sql = "INSERT INTO LOGGED_IN VALUES(" + emp.returnEmpID(username) + ", " + true + ", " + "'" + Timestamp + "'" + ", " + "'" + ssid + "'" + ")";
        System.out.println(sql);
        try{
            Connection conn = DriverManager.getConnection("jdbc:h2:~/lib-DB", "root", "");
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            
            //Updating logs
            logger.Logger_Login_Accepted(this);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "The data could not be loaded into the database, please contact the administrator. " + e, "Warning", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public boolean LogOutUser() throws FileNotFoundException, IOException{
        //Retrieve ssid from a file
        String ret_ssid;
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:h2:~/lib-DB", "root", "");
            Statement stmt = conn.createStatement();
            ret_ssid = temp.retrieveSessionID();
            String sql = "DELETE FROM LOGGED_IN WHERE SSID = " + "'" + ret_ssid + "';";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            
            //Updating logs
            logger.Logger_Logout_Accepted(this);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "We could not log you out, please contact the administrator. " + e, "Warning", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
        
}
