package com.example;

public class Livro extends Produto implements Transportavel {
    private  double volume;


    public  Livro (String nome , double preco, double volume) {
       super(nome, preco);
       this.volume = volume;
    }

@Override
public double calcularPeso(){
    double peso = volume*1;
    return peso;
}
@Override
public double calcularFrete(){
    double valorFrete = calcularPeso()*2;
    return valorFrete;
}  
}
