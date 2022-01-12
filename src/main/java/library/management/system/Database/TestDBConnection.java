/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kneiv
 */
public class TestDBConnection {

    public TestDBConnection() {
    }
    
    public boolean returnConnectionConfirmed(){
        Connection conn;
        try {
            ArrayList temp = new ArrayList();
            conn = DriverManager.getConnection("jdbc:h2:~/lib-DB", "root", "");
            Statement stmt = conn.createStatement();
            
            ResultSet execute = stmt.executeQuery("Select * from test");
            
            while(execute.next()){
                System.out.println(execute.getString("name"));
            }
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(TestDBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
}
