package com.example.api;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.Models.Maquina;

import org.json.JSONArray;
import org.json.JSONObject;




public class MaquinaAPI {
   public static List<Maquina> getMaquinas() {
    String json = ApiConnection.getData("maquinas");
    List<Maquina> maquinas = new ArrayList<>();

    if (json != null) {
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Maquina maquina = new Maquina(
                jsonObject.getString("id"), // Altere para getString
                jsonObject.getString("codigo"),
                jsonObject.getString("nome"),
                jsonObject.getString("modelo"),
                jsonObject.getString("fabricante"),
                LocalDate.parse(jsonObject.getString("dataAquisicao")), // Conversão de String para LocalDate
                jsonObject.getLong("tempoVidaEstimado"), // Altere para getLong
                jsonObject.getString("localizacao"),
                jsonObject.getString("detalhes"),
                jsonObject.getString("manual")
            );
            maquinas.add(maquina);
        }
    }
    return maquinas;
}
}
