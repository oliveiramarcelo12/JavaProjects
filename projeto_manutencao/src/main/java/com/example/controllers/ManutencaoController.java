package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Manutencao;
import com.example.api.ManutencaoAPI;

public class ManutencaoController {
    private List<Manutencao> manutencoes;

    public ManutencaoController() {
        manutencoes = new ArrayList<>();
    }

    // Create - Adicionar uma nova manutenção
    public void createManutencao(Manutencao manutencao) {
        
        manutencoes.add(manutencao);
        
    }

    // Read - Listar todas as manutenções
    public List<Manutencao> readManutencoes() {
        manutencoes = ManutencaoAPI.getManutencoes(); // Busca as manutenções do backend
        return manutencoes;
    }

    // Update - Atualizar uma manutenção existente
    public boolean updateManutencao(String id, Manutencao novaManutencao) {
        for (int i = 0; i < manutencoes.size(); i++) {
            if (manutencoes.get(i).getId().equals(id)) { // Verifica se o ID é igual
                manutencoes.set(i, novaManutencao);
                // Você pode adicionar lógica para atualizar no backend, se necessário
                return true; // Atualização bem-sucedida
            }
        }
        return false; // Retorna false se a manutenção com o ID não for encontrada
    }

    // Delete - Remover uma manutenção pelo ID
    public boolean deleteManutencao(String id) {
        for (int i = 0; i < manutencoes.size(); i++) {
            if (manutencoes.get(i).getId().equals(id)) { // Verifica se o ID é igual
                manutencoes.remove(i);
                // Você pode adicionar lógica para remover do backend, se necessário
                return true; // Remoção bem-sucedida
            }
        }
        return false; // Retorna false se a manutenção com o ID não for encontrada
    }
}
