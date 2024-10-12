package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.api.ManutencaoAPI;
import com.example.models.Manutencao;

public class ManutencaoController {
    private List<Manutencao> manutencao;

    public ManutencaoController() {
        manutencao = new ArrayList<>();
    }

    // Métodos CRUD
    public void createManutencao(Manutencao manutencao) {
        ManutencaoAPI.postManutencao(manutencao);
        this.manutencao.add(manutencao);
    }

    public List<Manutencao> readManutencao() {
        manutencao = ManutencaoAPI.getHistoricoManutencao(); // Chama a API e obtém todo o histórico de manutenções
        return manutencao;
    }

    public Manutencao readManutencao(String id) {
        for (Manutencao m : manutencao) { // Usando 'm' para diferenciar da lista 'manutencao'
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null; // Caso não encontre a manutenção com o ID
    }
    public void updateManutencao(String id, Manutencao manutencaoAtualizada) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID da manutenção não pode ser vazio.");
        }
    
        // Atualiza a manutenção na API
        ManutencaoAPI.putManutencao(manutencaoAtualizada);
    
        // Atualiza a lista local
        for (int i = 0; i < manutencao.size(); i++) {
            if (manutencao.get(i).getId().equals(id)) {
                manutencao.set(i, manutencaoAtualizada); // Atualiza a manutenção local
                return; // Atualização concluída
            }
        }
    
        // Se a manutenção não for encontrada, lança uma exceção
        throw new IllegalArgumentException("Manutenção com ID " + id + " não encontrada.");
    }
    
    public void deleteManutencao(String id) {
        for (int i = 0; i < manutencao.size(); i++) {
            if (manutencao.get(i).getId().equals(id)) {
                // Remove a manutenção da API
                ManutencaoAPI.deleteManutencao(id);
                // Remove da lista local
                manutencao.remove(i);
                return; // Exclusão concluída
            }
        }
        throw new IllegalArgumentException("Manutenção com ID " + id + " não encontrada.");
    }
    
}
