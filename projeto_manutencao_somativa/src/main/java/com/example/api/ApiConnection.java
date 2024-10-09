package com.example.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {
    private static final String API_URL = "http://localhost:3000/";


    public static String getData(String endpoint) {
        try {
            URL url = new URL(API_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();


            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }


            in.close();
            connection.disconnect();
            return content.toString();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
}
