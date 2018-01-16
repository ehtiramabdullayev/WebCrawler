/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Master
 */
public class CheckSumTest {
    
    public CheckSumTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkSumFile method, of class CheckSum.
     */
    @Test
    public void testCheckSumFile_String() {
        System.out.println("checkSumFile");
        //files are identical
        String path1 = "Test1.txt";
        String path2 = "Test930523.txt";

        String expResult = CheckSum.checkSumFile(path1);
        String result = CheckSum.checkSumFile(path2);
        assertEquals(expResult, result);
    }

 
    
}
