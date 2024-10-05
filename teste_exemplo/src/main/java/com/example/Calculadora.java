package com.example;

public class Calculadora {
    //soma
    public double soma(double a, double b){
        return a + b;
    }

    //subtrai
    public double subtracao(double a, double b){
        return a - b;
    }

    //multiplica
    public double multiplicacao(double a, double b){
        return a * b;
    }
    //divide
    public double divisao(double a, double b){
        if(b == 0){
            throw new IllegalArgumentException("Não é possível dividir por zero!");  //lançando uma exceção personalizada
        }
        return a / b;
    

}

}