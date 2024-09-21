package com.marcelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Funcionario {
    private String nome;
    private int idade;
    private double salario;

    //toString
    @Override
    public String toString(){
        return "Nome: "+nome+", Idade:  "+idade+" , Salário: "+salario;
    }

}
