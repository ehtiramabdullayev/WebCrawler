package com.scalable.capital.webcrawler.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Master
 */
public class GeneralUtils {

    public static String getSHA1(byte[] data) {
        byte[] sha1hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            sha1hash = new byte[1024];
            md.update(data);
            sha1hash = md.digest();
        } catch (Exception e) {
            System.out.println("SHA1 could't be created");
        }
        return byteArrayToHex(sha1hash);

    }



    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
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

    public static HashMap<String, Integer> sortByValue(Map<String, Integer> unsortedMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (o2.getValue().compareTo(o1.getValue()));

            }
        });
        HashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {

            sortedMap.put(entry.getKey(), entry.getValue());

        }
        return sortedMap;
    }

}
