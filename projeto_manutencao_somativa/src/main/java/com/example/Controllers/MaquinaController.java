package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.Models.Maquina;

public class MaquinaController {
    private List<Maquina> maquinas;

    public MaquinaController() {
        maquinas = new ArrayList<>();
    }

    // Create - Adicionar uma nova máquina
    public void createMaquina(Maquina maquina) {
        maquinas.add(maquina);
    }

    // Read - Listar todas as máquinas
    public List<Maquina> ReadMaquinas() {
        return maquinas;
    }

    // Read - Buscar uma máquina por ID (assumindo que a classe Maquina tem um campo 'id')
    public Maquina getMaquinaById(String id) {
        for (Maquina maquina : maquinas) {
            if (maquina.getId() == id) {
                return maquina;
            }
        }
        return null; // Retorna null se a máquina com o ID não for encontrada
    }

    // Update - Atualizar uma máquina existente
    public boolean updateMaquina(String id, Maquina novaMaquina) {
        for (int i = 0; i < maquinas.size(); i++) {
            if (maquinas.get(i).getId() == id) {
                maquinas.set(i, novaMaquina);
                return true; // Atualização bem-sucedida
            }
        }
        return false; // Retorna false se a máquina com o ID não for encontrada
    }

    // Delete - Remover uma máquina pelo ID
    public boolean deleteMaquina(String id) {
        for (int i = 0; i < maquinas.size(); i++) {
            if (maquinas.get(i).getId() == id) {
                maquinas.remove(i);
                return true; // Remoção bem-sucedida
            }
        }
        return false; // Retorna false se a máquina com o ID não for encontrada
    }
}
