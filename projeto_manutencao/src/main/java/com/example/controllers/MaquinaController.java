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
    

    public void updateMaquina(int posicao,Maquina maquina){
        maquinas.set(posicao, maquina);
    }

    public void deleteMaquina(int posicao){
        maquinas.remove(posicao);
    }
}