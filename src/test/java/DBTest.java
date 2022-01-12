/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import library.management.system.Database.TestDBConnection;
import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kneiv
 */
public class DBTest {
    
    private TestDBConnection TEST;
    
    public DBTest() {
    }
 
    /**
     *
     * @throws Exception
     */
    
    @Before
    public void setUp() throws Exception {
        TEST = new TestDBConnection();
    }


   @Test
   public void testConnection(){
       assertTrue("Should equal true", TEST.returnConnectionConfirmed());
   }
}
