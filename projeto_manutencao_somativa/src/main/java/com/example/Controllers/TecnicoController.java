package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.Models.Tecnico;
import com.example.api.TecnicoAPI;

public class TecnicoController {
    private List<Tecnico> tecnicos;

    
    public TecnicoController() {
        tecnicos = new ArrayList<>();
    }

    // Create - Adicionar um novo técnico
    public void createTecnico(Tecnico tecnico) {
        tecnicos.add(tecnico);
        
    }

    // Read - Listar todos os técnicos
    public List<Tecnico> readTecnicos() {
        tecnicos = TecnicoAPI
        
        .getTecnicos(); // Busca os técnicos do backend
        return tecnicos;
    }

    // Update - Atualizar um técnico existente
    public boolean updateTecnico(String id, Tecnico novoTecnico) {
        for (int i = 0; i < tecnicos.size(); i++) {
            if (tecnicos.get(i).getId().equals(id)) { // Verifica se o ID é igual
                tecnicos.set(i, novoTecnico);
                // Lógica adicional para atualizar o técnico no backend, se necessário
                return true; // Atualização bem-sucedida
            }
        }
        return false; // Retorna false se o técnico com o ID não for encontrado
    }

    // Delete - Remover um técnico pelo ID
    public boolean deleteTecnico(String id) {
        for (int i = 0; i < tecnicos.size(); i++) {
            if (tecnicos.get(i).getId().equals(id)) { // Verifica se o ID é igual
                tecnicos.remove(i);
                // Lógica adicional para remover o técnico do backend, se necessário
                return true; // Remoção bem-sucedida
            }
        }
        return false; // Retorna false se o técnico com o ID não for encontrado
    }
}
