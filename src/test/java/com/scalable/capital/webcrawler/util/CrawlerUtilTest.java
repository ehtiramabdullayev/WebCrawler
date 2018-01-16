/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.util;

import com.scalable.capital.webcrawler.bean.LinkBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ehtiram Abdullayev
 */
public class CrawlerUtilTest {
    
    public CrawlerUtilTest() {
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

//    /**
//     * Test of getGoogleLinks method, of class CrawlerUtil.
//     */
//    @Test
//    public void testGetGoogleLinks() {
//        System.out.println("getGoogleLinks");
//        String content = "";
//        String selector = "";
//        List<String> expResult = null;
//        List<String> result = CrawlerUtil.getGoogleLinks(content, selector);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getJsLibrariesFromLink method, of class CrawlerUtil.
//     */
//    @Test
//    public void testGetJsLibrariesFromLink() {
//        System.out.println("getJsLibrariesFromLink");
//        String url = "";
//        ArrayList<LinkBean> expResult = null;
//        ArrayList<LinkBean> result = CrawlerUtil.getJsLibrariesFromLink(url);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of printTopLibraries method, of class CrawlerUtil.
     */
//    @Test
//    public void testPrintTopLibraries() {
//        System.out.println("printTopLibraries");
//        ArrayList<LinkBean> librarylist = null;
//        HashMap<String, Integer> expResult = null;
//        HashMap<String, Integer> result = CrawlerUtil.printTopLibraries(librarylist);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of deduplicateNames method, of class CrawlerUtil.
     */
    @Test
    public void testDeduplicateNames() {
        System.out.println("deduplicateNames");
        ArrayList<LinkBean> librarylist = new ArrayList<>();
        
        librarylist.add(new LinkBean("http://jquery.com", "jquery 1", "67"));
        librarylist.add(new LinkBean("http://jquery.com", "jquery 2", "67"));
        librarylist.add(new LinkBean("http://jquery.com", "jquery 3", "67"));
        
        CrawlerUtil instance = new CrawlerUtil();
        instance.deduplicateNames(librarylist);
        
        for(LinkBean l:librarylist){
            assertEquals(l.getName(), "jquery 1");
        }
    }
    
}
