package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.api.MaquinaAPI;
import com.example.models.Maquina;

public class MaquinaController {
    private List<Maquina> maquinas;

    public MaquinaController() {
        maquinas = new ArrayList<>();
    }

    //métodos - CRUD
    public void createMaquina(Maquina maquina){
        MaquinaAPI.postMaquina(maquina);
        this.maquinas.add(maquina);

    }
    public List<Maquina> readMaquinas() {
        maquinas = MaquinaAPI.getMaquinas(); // Chama a API e obtém todas as máquinas
        return maquinas;
    }
    
    public Maquina readMaquina(String id) {
        for (Maquina maquina : maquinas) {
            if (maquina.getId().equals(id)) {
                return maquina;
            }
        }
        return null; // Caso não encontre a máquina com o ID
    }
    
    public void updateMaquina(Maquina maquina) {
        // Primeiro, atualiza a máquina na API
        MaquinaAPI.putMaquina(maquina);
    
        // Depois, atualiza a lista local
        for (int i = 0; i < maquinas.size(); i++) {
            if (maquinas.get(i).getId().equals(maquina.getId())) {
                maquinas.set(i, maquina);
                return; // Atualização concluída
            }
        }
        throw new IllegalArgumentException("Máquina com ID " + maquina.getId() + " não encontrada.");
    }
    
    
    public void deleteMaquina(int posicao) {
        if (posicao >= 0 && posicao < maquinas.size()) {
            // Primeiro, obtém o ID da máquina que será deletada
            String id = maquinas.get(posicao).getId();
            
            // Deleta a máquina na API
            MaquinaAPI.deleteMaquina(id);
            
            // Depois, remove a máquina da lista local
            maquinas.remove(posicao);
        } else {
            throw new IndexOutOfBoundsException("Posição inválida para deleção");
        }
    }
    
}