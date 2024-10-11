package com.example.view;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.controllers.MaquinaController;
import com.example.models.Maquina;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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

        tableModel = new DefaultTableModel(new Object[] {
                "ID", "Nome", "Fabricante", "Modelo", "Detalhes", "Localização", "Tempo Vida"
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
        this.add(scrollPane, BorderLayout.CENTER);

        // adicionar os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarMaquina = new JButton("Cadastrar");
        btnSalvarAlteracoes = new JButton("Salvar");
        painelInferior.add(btnCadastrarMaquina);
        painelInferior.add(btnSalvarAlteracoes);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Criar as ActionListener para Botões
        adicionarActionListeners();
    }

    private void adicionarActionListeners() {
        // ActionListener para o botão "Cadastrar"
        btnCadastrarMaquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastrarMaquinaForm(maquinaController); // Abrir o formulário
            }
        });
        // ActionListener para o botão "Salvar"
btnSalvarAlteracoes.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Percorrer as linhas da tabela e salvar as alterações no banco de dados
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String id = (String) tableModel.getValueAt(i, 0);
            String nome = (String) tableModel.getValueAt(i, 1);
            String fabricante = (String) tableModel.getValueAt(i, 2);
            String modelo = (String) tableModel.getValueAt(i, 3);
            String detalhes = (String) tableModel.getValueAt(i, 4);
            String localizacao = (String) tableModel.getValueAt(i, 5);
            int tempoVida = Integer.parseInt(tableModel.getValueAt(i, 6).toString());

            // Obter a máquina existente pelo ID
            Maquina maquinaExistente = maquinaController.readMaquina(id);
            LocalDate dataAquisicao = maquinaExistente != null ? maquinaExistente.getDataAquisicao() : LocalDate.now();

            // Atualizar a máquina utilizando o builder
            Maquina maquinaAtualizada = Maquina.builder()
                .id(id)
                .nome(nome)
                .fabricante(fabricante)
                .modelo(modelo)
                .detalhes(detalhes)
                .localizacao(localizacao)
                .tempoVidaEstimado(tempoVida)
                .dataAquisicao(dataAquisicao) // Reutiliza a data original se possível
                .manual(maquinaExistente != null ? maquinaExistente.getManual() : "manual.pdf") // Reutilizar o manual ou definir um valor padrão
                .build();
            
            // Atualizar a máquina no controlador
            int posicao = i;  // A posição atual da linha na tabela
            maquinaController.updateMaquina(posicao, maquinaAtualizada);
        }

        JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso!");
    }
});

    }
}
