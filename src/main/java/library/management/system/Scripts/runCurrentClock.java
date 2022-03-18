/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.Scripts;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

/**
 *
 * @author kneiv
 */
//Class for handling the clock in the mainpage
public class runCurrentClock {
    private LocalTime currentTime;
    private String formattedTime;
    private char[] tempTime;
    
    
    public runCurrentClock(){
        
    }
    
    private String timeFormatter(){
        String format = "HH:mm:ss";
        this.currentTime = java.time.LocalTime.now();
        this.tempTime = this.currentTime.toString().toCharArray();
        //Raw time format is: 15:55:10.424178667, this algorithm just cuts of the milisecond part
        for (int i = 0; i < 7; i++) {
            this.formattedTime += tempTime[i];
        }
        
        return this.formattedTime;
    }
    //Returns currentTime
    public String getTime(){
        return this.timeFormatter();
    }
}
