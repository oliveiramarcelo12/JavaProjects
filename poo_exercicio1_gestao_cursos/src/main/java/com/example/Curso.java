package com.example;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Curso {
    //atributos
    private String nomeCurso;
    private List <Aluno> alunos;
    private Professor professor;
    
    //construtor
    public Curso(String nomeCurso){
        this.nomeCurso = nomeCurso;
        alunos = new ArrayList<>();
    }

    // método adicionar professor
    public void addProf(Professor professor){
        this.professor = professor;
    }

    // método para adicionar aluno 
    public void addAluno(Aluno aluno){
        alunos.add(aluno);
    }

    // método infoCurso 
    public void infoCurso(){
        System.out.println("Curso: " +nomeCurso);
        System.out.println("Professor: "+professor.getNome());
        System.out.println("Alunos Matriculados");
        for (Aluno aluno : alunos) {
            System.out.println("Aluno: "+aluno.getNome()+"Ra: "+aluno.getMatricula());
        }
    }

    // método lançar nota 
    public void atribuirNota(String nomeAluno, double notaAluno){
        for (Aluno aluno : alunos) { //percorrer arraylist 
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                aluno.setNota(notaAluno);
                return;
                
            }
            System.out.println("Aluno Não Encontrado");
        
        }
    }

    // método exibirResultadoFinal

    public void exibirResultadoFinal(){
      for (Aluno aluno : alunos) {
        System.out.println(aluno.exibirInfo());
        System.out.println(aluno.avaliarDesempenho());
      }
    }

    
}
