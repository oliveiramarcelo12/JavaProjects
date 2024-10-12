package com.example.api;


import org.json.JSONArray;
import org.json.JSONObject;

import com.example.models.Maquina;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class MaquinaAPI {

    public static List<Maquina> getMaquinas() {
        String json = ApiConnection.getData("maquinas");
        List<Maquina> maquinas = new ArrayList<>();
    
        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
    
                // Verificar se o campo "codigo" existe
                String codigo = "";
                if (jsonObject.has("codigo")) {
                    codigo = jsonObject.getString("codigo");
                } else {
                    System.out.println("Campo 'codigo' não encontrado para a máquina com ID: " + jsonObject.getString("id"));
                }
    
                // Continuar preenchendo os demais campos normalmente
                Maquina maquina = new Maquina(
                    jsonObject.getString("id"),
                    codigo,  // Usar o valor de "codigo", mesmo que possa estar vazio
                    jsonObject.getString("nome"),
                    jsonObject.getString("modelo"),
                    jsonObject.getString("fabricante"),
                    LocalDate.parse(jsonObject.getString("dataAquisicao")),
                    jsonObject.getInt("tempoVidaEstimado"),
                    jsonObject.getString("localizacao"),
                    jsonObject.getString("detalhes"),
                    jsonObject.getString("manual")
                );
                maquinas.add(maquina);
            }
        }
        return maquinas;
    }
    
    public static void postMaquina(Maquina maquina) {
        JSONObject maquinaObject = new JSONObject();
        maquinaObject.put("ID",maquina.getId());
        maquinaObject.put("codigo",maquina.getCodigo());
        maquinaObject.put("nome",maquina.getNome());
        maquinaObject.put("modelo",maquina.getModelo());
        maquinaObject.put("fabricante",maquina.getFabricante());
        maquinaObject.put("dataAquisicao", maquina.getDataAquisicao().toString());
        maquinaObject.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
        maquinaObject.put("localizacao",maquina.getLocalizacao());
        maquinaObject.put("detalhes",maquina.getDetalhes());
        maquinaObject.put("manual", maquina.getManual());
        
        if (!maquinaObject.isEmpty()) {
            ApiConnection.postData("maquinas", maquinaObject.toString());
            
        }
    }

  // PUT
public static void putMaquina(Maquina maquina) {
    JSONObject maquinaObject = new JSONObject();
    maquinaObject.put("codigo", maquina.getCodigo());
    maquinaObject.put("nome", maquina.getNome());
    maquinaObject.put("modelo", maquina.getModelo());
    maquinaObject.put("fabricante", maquina.getFabricante());
    maquinaObject.put("dataAquisicao", maquina.getDataAquisicao().toString());
    maquinaObject.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
    maquinaObject.put("localizacao", maquina.getLocalizacao());
    maquinaObject.put("detalhes", maquina.getDetalhes());
    maquinaObject.put("manual", maquina.getManual());

    if (!maquinaObject.isEmpty()) {
        // O endpoint deve incluir o ID da máquina que você deseja atualizar
        String endpoint = "maquinas/" + maquina.getId();
        ApiConnection.putData(endpoint, maquinaObject.toString());
    }
}

// DELETE
public static void deleteMaquina(String id) {
    // O endpoint deve incluir o ID da máquina que você deseja deletar
    String endpoint = "maquinas/" + id;
    ApiConnection.deleteData(endpoint);
}
  
}
