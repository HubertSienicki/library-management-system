/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.Modules;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author kneiv
 */
//Class for handling the clock in the mainpage
public class ClockModule {
   private final int hour;
   private final int min;
   private final int sec;
   private final int AM_PM;
   private final String currentTime;
    
    public ClockModule(){
        Calendar cal = new GregorianCalendar();
        hour = cal.get(Calendar.HOUR);
        min = cal.get(Calendar.MINUTE);
        sec = cal.get(Calendar.SECOND);
        AM_PM = cal.get(Calendar.AM_PM);
        
        
        /*
        Formatting added for a leading zero
        Also, IF AM_PM value is 1, then the hour is PM, else its AM
        */
        
        if(AM_PM == 1) {
            currentTime = hour +":" + String.format("%02d", min) + ":" + 
                    String.format("%02d", sec) + " " + "PM";
        }else{
            currentTime = hour +":" + String.format("%02d", min) + ":" +  
                    String.format("%02d", sec) + " " + "AM";
        }
    }
    
    public String returnCurrentTime(){
        return this.currentTime;
    }
        
}
