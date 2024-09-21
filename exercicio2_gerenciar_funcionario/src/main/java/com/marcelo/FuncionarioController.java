package com.marcelo;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class FuncionarioController {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public FuncionarioController() {
        funcionarios = new ArrayList<>();
    }

    // Método para adicionar funcionário
    public void addFuncionario() {
        try {
            String nome = JOptionPane.showInputDialog("Digite o Nome do Funcionário");
            int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a Idade"));
            double salario = Double.parseDouble(JOptionPane.showInputDialog("Digite o Salário"));
            Funcionario funcionario = new Funcionario(nome, idade, salario);
            funcionarios.add(funcionario);
            JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Por favor, tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para listar todos os funcionários
    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado.");
        } else {
            StringBuilder lista = new StringBuilder();
            for (Funcionario funcionario : funcionarios) {
                lista.append(funcionario.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, lista.toString(), "Lista de Funcionários", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para buscar funcionário pelo nome
    public void buscarFuncionario() {
        String nome = JOptionPane.showInputDialog("Digite o Nome do Funcionário a ser Buscado");
        boolean encontrado = false;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().equalsIgnoreCase(nome)) {
                JOptionPane.showMessageDialog(null, funcionario.toString(), "Funcionário Encontrado", JOptionPane.INFORMATION_MESSAGE);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para calcular a média salarial
    public void calculoMediaSalario() {
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado para calcular a média salarial.");
        } else {
            double somaSalarios = 0;
            for (Funcionario funcionario : funcionarios) {
                somaSalarios += funcionario.getSalario();
            }
            double mediaSalarios = somaSalarios / funcionarios.size();
            JOptionPane.showMessageDialog(null, "A média de salários é: R$" + mediaSalarios);
        }
    }

    // Método para remover funcionário pelo nome
public void removerFuncionario() {
    String nome = JOptionPane.showInputDialog("Digite o Nome do Funcionário a ser Removido");
    boolean encontrado = false;
    Funcionario funcionarioARemover = null;

    for (Funcionario funcionario : funcionarios) {
        if (funcionario.getNome().equalsIgnoreCase(nome)) {
            funcionarioARemover = funcionario;
            encontrado = true;
            break;
        }
    }

    if (encontrado) {
        funcionarios.remove(funcionarioARemover);
        JOptionPane.showMessageDialog(null, "Funcionário " + nome + " removido com sucesso!");
    } else {
        JOptionPane.showMessageDialog(null, "Funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}



}
