package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.api.TecnicoAPI;
import com.example.models.Tecnico;

public class TecnicoController {
    private List<Tecnico> tecnicos;

    public TecnicoController() {
        tecnicos = new ArrayList<>();
    }

    // Create - Adicionar um novo técnico
    public void createTecnico(Tecnico tecnico) {
        TecnicoAPI.postTecnico(tecnico); // Chama a API para adicionar o técnico
        tecnicos.add(tecnico); // Adiciona à lista local
    }

    // Read - Listar todos os técnicos
    public List<Tecnico> readTecnicos() {
        tecnicos = TecnicoAPI.getTecnicos(); // Busca os técnicos do backend
        return tecnicos;
    }

    // Read - Obter um técnico pelo ID
    public Tecnico readTecnico(String id) {
        for (Tecnico tecnico : tecnicos) {
            if (tecnico.getId().equals(id)) {
                return tecnico; // Retorna o técnico encontrado
            }
        }
        return null; // Caso não encontre o técnico com o ID
    }

    // Update - Atualizar um técnico existente
    public void updateTecnico(Tecnico tecnico) {
        TecnicoAPI.putTecnico(tecnico); // Atualiza na API

        for (int i = 0; i < tecnicos.size(); i++) {
            if (tecnicos.get(i).getId().equals(tecnico.getId())) {
                tecnicos.set(i, tecnico); // Atualiza a lista local
                return; // Atualização concluída
            }
        }
        throw new IllegalArgumentException("Técnico com ID " + tecnico.getId() + " não encontrado.");
    }

    // Delete - Remover um técnico pelo ID
    public void deleteTecnico(String id) {
        for (int i = 0; i < tecnicos.size(); i++) {
            if (tecnicos.get(i).getId().equals(id)) {
                TecnicoAPI.deleteTecnico(id); // Deleta na API
                tecnicos.remove(i); // Remove da lista local
                return; // Remoção concluída
            }
        }
        throw new IllegalArgumentException("Técnico com ID " + id + " não encontrado.");
    }

        // Método que verifica se um técnico com o ID já existe
        public boolean existeTecnico(String id) {
            for (Tecnico tecnico : tecnicos) {
                if (tecnico.getId().equals(id)) {
                    return true; // Retorna true se o ID já estiver cadastrado
                }
            }
            return false; // Retorna false se o ID não for encontrado
        }
    
}
