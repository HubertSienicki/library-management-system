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
public class RetrieveEmployeeData {

    public RetrieveEmployeeData() {
    }
    
    public String[] returnEmployeeData(String firstname){
        String[] empData = new String[6];
        String sql = "SELECT * FROM EMPLOYEE NATURAL JOIN CREDENTIALS WHERE FIRSTNAME = '" + firstname + "'";
        
        try{    
            Connection conn = DriverManager.getConnection("jdbc:h2:~/lib-DB", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet execute = stmt.executeQuery(sql);
            
            while(execute.next()){
                empData[0] = Integer.toString(execute.getInt("EMPID"));
                empData[1] = execute.getString("FIRSTNAME");
                empData[2] = execute.getString("LASTNAME");
                empData[3] = Integer.toString(execute.getInt("CREDID"));
                empData[4] = execute.getString("USERNAME");
                empData[5] = execute.getString("PASSWORD");
            }
            return empData;
        
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
   }
    public int returnEmpID(String username){
        int empID = 0;
        
        String sql = "SELECT EMPID FROM EMPLOYEE NATURAL JOIN CREDENTIALS WHERE USERNAME = '" + username + "'";
        try{
            Connection conn = DriverManager.getConnection("jdbc:h2:~/lib-DB", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet execute = stmt.executeQuery(sql);
            
            execute.next();
            empID = execute.getInt("EMPID");
            
        } catch(SQLException e){
            System.out.println(e);
        }
        return empID;
    }
}
