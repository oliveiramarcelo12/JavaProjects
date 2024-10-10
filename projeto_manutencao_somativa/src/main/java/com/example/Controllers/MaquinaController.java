package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.api.MaquinaAPI;
import com.example.Models.Maquina;

public class MaquinaController {
    private List<Maquina> maquinas;

    public MaquinaController() {
        maquinas = new ArrayList<>();
    }

    //métodos - CRUD
    public void createMaquina(Maquina maquina){
        this.maquinas.add(maquina);
    }

    public List<Maquina> readMaquinas(){
        maquinas = MaquinaAPI.getMaquinas();
        return maquinas;
    }

    public void updateMaquina(int posicao,Maquina maquina){
        maquinas.set(posicao, maquina);
    }

    public void deleteMaquina(int posicao){
        maquinas.remove(posicao);
    }
}