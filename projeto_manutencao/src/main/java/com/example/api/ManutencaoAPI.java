package com.example.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Manutencao;

import org.json.JSONArray;
import org.json.JSONObject;

public class ManutencaoAPI {

    // Método para obter o histórico de manutenções
    public static List<Manutencao> getHistoricoManutencao() {
        String json = ApiConnection.getData("historicoManutencao");
        List<Manutencao> historicoManutencao = new ArrayList<>();

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
                historicoManutencao.add(manutencao);
            }
        }
        return historicoManutencao;
    }

    // Método para enviar uma nova manutenção para a API
    public static void postManutencao(Manutencao manutencao) {
        JSONObject manutencaoObject = new JSONObject();
        manutencaoObject.put("id", manutencao.getId());
        manutencaoObject.put("maquinaId", manutencao.getMaquinaID());
        manutencaoObject.put("data", manutencao.getData().toString()); // Converter LocalDate para String
        manutencaoObject.put("tipo", manutencao.getTipo());
        manutencaoObject.put("pecasTrocadas", manutencao.getPecasTrocadas());
        manutencaoObject.put("tempoDeParada", manutencao.getTempoDeParada());
        manutencaoObject.put("tecnicoId", manutencao.getTecnicoID());
        manutencaoObject.put("observacoes", manutencao.getObservacoes());

        if (!manutencaoObject.isEmpty()) {
            ApiConnection.postData("manutencoes", manutencaoObject.toString());
        }
    }

    // Método para atualizar uma manutenção existente
    public static void putManutencao(Manutencao manutencao) {
        JSONObject manutencaoObject = new JSONObject();
        manutencaoObject.put("maquinaId", manutencao.getMaquinaID());
        manutencaoObject.put("data", manutencao.getData().toString());
        manutencaoObject.put("tipo", manutencao.getTipo());
        manutencaoObject.put("pecasTrocadas", manutencao.getPecasTrocadas());
        manutencaoObject.put("tempoDeParada", manutencao.getTempoDeParada());
        manutencaoObject.put("tecnicoId", manutencao.getTecnicoID());
        manutencaoObject.put("observacoes", manutencao.getObservacoes());

        // Faz a chamada PUT na API
        ApiConnection.putData("manutencoes/" + manutencao.getId(), manutencaoObject.toString());
    }

    // Método para deletar uma manutenção
    public static void deleteManutencao(String manutencaoId) {
        // Faz a chamada DELETE na API
        ApiConnection.deleteData("manutencoes/" + manutencaoId);
    }
}
