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

/**
 *
 * @author kneiv
 */
public class retrieveCredentials {

    public retrieveCredentials() {
    }
    
    public String[] returnCredentials(int credID){
        String[] credentials = new String[3];
        try{
            String sql = "SELECT CREDID, USERNAME, PASSWORD FROM CREDENTIALS WHERE CREDID = " + credID;
            
            Connection conn = DriverManager.getConnection("jdbc:h2:~/lib-DB", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet execute = stmt.executeQuery(sql);
            
            while(execute.next()){
                credentials[0] = Integer.toString(execute.getInt("CREDID"));
                credentials[1] = execute.getString("USERNAME");
                credentials[2] = execute.getString("PASSWORD");
            }
            
            conn.close();
            return credentials;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }
}
