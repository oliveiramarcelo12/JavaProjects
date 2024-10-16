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
// Método para enviar uma nova falha para a API
public static void postFalha(Falha falha) {
    // Criar um JSONObject com os dados da falha
    JSONObject falhaObject = new JSONObject();
    falhaObject.put("id", falha.getId()); // ID da falha, se necessário
    falhaObject.put("maquinaId", falha.getMaquinaID());
    falhaObject.put("data", falha.getData().toString()); // Converter LocalDate para String
    falhaObject.put("problema", falha.getProblema());
    falhaObject.put("prioridade", falha.getPrioridade());
    falhaObject.put("operador", falha.getOperador());

    // Verificar se o JSONObject não está vazio antes de enviar
    if (!falhaObject.isEmpty()) {
        ApiConnection.postData("falhas", falhaObject.toString()); // Enviar os dados para a API
    }
}
// PUT
public static void putFalha(Falha falha) {
    JSONObject falhaObject = new JSONObject();
    falhaObject.put("maquinaId", falha.getMaquinaID());
    falhaObject.put("data", falha.getData().toString());
    falhaObject.put("problema", falha.getProblema());
    falhaObject.put("prioridade", falha.getPrioridade());
    falhaObject.put("operador", falha.getOperador());

    if (!falhaObject.isEmpty()) {
        // O endpoint deve incluir o ID da falha que você deseja atualizar
        String endpoint = "falhas/" + falha.getId();
        ApiConnection.putData(endpoint, falhaObject.toString());
    }
}

}
