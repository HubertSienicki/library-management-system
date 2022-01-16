package DBTESTS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import library.management.system.Database.RetrieveEmployeeData;
import library.management.system.Database.TestDBConnection;
import library.management.system.Database.checkValidCredentials;
import library.management.system.Database.retrieveCredentials;
import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kneiv
 */
public class DBTest {
    
    private TestDBConnection testConnection;
    private retrieveCredentials testConnectionRetrieval;
    private checkValidCredentials credCheckTrue;
    private checkValidCredentials credCheckFalse;
    private String[] tempCreds;
    private RetrieveEmployeeData emp;
    private String[] empData;
    
    public DBTest() {
    }
 
    /**
     *
     * @throws Exception
     */
    
    @Before
    public void setUp() throws Exception {
        testConnection = new TestDBConnection();
        testConnectionRetrieval = new retrieveCredentials();
        tempCreds = testConnectionRetrieval.returnCredentials(0);
        credCheckTrue = new checkValidCredentials("root", "123");
        credCheckFalse = new checkValidCredentials("admin", "321");
        emp = new RetrieveEmployeeData();
        empData = emp.returnEmployeeData("John");
    }


   @Test
   public void testConnection(){
       assertTrue("Should equal true", testConnection.returnConnectionConfirmed());
   }
   
   @Test
   public void credIDCheck(){
       assertEquals("ID should equal to 0", "0", tempCreds[0]);
   }
   
   @Test
   public void credUsernameCheck(){
       assertEquals("Username should equal to root", "root", tempCreds[1]);
   }
   
   @Test
   public void credPasswordCheck(){
       assertEquals("Password should equal to 123", "123", tempCreds[2]);
   }
   
   @Test
   public void checkValidCredentials(){
       assertTrue("Should return TRUE", credCheckTrue.checkValidCredentials());
       assertEquals("Counter should equal 1", 1, credCheckTrue.counter);
       assertFalse("Should return FALSE", credCheckFalse.checkValidCredentials());
   }
   
   @Test
   public void checkValidEmp(){
       assertEquals("Employee id should equal 1", "1", empData[0]);
       assertEquals("First name should equal John", "John", empData[1]);
       assertEquals("Last name should equal Doe", "Doe", empData[2]);
       assertEquals("Credentials id should equal 1", "1", empData[3]);
       assertEquals("Username should equal johndoe", "johndoe", empData[4]);
       assertEquals("Password should equal 123123", "123123", empData[5]);
   }
}
