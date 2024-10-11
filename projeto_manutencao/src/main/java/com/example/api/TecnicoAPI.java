package com.example.api;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Tecnico;

import org.json.JSONArray;
import org.json.JSONObject;

public class TecnicoAPI {

    // Método para buscar a lista de técnicos
    public static List<Tecnico> getTecnicos() {
        String json = ApiConnection.getData("tecnicos");
        List<Tecnico> tecnicos = new ArrayList<>();

        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Tecnico tecnico = new Tecnico(
                    jsonObject.getString("id"), // id como String
                    jsonObject.getString("nome"),
                    jsonObject.getString("especialidade"),
                    jsonObject.getString("disponibilidade")
                );
                tecnicos.add(tecnico);
            }
        }
        return tecnicos;
    }
    
}

