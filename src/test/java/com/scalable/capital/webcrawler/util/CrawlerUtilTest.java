/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.util;

import com.scalable.capital.webcrawler.bean.LinkBean;
import com.scalable.capital.webcrawler.error.NoLibrariesReturnedException;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Test
    public void testPrintTopLibraries() throws NoLibrariesReturnedException {
        System.out.println("printTopLibraries");
        ArrayList<LinkBean> librarylist = new ArrayList<>();
        librarylist.add(new LinkBean("http://www.a.com/a.js", "a.js", "123"));
        librarylist.add(new LinkBean("http://www.a.com/b.js", "b.js", "123"));
        librarylist.add(new LinkBean("http://www.a.com/c.js", "c.js", "123"));
        librarylist.add(new LinkBean("http://www.a.com/g.js", "g.js", "129"));
        librarylist.add(new LinkBean("http://www.a.com/d.js", "d.js", "129"));
        librarylist.add(new LinkBean("http://www.a.com/e.js", "e.js", "126"));
        librarylist.add(new LinkBean("http://www.a.com/f.js", "f.js", "127"));
        librarylist.add(new LinkBean("http://www.a.com/h.js", "h.js", "128"));

        HashMap<String, Integer> expResult = new HashMap<>();
        expResult.put("a.js", 3);
        expResult.put("g.js", 2);
        expResult.put("e.js", 1);
        expResult.put("f.js", 1);
        expResult.put("h.js", 1);

        HashMap<String, Integer> result = CrawlerUtil.printTopFiveLibraries(librarylist);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

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

        for (LinkBean l : librarylist) {
            assertEquals(l.getName(), "jquery 1");
        }
    }

}
