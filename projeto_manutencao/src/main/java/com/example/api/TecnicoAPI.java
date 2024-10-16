package com.example.api;

import com.example.models.Tecnico;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;

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

    // Método para adicionar um técnico (POST)
    public static void postTecnico(Tecnico tecnico) {
        JSONObject tecnicoObject = new JSONObject();
        tecnicoObject.put("id", tecnico.getId());
        tecnicoObject.put("nome", tecnico.getNome());
        tecnicoObject.put("especialidade", tecnico.getEspecialidade());
        tecnicoObject.put("disponibilidade", tecnico.getDisponibilidade());

        if (!tecnicoObject.isEmpty()) {
            ApiConnection.postData("tecnicos", tecnicoObject.toString());
        }
    }

    // Método para atualizar um técnico (PUT)
    public static void putTecnico(Tecnico tecnico) {
        JSONObject tecnicoObject = new JSONObject();
        tecnicoObject.put("nome", tecnico.getNome());
        tecnicoObject.put("especialidade", tecnico.getEspecialidade());
        tecnicoObject.put("disponibilidade", tecnico.getDisponibilidade());

        if (!tecnicoObject.isEmpty()) {
            String endpoint = "tecnicos/" + tecnico.getId();
            ApiConnection.putData(endpoint, tecnicoObject.toString());
        }
    }

    // Método para deletar um técnico (DELETE)
    public static void deleteTecnico(String id) {
        String endpoint = "tecnicos/" + id;
        ApiConnection.deleteData(endpoint);
    }
}
