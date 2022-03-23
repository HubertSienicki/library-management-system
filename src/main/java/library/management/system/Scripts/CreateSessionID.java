/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.Scripts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kneiv
 */
public class CreateSessionID {
    private String Timestamp;
    private String username;
    private String SessionID;

    public CreateSessionID(String TimeStamp, String username) {
        this.Timestamp = TimeStamp;
        this.username = username;
    }
    
    public CreateSessionID(){
        
    }

    private void setSessionID(String SessionID) {
        this.SessionID = SessionID;
    }
    
    
    //Returns sessionID for user when he logs in
    public String returnSessionID(){
        //substrings timestamp
        String rev_timestamp = Timestamp.substring(0, 4) + Timestamp.substring(5,7) + Timestamp.substring(8, 10) + Timestamp.substring(11, 13) + Timestamp.substring(14, 16) + Timestamp.substring(17, 19);
        System.out.println(rev_timestamp);
        rev_timestamp += ':' + this.username; // concats username and timestamp to create an ssid
        this.setSessionID(rev_timestamp);
        try {
            this.saveSessionID();
        } catch (IOException e) {
            System.out.println(e);
        }
        
        return this.SessionID;
    }
    
    //saves ssid to a file
    public boolean saveSessionID() throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ssid"))) {
            writer.write(this.SessionID);
            return true;
        }
    }
    
    public String retrieveSessionID(){
        ///get the ssid back from a file
        String tempSSID = "";
        try(BufferedReader reader = new BufferedReader(new FileReader("ssid"))){
            tempSSID = reader.readLine();
            reader.close();
        }catch(IOException e){
            System.out.println(e);
        }
        return tempSSID;
    }
}
