package com.example.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.controllers.ManutencaoController;
import com.example.models.Manutencao;

public class ManutencaoPanel extends JPanel {
    // ATRIBUTOS
    private ManutencaoController manutencaoController;
    private JTable manutencoesTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarManutencao;

    // Construtor
    public ManutencaoPanel() {
        super(new BorderLayout());
        manutencaoController = new ManutencaoController();

        // Definir as colunas da tabela para exibir as informações de manutenção
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Data", "Tipo", "Peças Trocadas", "Tempo de Parada", "Técnico ID", "Observações"
        }, 0);
        manutencoesTable = new JTable(tableModel);

        // Obter a lista de manutenções e preenchê-la na tabela
        List<Manutencao> manutencoes = manutencaoController.readManutencoes();
        for (Manutencao manutencao : manutencoes) {
            tableModel.addRow(new Object[] {
                    manutencao.getId(),
                    manutencao.getData(),
                    manutencao.getTipo(),
                    manutencao.getPecasTrocadas(),
                    manutencao.getTempoDeParada(),
                    manutencao.getTecnicoID(),
                    manutencao.getObservacoes()
            });
        }

        JScrollPane scrollPane = new JScrollPane(manutencoesTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Adicionar os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarManutencao = new JButton("Cadastrar");
        btnSalvarAlteracoes = new JButton("Salvar");
        painelInferior.add(btnCadastrarManutencao);
        painelInferior.add(btnSalvarAlteracoes);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Criar as ActionListener para Botões
        // Você pode adicionar funcionalidade de salvar e cadastrar conforme necessário
    }
}
