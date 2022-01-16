/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.Scripts;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author kneiv
 */
public class ReturnTimestamp {
    //TODO: return current timestamp
    
    public ReturnTimestamp() {
    }
    
    public String returnTimestamp(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp current = new Timestamp(System.currentTimeMillis());
        String timestamp = date.format(current);
        
        return timestamp;
    }
    
    
    
}
