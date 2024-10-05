package com.example;

public interface ContaBancariaRepository {
    ContaBancaria encontrarPorNumero(String numeroConta);
    void atualizar(ContaBancaria contaBancaria);

}
