package com.example.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
 //POST
public static void postData(String endpoint, String inputData){
    try {
        URL url = new URL(API_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

         try (BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(connection.getOutputStream(), "UTF-8"))){
            bw.write(inputData);
            bw.flush();
            

        }
        
        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_CREATED) {
            throw new Exception("Erro ao criar usuário" + status);
            
        }
        System.out.println("Cadastro Realizado com sucesso");
    connection.disconnect();

}catch(Exception e){
    e.printStackTrace();
}
} 
}