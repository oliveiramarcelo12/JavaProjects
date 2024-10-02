package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LeituraAPI {
    public void exemplo() {
        try {
            URL url = new URL("https://api.github.com/users/oliveiramarcelo12");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            if (status != 200) {
                throw new Exception("Erro de conexão: " + status);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String linha;

            while ((linha = br.readLine()) != null) {
                response.append(linha);
            }
            br.close();

            // Imprime a resposta da API
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace(); // Melhoria no tratamento de exceções
        }
    }
}