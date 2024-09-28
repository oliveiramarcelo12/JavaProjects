package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GerencimentoVendas {
    private Map<String, List<Produto>> vendasClientes;

    public GerencimentoVendas() {
        vendasClientes = new HashMap<>();
    }

    // registrar venda - IDCliente
    public void venda(String cpf, Produto produto) {
        for (String cpfCliente : vendasClientes.keySet()) {
            if (cpfCliente.equalsIgnoreCase(cpf)) {
                // criando uma lista de produtos que já existem
                List<Produto> produtos = vendasClientes.get(cpfCliente);
                produtos.add(produto);//list<produtos>
                vendasClientes.put(cpf, produtos);//hashmap<cpf,list<produtos>>
                return;
            }
        }
        List<Produto> produtos = new ArrayList<>();
            produtos.add(produto);
            vendasClientes.put(cpf, produtos);
    }
    // consultar vendas por cliente
    public void produtosCliente(String cpf){
        List<Produto> produtos = vendasClientes.getOrDefault(
            cpf,Collections.emptyList());
        if (produtos.isEmpty()) {
            System.out.println("Nenhuma venda Cadastrada");
        }else{
            for (Produto produto: produtos) {
                System.out.println(produto.toString());
            }
        }
    }

    // listar vendas acima de determinado valor
    public void produtosClienteAcimaDe(String cpf, double valorMinimo){
        List<Produto> produtos = vendasClientes.getOrDefault(
            cpf,Collections.emptyList());
        if (produtos.isEmpty()) {
            System.out.println("Nenhuma venda Cadastrada");
        }else{
            List<Produto> resultado = produtos.stream()
                    .filter(p->p.getValor()>=valorMinimo)
                    .collect(Collectors.toList());
            if (resultado.isEmpty()) {
                System.out.println(
                    "Nenhuma compra atingiu o valor mínimo"
                );
            }else{
                for (Produto produto : resultado) {
                    System.out.println(produto.toString());
                }
            }
        }
    }

}