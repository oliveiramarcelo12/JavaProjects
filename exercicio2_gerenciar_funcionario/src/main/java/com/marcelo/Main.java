package com.marcelo;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        FuncionarioController gerenciarFuncionario = new FuncionarioController();
        int operacao = 0;

        do {
            String menu = "Escolha uma operação:\n"
                        + "1 - Adicionar Funcionário\n"
                        + "2 - Listar Funcionários\n"
                        + "3 - Buscar Funcionário\n"
                        + "4 - Calcular Média Salarial\n"
                        + "5 - Remover Funcionário\n"
                        + "6 - Sair";
            
            try {
                operacao = Integer.parseInt(JOptionPane.showInputDialog(menu));

                switch (operacao) {
                    case 1:
                        gerenciarFuncionario.addFuncionario();
                        break;
                    case 2:
                        gerenciarFuncionario.listarFuncionarios();
                        break;
                    case 3:
                        gerenciarFuncionario.buscarFuncionario();
                        break;
                    case 4:
                        gerenciarFuncionario.calculoMediaSalario();
                        break;
                    case 5:
                        gerenciarFuncionario.removerFuncionario(); // Nova opção de remover funcionário
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, "Saindo...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Por favor, insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } while (operacao != 6);
    }
}
