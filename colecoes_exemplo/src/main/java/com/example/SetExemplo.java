package com.example;

import java.util.HashSet;
import java.util.Set;

public class SetExemplo {
    private Set<String> nomes;

    public SetExemplo() {
        nomes = new HashSet<>();
    }

    // Adicionar nome
    public void adicionarNome(String nome) {
        if (nomes.add(nome)) {
            System.out.println("Nome adicionado: " + nome + ", HashCode: " + nome.hashCode());
        } else {
            System.out.println("Nome já existe: " + nome);
        }
    }

    // Listar nomes
    public void listarNomes() {
        System.out.println("Lista de nomes:");
        for (String nome : nomes) {
            System.out.println(nome);
        }
    }

    // Remover nome
    public void deleteNome(String nome) {
        if (nomes.remove(nome)) {
            System.out.println("Nome removido com sucesso: " + nome);
        } else {
            System.out.println("Nome não encontrado: " + nome);
        }
    }

    // Modificar nome
    public void modificarNomeIndex(String nome, String nomeNovo) {
        if (nomes.remove(nome)) {
            nomes.add(nomeNovo);
            System.out.println("Nome alterado de " + nome + " para " + nomeNovo);
        } else {
            System.out.println("Nome não encontrado: " + nome);
        }
    }
}
