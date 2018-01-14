/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler;

import static com.scalable.capital.webcrawler.util.GeneralUtils.getSHA1;
import static com.scalable.capital.webcrawler.util.GeneralUtils.readBytesFromFile;



/**
 *
 * @author Master
 */
public class TestCheckSum {

    public static void main(String[] args) {
        String dataFile = "b.txt";
        byte[] readBytesFromFile = readBytesFromFile(dataFile);
        System.out.println(getSHA1(readBytesFromFile));
    }

   
}
