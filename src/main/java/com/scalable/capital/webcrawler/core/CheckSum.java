/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.core;

import static com.scalable.capital.webcrawler.util.GeneralUtils.getSHA1;
import static com.scalable.capital.webcrawler.util.GeneralUtils.readBytesFromFile;



/**
 *
 * @author Ehtiram Abdullayev
 */
public class CheckSum {

    
    public static String checkSumFile(String path){
        byte[] readBytesFromFile = readBytesFromFile(path);
        return getSHA1(readBytesFromFile);
    }
    public static String checkSumFile(byte[] readBytesFromFile){
        return getSHA1(readBytesFromFile);
    }
    
}
