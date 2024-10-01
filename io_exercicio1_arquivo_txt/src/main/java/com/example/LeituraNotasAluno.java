package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LeituraNotasAluno {
    private List<Aluno> alunos;

    public LeituraNotasAluno() {
        alunos = new ArrayList<>();
    }

    // Método de leitura
    public void leituraFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("notas.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] alunoDados = linha.split(",");
                Aluno alunoNota = new Aluno(
                    alunoDados[0],
                    Double.parseDouble(alunoDados[1]),
                    Double.parseDouble(alunoDados[2]),
                    Double.parseDouble(alunoDados[3])
                );
                alunos.add(alunoNota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(alunos);
    }

    // Aluno com maior nota
    public void alunoMaiorNota() {
        String nome = null; // Inicializa como null
        double maiorNota = Double.MIN_VALUE; // Começa com o menor valor possível

        for (Aluno aluno : alunos) {
            double notaAtual = aluno.maiorNotaAluno();
            if (notaAtual > maiorNota) {
                maiorNota = notaAtual;
                nome = aluno.getNome();
            }
        }

        if (nome != null) {
            System.out.println("O Aluno com maior nota é " + nome + " e a maior nota é = " + maiorNota);
        } else {
            System.out.println("Nenhum aluno encontrado.");
        }
    }

    // Aluno com menor nota
    public void alunoMenorNota() {
        String nome = null; // Inicializa como null
        double menorNota = Double.MAX_VALUE; // Começa com o maior valor possível

        for (Aluno aluno : alunos) {
            double notaAtual = aluno.menorNotaAluno();
            if (notaAtual < menorNota) {
                menorNota = notaAtual;
                nome = aluno.getNome();
            }
        }

        if (nome != null) {
            System.out.println("O Aluno com menor nota é " + nome + " e a menor nota é = " + menorNota);
        } else {
            System.out.println("Nenhum aluno encontrado.");
        }
    }

    // Cálculo da média
    public void mediaTurma() {
        double mediaNotasTurma = 0;
        for (Aluno aluno : alunos) {
            mediaNotasTurma += aluno.media();
        }
        mediaNotasTurma /= alunos.size();
        System.out.println("A média da turma é " + mediaNotasTurma);
    }
}
