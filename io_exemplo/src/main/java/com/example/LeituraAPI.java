package com.example;

import java.io.BufferedReader;
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
        //conexao estabelecida
        BufferedReader br = new BufferedReader(
            new InputStreamReader(con.getInputStream())
        );
       } catch (Exception e) {
       
       }
    }
}
