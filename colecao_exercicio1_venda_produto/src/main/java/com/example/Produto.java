package com.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Produto {
    private String nome;
    private double valor;
    @Override
    public String toString() {
        return "Nome Produto"+nome+"Valor: "+valor;
    }

}
