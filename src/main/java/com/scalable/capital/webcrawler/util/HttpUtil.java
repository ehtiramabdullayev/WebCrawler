/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.util;

import com.scalable.capital.webcrawler.TestCheckSum;
import static com.scalable.capital.webcrawler.util.GeneralUtils.getSHA1;
import static com.scalable.capital.webcrawler.util.GeneralUtils.readBytesFromFile;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        conn.setRequestProperty("User-Agent", "");

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

    public static byte[] downloadUrl(final String urlString) {
        byte[] data = null;
        try (BufferedInputStream in = new BufferedInputStream(new URL(urlString).openStream());
                ByteArrayOutputStream output = new ByteArrayOutputStream(1024);) {
            int count;
            while ((count = in.read()) != -1) {
                output.write(count);
            }
            data = output.toByteArray();

        } catch (MalformedURLException ex) {
        } catch (IOException ex) {
        }
        return data;
    }

}
