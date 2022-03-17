/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author kneiv
 */
public class checkValidCredentials {
    private String username;
    private String password;
    public int counter;

    public checkValidCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean checkValidCredentials(){
        boolean flag = false;
        this.counter = 0;
        String DBPASSWORD = "";
        String sql = "SELECT PASSWORD FROM CREDENTIALS WHERE USERNAME = '" + this.username + "'";
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:h2:~/lib-DB", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet execute = stmt.executeQuery(sql);
            
            while(execute.next()){
                this.counter++;
                DBPASSWORD = execute.getString("PASSWORD");
            }
            
        }catch(SQLException e){
            System.out.println(e);
            return flag;
        }
        
        System.out.println(counter);
        System.out.println(DBPASSWORD);
        switch(counter){
                
                case 0 -> {
                    JOptionPane.showMessageDialog(null, "There are no associated credentials in the database", "Failure", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                case 1 -> {
                    if(this.password.equals(DBPASSWORD)){
                        flag = true; 
                        JOptionPane.showMessageDialog(null, "You have been logged in correctly.");
                        ManageLoginStatus login = new ManageLoginStatus(this.username);
                        try{
                            login.LogInUser();
                        }catch(IOException e){
                            System.out.println(e);
                        }
                        
                        return flag;
                    }
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "There is a password duplication, please contact the administrator for further details");
                }
            }
            
            return flag;
    }
}
