package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import netscape.javascript.JSObject;




public class UsuarioController {
    private List<Usuario> usuarios;
    private URL url;
    public UsuarioController() {
        usuarios = new ArrayList<>();
    
    }
    public void read(){
        try {//estabelecer conexão
              url = new URL("http://localhost:3000/usuarios");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //verificar status da conexão

            int status = con.getResponseCode();
            if (status != 200) {
                throw new Exception("Erro de conexão: " + status);
            }
             BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer content = new StringBuffer();
            String linha;

            while ((linha = br.readLine()) != null) {
                content.append(linha);
            }
            br.close();
            // converter arquivos de texto para dados da classe usuario
            JSONArray dadosUsuarios = new JSONArray(content.toString());
            for (int i = 0; i < dadosUsuarios.length(); i++) {
                JSONObject usuarioJson = dadosUsuarios.getJSONObject(i);
                usuarios.add(new Usuario(
                    usuarioJson.getString("id"),
                    usuarioJson.getString("nome"),
                    usuarioJson.getInt("idade"),
                    usuarioJson.getString("endereco")
                ));
               
                
            }
            System.out.println(usuarios.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //create
 public void createUser(Usuario usuario) {
    try {
        // Estabelecer conexão
         url = new URL("http://localhost:3000/usuarios");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true); // Para indicar que vamos enviar dados no corpo da requisição

        // Converter o objeto Usuario em JSON
        JSONObject usuarioJson = new JSONObject();
        usuarioJson.put("nome", usuario.getNome());
        usuarioJson.put("idade", usuario.getIdade());
        usuarioJson.put("endereco", usuario.getEndereco());

       try (BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(con.getOutputStream(), "UTF-8"))){
            bw.write(usuarioJson.toString());
            bw.flush();
            

        }
        
        int status = con.getResponseCode();
        if (status != HttpURLConnection.HTTP_CREATED) {
            throw new Exception("Erro ao criar usuário" + status);
            
        }
        System.out.println("Usuario Cadastrado com sucesso");
    }
    

     catch (Exception e) {
        e.printStackTrace();
    }
}

}