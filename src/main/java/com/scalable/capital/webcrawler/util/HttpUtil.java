/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scalable.capital.webcrawler.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Ehtiram Abdullayev
 */
public class HttpUtil {

    public static String getPage(String url) throws IOException {
        URL commonUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) commonUrl.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        int repCode = conn.getResponseCode();
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
            System.out.println("URL is malformed " + ex.getLocalizedMessage());
        } catch (IOException ex) {
            System.out.println("IO exception occured " + ex.getLocalizedMessage());

        }
        return data;
    }

}
