package com.example.api;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Manutencao;

import org.json.JSONArray;
import org.json.JSONObject;

public class ManutencaoAPI {

    public static List<Manutencao> getManutencoes() {
        String json = ApiConnection.getData("manutencoes");
        List<Manutencao> manutencoes = new ArrayList<>();

        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Manutencao manutencao = new Manutencao(
                    jsonObject.getString("id"),
                    jsonObject.getInt("maquinaId"),
                    LocalDate.parse(jsonObject.getString("data")), // Conversão de String para LocalDate
                    jsonObject.getString("tipo"),
                    jsonObject.getString("pecasTrocadas"),
                    jsonObject.getInt("tempoDeParada"),
                    jsonObject.getString("tecnicoId"),
                    jsonObject.getString("observacoes")
                );
                manutencoes.add(manutencao);
            }
        }
        return manutencoes;
    }

}