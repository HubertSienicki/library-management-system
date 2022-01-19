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
import javax.swing.JOptionPane;
import library.management.system.Scripts.ReturnTimestamp;

/**
 *
 * @author kneiv
 */
public class ManageLoginStatus {

    public ManageLoginStatus() {
    }
    
    public boolean LogInUser(String username){
        RetrieveEmployeeData emp = new RetrieveEmployeeData();
        ReturnTimestamp timestamp = new ReturnTimestamp();
        
        String sql = "INSERT INTO LOGGED_IN VALUES(" + emp.returnEmpID(username) + ", " + true + ", " + "'" + timestamp.returnTimestamp() + "'" + ")";
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:h2:~/lib-DB", "root", "");
            Statement stmt = conn.createStatement();
            boolean isFailed = stmt.execute(sql);
            if(isFailed){
                return true;
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "The data could not be loaded into the database, please contact the administrator.", "Warning", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
