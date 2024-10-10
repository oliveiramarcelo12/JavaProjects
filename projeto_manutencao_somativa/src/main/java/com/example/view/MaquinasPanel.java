package com.example.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.MaquinaController;
import com.example.Models.Maquina;

public class MaquinasPanel extends JPanel {
    // ATRIBUTOS
    private MaquinaController maquinaController;
    private JTable maquinasTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarMaquina;

    // Construtor
    public MaquinasPanel() {
        super(new BorderLayout());
        maquinaController = new MaquinaController();

        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nome", "Fabricante","Modelo", "Detalhes", "Localização","Tempo Vida"
        }, 0);
        maquinasTable = new JTable(tableModel);

        // criar a tabela
        List<Maquina> maquinas = maquinaController.readMaquinas();
        for (Maquina maquina : maquinas) {
            tableModel.addRow(new Object[] {
                    maquina.getId(),
                    maquina.getNome(),
                    maquina.getFabricante(),
                    maquina.getModelo(),
                    maquina.getDetalhes(),
                    maquina.getLocalizacao(),
                    maquina.getTempoVidaEstimado()
            });
        }
        JScrollPane scrollPane = new JScrollPane(maquinasTable);
        this.add(scrollPane,BorderLayout.CENTER);

        //adicionar os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarMaquina = new JButton("Cadastrar");
        btnSalvarAlteracoes = new JButton("Salvar");
        painelInferior.add(btnCadastrarMaquina);
        painelInferior.add(btnSalvarAlteracoes);
        this.add(painelInferior,BorderLayout.SOUTH);

        //Criar as ActionListener para Botões

    }
}