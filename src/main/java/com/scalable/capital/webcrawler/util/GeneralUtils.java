/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Master
 */
public class GeneralUtils {
     public static String getSHA1(byte[] data) {
        byte[] sha1hash;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            sha1hash = new byte[1024];
            md.update(data);
            sha1hash = md.digest();
            return toHex(sha1hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toHex(byte[] data) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int high = ((data[i] >> 4) & 0xf) << 4;
            int low = data[i] & 0xf;
            if (high == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(high | low));
        }
        return s.toString();
    }

    public static byte[] readBytesFromFile(String filePath) {
        byte[] bytesArray = null;
        File file = new File(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            bytesArray = new byte[(int) file.length()];
            fileInputStream.read(bytesArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytesArray;

    }
	
	public static Map<String,Integer> sortByValue(Map<String,Integer> unsortedMap){
        List<Map.Entry<String,Integer>> list = new LinkedList<>(unsortedMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
          @Override
          public int compare(Map.Entry<String,Integer> o1,
                             Map.Entry<String,Integer> o2){
              return (o2.getValue().compareTo(o1.getValue()));
          
          }
        });
        

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

}
