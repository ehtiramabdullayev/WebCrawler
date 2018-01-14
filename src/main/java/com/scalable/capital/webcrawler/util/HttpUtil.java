/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Ehtiram Abdullayev
 */
public class HttpUtil {

    public static String getPage(String url) throws IOException {
        HttpURLConnection conn = null;
        URL commonUrl = new URL(url);
        conn = (HttpURLConnection) commonUrl.openConnection();

        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

        int repCode = conn.getResponseCode();

//        if (conn instanceof HttpURLConnection) {
//            conn = (HttpURLConnection) conn;
//        } else if (conn instanceof HttpsURLConnection) {
//            conn = (HttpsURLConnection) conn;
//        }
        BufferedReader buffRead;
        if (repCode >= 200 && repCode < 300) {
            buffRead = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            buffRead = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder build = new StringBuilder();

        while (buffRead.readLine() != null) {
            build.append(buffRead.readLine());

        }
        return build.toString();

    }

    // public String down
}
