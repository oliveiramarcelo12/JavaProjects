package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContatoController agenda = new ContatoController(5);
        int operacao = 0;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("\n-------- Agenda de Contatos -------");
            System.out.println("1 - Adicionar Contato");
            System.out.println("2 - Listar Contatos");
            System.out.println("3 - Buscar Contato pelo Nome");
            System.out.println("4 - Apagar Contato pelo Nome");
            System.out.println("5 - Sair");

            try {
                operacao = sc.nextInt();
                sc.nextLine(); // Limpar o buffer de entrada após a leitura de números

                switch (operacao) {
                    case 1:
                        try {
                            System.out.println("Nome:");
                            String nome = sc.nextLine();
                            System.out.println("Endereço:");
                            String endereco = sc.nextLine();
                            System.out.println("Email:");
                            String email = sc.nextLine();
                            System.out.println("Telefone:");
                            String telefone = sc.nextLine();

                            Contato contato = new Contato(nome, email, endereco, telefone);
                            agenda.addContato(contato);
                            System.out.println("Contato adicionado com sucesso!");
                        } catch (AgendaCheiaException e) {
                            System.out.println("Erro: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Erro ao adicionar contato: " + e.getMessage());
                        }
                        break;

                    case 2:
                        agenda.listarContato();
                        break;

                    case 3:
                        try {
                            System.out.println("Digite o Nome a ser buscado:");
                            String nomeBuscado = sc.nextLine();
                            Contato contatoEncontrado = agenda.buscarContato(nomeBuscado);
                            System.out.println("Contato encontrado: " + contatoEncontrado.toString());
                        } catch (ContatoNaoEncontrado e) {
                            System.out.println("Erro: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Erro ao buscar contato: " + e.getMessage());
                        }
                        break;
                    case 4:
                    try {
                        System.out.println("Digite o Nome a ser buscado:");
                        String nomeDeletar = sc.nextLine();
                        agenda.removerContato(nomeDeletar);
                        System.out.println("Contato Deletado Com Sucesso " );
                    } catch (Exception e) {
                        System.out.println(e);
                    }  
                    break;  

                    case 5:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Digite um número válido.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Tente novamente.");
                sc.nextLine(); // Limpar o buffer se houver erro
            }
        } while (operacao != 5);

        sc.close();
    }
}
