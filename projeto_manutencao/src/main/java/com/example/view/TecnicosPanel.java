package com.example.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.example.controllers.RelatorioCompletoController;
import com.example.controllers.TecnicoController;
import com.example.models.Tecnico;

public class TecnicosPanel extends JPanel {
    // ATRIBUTOS
    private TecnicoController tecnicoController;
    private JTable tecnicosTable;
    private DefaultTableModel tableModel;
    private JButton btnCadastrarTecnico;
    private JButton btnDeletarTecnico;
    private JButton btnGerarRelatorio;

    // Construtor
    public TecnicosPanel() {
        super(new BorderLayout());
        tecnicoController = new TecnicoController();

        // Definir as colunas da tabela
        tableModel = new DefaultTableModel(new Object[] {
                "ID", "Nome", "Especialidade", "Disponibilidade"
        }, 0);
        tecnicosTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desabilitar edição de células
            }
        };

        // Estilizar a tabela
        tecnicosTable.setFillsViewportHeight(true);
        tecnicosTable.setBackground(Color.WHITE);
        tecnicosTable.setFont(new Font("Arial", Font.PLAIN, 12));
        tecnicosTable.setRowHeight(25);
        tecnicosTable.setSelectionBackground(Color.LIGHT_GRAY);
        tecnicosTable.setSelectionForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(tecnicosTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Preencher a tabela com dados dos técnicos
        atualizarTabela();

        // Adicionar os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarTecnico = new JButton("Cadastrar");
        btnDeletarTecnico = new JButton("Deletar");

        // Estilizar os botões
        estilizarBotao(btnCadastrarTecnico);
        estilizarBotao(btnDeletarTecnico);
        painelInferior.add(btnCadastrarTecnico);
        painelInferior.add(btnDeletarTecnico);
        this.add(painelInferior, BorderLayout.SOUTH);

        btnGerarRelatorio = new JButton("Gerar Relatório");
        estilizarBotao(btnGerarRelatorio);
        painelInferior.add(btnGerarRelatorio);

        // Criar ActionListeners para os botões
        btnCadastrarTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioCadastro(null); // Para cadastrar novo técnico
            }
        });

        btnGerarRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioCompletoController relatorioController = new RelatorioCompletoController();
                relatorioController.gerarRelatorioCompleto(); // Gera o relatório completo
            }
        });
        
        
        btnDeletarTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tecnicosTable.getSelectedRow();
                if (selectedRow != -1) {
                    String nomeTecnico = (String) tableModel.getValueAt(selectedRow, 1); // Obtendo o nome do técnico
                    int confirm = JOptionPane.showConfirmDialog(null, 
                            "Você realmente deseja deletar o técnico \"" + nomeTecnico + "\"? Esta ação não pode ser desfeita.", 
                            "Confirmação de Deleção", 
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        String id = (String) tableModel.getValueAt(selectedRow, 0);
                        tecnicoController.deleteTecnico(id); // Deletar pelo ID
                        tableModel.removeRow(selectedRow); // Remover da tabela
                        JOptionPane.showMessageDialog(null, "Técnico deletado com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um técnico para deletar!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Detectar duplo clique na tabela
        tecnicosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Duplo clique
                    int selectedRow = tecnicosTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Tecnico tecnicoSelecionado = new Tecnico();
                        tecnicoSelecionado.setId((String) tableModel.getValueAt(selectedRow, 0));
                        tecnicoSelecionado.setNome((String) tableModel.getValueAt(selectedRow, 1));
                        tecnicoSelecionado.setEspecialidade((String) tableModel.getValueAt(selectedRow, 2));
                        tecnicoSelecionado.setDisponibilidade((String) tableModel.getValueAt(selectedRow, 3));
                        mostrarFormularioCadastro(tecnicoSelecionado); // Atualizar técnico
                    }
                }
            }
        });
    }

    private void estilizarBotao(JButton botao) {
        botao.setBackground(new Color(70, 130, 180));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpar antes de atualizar
        List<Tecnico> tecnicos = tecnicoController.readTecnicos();
        for (Tecnico tecnico : tecnicos) {
            tableModel.addRow(new Object[] {
                    tecnico.getId(),
                    tecnico.getNome(),
                    tecnico.getEspecialidade(),
                    tecnico.getDisponibilidade()
            });
        }
    }

    private void mostrarFormularioCadastro(Tecnico tecnico) {
        JDialog dialog = new JDialog();
        dialog.setTitle(tecnico == null ? "Cadastrar Técnico" : "Atualizar Técnico");
        dialog.setModal(true);
        dialog.setSize(300, 300);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField txtId = new JTextField(tecnico != null ? tecnico.getId() : "");
        txtId.setEditable(tecnico == null); // O ID não pode ser alterado durante a atualização
        JTextField txtNome = new JTextField(tecnico != null ? tecnico.getNome() : "");
        JTextField txtEspecialidade = new JTextField(tecnico != null ? tecnico.getEspecialidade() : "");
        JTextField txtDisponibilidade = new JTextField(tecnico != null ? tecnico.getDisponibilidade() : "");

        dialog.add(new JLabel("ID:"));
        dialog.add(txtId);
        dialog.add(new JLabel("Nome:"));
        dialog.add(txtNome);
        dialog.add(new JLabel("Especialidade:"));
        dialog.add(txtEspecialidade);
        dialog.add(new JLabel("Disponibilidade:"));
        dialog.add(txtDisponibilidade);

        JButton btnSalvar = new JButton("Salvar");
        estilizarBotao(btnSalvar);
        dialog.add(btnSalvar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtId.getText().isEmpty() || txtNome.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                Tecnico novoTecnico = tecnico != null ? tecnico : new Tecnico();
                novoTecnico.setId(txtId.getText());
                novoTecnico.setNome(txtNome.getText());
                novoTecnico.setEspecialidade(txtEspecialidade.getText());
                novoTecnico.setDisponibilidade(txtDisponibilidade.getText());
        
                if (tecnico == null) {
                    if (tecnicoController.existeTecnico(novoTecnico.getId())) {
                        JOptionPane.showMessageDialog(dialog, "ID já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    tecnicoController.createTecnico(novoTecnico);
                    tableModel.addRow(new Object[] {
                            novoTecnico.getId(),
                            novoTecnico.getNome(),
                            novoTecnico.getEspecialidade(),
                            novoTecnico.getDisponibilidade()
                    });
                    JOptionPane.showMessageDialog(dialog, "Técnico cadastrado com sucesso!");
                } else {
                    int confirm = JOptionPane.showConfirmDialog(dialog, 
                            "Você está prestes a atualizar os dados do técnico \"" + tecnico.getNome() + "\". Deseja continuar?", 
                            "Confirmação de Atualização", 
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        tecnicoController.updateTecnico(novoTecnico);
                        int selectedRow = tecnicosTable.getSelectedRow();
                        tableModel.setValueAt(novoTecnico.getNome(), selectedRow, 1);
                        tableModel.setValueAt(novoTecnico.getEspecialidade(), selectedRow, 2);
                        tableModel.setValueAt(novoTecnico.getDisponibilidade(), selectedRow, 3);
                        JOptionPane.showMessageDialog(dialog, "Técnico atualizado com sucesso!");
                    } else {
                        return; // Se o usuário não confirmar, sai da função
                    }
                }
                dialog.dispose();
                atualizarTabela();
            }
        });

        dialog.setVisible(true);
    }
}
