package com.example;

import java.net.HttpURLConnection;
import java.net.URL;

public class LeituraAPI {
    public void exemplo(){
       try {
        URL url = new URL("https://api.github.com/users/oliveiramarcelo12");
        HttpURLConnection con =  (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        if (status !=200) {
            throw new  Exception("Erro de Conexão");
            
        }
       } catch (Exception e) {
       
       }
    }
}
