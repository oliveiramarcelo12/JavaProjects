package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.api.FalhaAPI;
import com.example.models.Falha;

public class FalhaController {
    private List<Falha> falhas;

    public FalhaController() {
        falhas = new ArrayList<>();
    }

    // Create - Adicionar uma nova falha
    public void createFalha(Falha falha) {
        FalhaAPI.postFalha(falha); // Envia a nova falha para a API
        falhas.add(falha); // Adiciona à lista local
    }

    // Read - Listar todas as falhas
    public List<Falha> readFalhas() {
        falhas = FalhaAPI.getFalhas(); // Busca as falhas do backend
        return falhas;
    }

    // Read - Buscar uma falha específica pelo ID
    public Falha readFalha(String id) {
        for (Falha falha : falhas) {
            if (falha.getId().equals(id)) {
                return falha; // Retorna a falha se o ID for encontrado
            }
        }
        return null; // Retorna null se não encontrar a falha com o ID fornecido
    }

    // Update - Atualizar uma falha existente
    public void updateFalha(Falha falha) {
        FalhaAPI.putFalha(falha); // Atualiza a falha na API

        // Atualiza a lista local
        for (int i = 0; i < falhas.size(); i++) {
            if (falhas.get(i).getId().equals(falha.getId())) {
                falhas.set(i, falha); // Atualiza a falha na lista local
                return; // Atualização concluída
            }
        }
        throw new IllegalArgumentException("Falha com ID " + falha.getId() + " não encontrada.");
    }

  
}
