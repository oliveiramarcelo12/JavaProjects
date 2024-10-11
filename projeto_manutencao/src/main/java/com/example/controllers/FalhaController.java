package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Falha;
import com.example.api.FalhaAPI;

public class FalhaController {
    private List<Falha> falhas;

    public FalhaController() {
        falhas = new ArrayList<>();
    }

    // Create - Adicionar uma nova falha
    public void createFalha(Falha falha) {
        falhas.add(falha);
      
    }

    // Read - Listar todas as falhas
    public List<Falha> readFalhas() {
        falhas = FalhaAPI.getFalhas(); // Busca as falhas do backend
        return falhas;
    }

    // Update - Atualizar uma falha existente
    public boolean updateFalha(String id, Falha novaFalha) {
        for (int i = 0; i < falhas.size(); i++) {
            if (falhas.get(i).getId().equals(id)) { // Verifica se o ID é igual
                falhas.set(i, novaFalha);
                // Lógica adicional para atualizar a falha no backend, se necessário
                return true; // Atualização bem-sucedida
            }
        }
        return false; // Retorna false se a falha com o ID não for encontrada
    }

    // Delete - Remover uma falha pelo ID
    public boolean deleteFalha(String id) {
        for (int i = 0; i < falhas.size(); i++) {
            if (falhas.get(i).getId().equals(id)) { // Verifica se o ID é igual
                falhas.remove(i);
                // Lógica adicional para remover a falha do backend, se necessário
                return true; // Remoção bem-sucedida
            }
        }
        return false; // Retorna false se a falha com o ID não for encontrada
    }
}
