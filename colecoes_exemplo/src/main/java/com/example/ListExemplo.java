package com.example;

import java.util.ArrayList;
import java.util.List;

public class ListExemplo {
  private List <String> nomes;
  public ListExemplo() {
    nomes = new ArrayList<>();
  }
  //adicionar
    public void adicionarNome(String nome) {
        nomes.add(nome);
        System.out.println(nome.indexOf(nome));
    }
//listar
    public void listarNomes() {
        for (String nome : nomes) {
            System.out.println(nome);
        }
    }
    //remove
    public void deleteNome(String nome) {
        nomes.remove(nome);
        System.out.println("Nome removido com sucesso!");
    }
    //update
    public void modificarNome(int index, String nome){
        String nomeAnterior = nomes.get(index);
        nomes.set(index, nome);
        System.out.println("Nome da posição " + index +" "+ nomeAnterior+ " alterado com sucesso!");
    }
}
