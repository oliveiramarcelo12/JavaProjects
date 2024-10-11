package com.example.api;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Falha;

import org.json.JSONArray;
import org.json.JSONObject;

public class FalhaAPI {

    // Método para buscar a lista de falhas
    public static List<Falha> getFalhas() {
        String json = ApiConnection.getData("falhas");
        List<Falha> falhas = new ArrayList<>();

        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Falha falha = new Falha(
                    jsonObject.getString("id"), // Corrigido para String
                    jsonObject.getInt("maquinaId"), // Se máquinaId for um inteiro
                    LocalDate.parse(jsonObject.getString("data")), // Conversão de String para LocalDate
                    jsonObject.getString("problema"),
                    jsonObject.getString("prioridade"),
                    jsonObject.getString("operador")
                );
                falhas.add(falha);
            }
        }
        return falhas;
    }

}
