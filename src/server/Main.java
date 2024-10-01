/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

/**
 * @author Administrator
 */
public class Main {

    public static String ip = "http://14.225.219.58/gro/";

    public static void main(String args) {
        try {
            URL url = new URL(ip);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int check = con.getResponseCode();
            if (check == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String input;

                StringBuffer sb = new StringBuffer();

                while ((input = br.readLine()) != null) {
                    sb.append(input);
                }
                br.close();

                System.out.println("Nội dung web");
                System.out.println(sb.toString());
            } else {
                System.out.println("web cút");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
