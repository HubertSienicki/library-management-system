/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system;

import library.management.system.Database.TestDBConnection;

/**
 *
 * @author kneiv
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestDBConnection test = new TestDBConnection();
        System.out.println(test.returnConnectionConfirmed());
    }
    
}