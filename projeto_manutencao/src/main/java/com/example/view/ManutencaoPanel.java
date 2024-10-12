package com.example.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.example.controllers.ManutencaoController;
import com.example.models.Manutencao;

public class ManutencaoPanel extends JPanel {
    // ATRIBUTOS
    private ManutencaoController manutencaoController;
    private JTable manutencaoTable;
    private DefaultTableModel tableModel;
    private JButton btnCadastrarManutencao;
    private JButton btnDeletarManutencao;

    // Construtor
    public ManutencaoPanel() {
        super(new BorderLayout());
        manutencaoController = new ManutencaoController();

        // Definir as colunas da tabela
        tableModel = new DefaultTableModel(new Object[] {
                "ID", "Data", "Tipo", "Peças Trocadas", "Tempo de Parada", "Técnico ID", "Observações"
        }, 0);
        manutencaoTable = new JTable(tableModel) {
            // Desabilitar edição de células
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Não permite edição em nenhuma célula
            }
        };

        // Preencher a tabela com dados das manutenções
        List<Manutencao> manutencoes = manutencaoController.readManutencao();
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

        JScrollPane scrollPane = new JScrollPane(manutencaoTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Adicionar os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarManutencao = new JButton("Cadastrar");
        btnDeletarManutencao = new JButton("Deletar");
        painelInferior.add(btnCadastrarManutencao);
        painelInferior.add(btnDeletarManutencao);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Criar os ActionListeners para os botões
        btnCadastrarManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioCadastro();
            }
        });

        btnDeletarManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = manutencaoTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Pega o ID da manutenção selecionada
                    String id = (String) tableModel.getValueAt(selectedRow, 0); // Supondo que a primeira coluna é o ID
                    
                    int confirm = JOptionPane.showConfirmDialog(null, "Você realmente deseja deletar esta manutenção?", 
                            "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            // Deletar a manutenção
                            manutencaoController.deleteManutencao(id);
                            tableModel.removeRow(selectedRow); // Remover da tabela
                            JOptionPane.showMessageDialog(null, "Manutenção deletada com sucesso!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao deletar a manutenção: " + ex.getMessage(), "Erro", 
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma manutenção para deletar!", "Erro", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        // Adicionar um MouseListener para a tabela para detectar duplo clique
        manutencaoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = manutencaoTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Pega o ID da manutenção selecionada
                        String id = (String) tableModel.getValueAt(selectedRow, 0);
                        // Busca a manutenção pelo ID
                        Manutencao manutencao = manutencaoController.readManutencao(id);
                        if (manutencao != null) {
                            // Mostra o formulário de atualização
                            mostrarFormularioAtualizacao(manutencao);
                        } else {
                            JOptionPane.showMessageDialog(null, "Manutenção não encontrada!", "Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    private void mostrarFormularioCadastro() {
        // Criar um JDialog para o formulário de cadastro
        JDialog dialog = new JDialog();
        dialog.setTitle("Cadastrar Manutenção");
        dialog.setModal(true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(8, 2)); // Aumentar linhas para incluir mais campos

        // Campos do formulário
        JTextField txtTipo = new JTextField();
        JTextField txtPecasTrocadas = new JTextField();
        JTextField txtTempoParada = new JTextField();
        JTextField txtTecnicoID = new JTextField();
        JTextField txtObservacoes = new JTextField();

        dialog.add(new JLabel("Tipo:"));
        dialog.add(txtTipo);
        dialog.add(new JLabel("Peças Trocadas:"));
        dialog.add(txtPecasTrocadas);
        dialog.add(new JLabel("Tempo de Parada (em horas):"));
        dialog.add(txtTempoParada);
        dialog.add(new JLabel("Técnico ID:"));
        dialog.add(txtTecnicoID);
        dialog.add(new JLabel("Observações:"));
        dialog.add(txtObservacoes);

        // Botão para salvar
        JButton btnSalvar = new JButton("Salvar");
        dialog.add(btnSalvar);

        // Ação do botão de salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Criar uma nova manutenção com os dados do formulário
                Manutencao novaManutencao = new Manutencao();
                novaManutencao.setTipo(txtTipo.getText());
                novaManutencao.setPecasTrocadas(txtPecasTrocadas.getText());

                // Converter tempo de parada para número
                try {
                    int tempoParada = Integer.parseInt(txtTempoParada.getText());
                    novaManutencao.setTempoDeParada(tempoParada);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "O tempo de parada deve ser um número!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Definir o técnico ID e observações
                novaManutencao.setTecnicoID(txtTecnicoID.getText());
                novaManutencao.setObservacoes(txtObservacoes.getText());

                // Salvar a nova manutenção usando o controlador
                manutencaoController.createManutencao(novaManutencao);

                // Adicionar a nova manutenção à tabela
                tableModel.addRow(new Object[] {
                        novaManutencao.getId(), // Supondo que o ID é gerado na API ou em outro lugar
                        LocalDate.now(), // Data da manutenção será a data atual
                        novaManutencao.getTipo(),
                        novaManutencao.getPecasTrocadas(),
                        novaManutencao.getTempoDeParada(),
                        novaManutencao.getTecnicoID(),
                        novaManutencao.getObservacoes()
                });

                dialog.dispose(); // Fecha o diálogo
                JOptionPane.showMessageDialog(ManutencaoPanel.this, "Manutenção cadastrada com sucesso!");
            }
        });

        dialog.setVisible(true); // Mostrar o diálogo
    }

    private void mostrarFormularioAtualizacao(Manutencao manutencao) {
        // Criar um JDialog para o formulário de atualização
        JDialog dialog = new JDialog();
        dialog.setTitle("Atualizar Manutenção");
        dialog.setModal(true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(8, 2));

        // Campos do formulário com os dados atuais da manutenção
        JTextField txtTipo = new JTextField(manutencao.getTipo());
        JTextField txtPecasTrocadas = new JTextField(manutencao.getPecasTrocadas());
        JTextField txtTempoParada = new JTextField(String.valueOf(manutencao.getTempoDeParada()));
        JTextField txtTecnicoID = new JTextField(manutencao.getTecnicoID());
        JTextField txtObservacoes = new JTextField(manutencao.getObservacoes());

        dialog.add(new JLabel("Tipo:"));
        dialog.add(txtTipo);
        dialog.add(new JLabel("Peças Trocadas:"));
        dialog.add(txtPecasTrocadas);
        dialog.add(new JLabel("Tempo de Parada (em horas):"));
        dialog.add(txtTempoParada);
        dialog.add(new JLabel("Técnico ID:"));
        dialog.add(txtTecnicoID);
        dialog.add(new JLabel("Observações:"));
        dialog.add(txtObservacoes);

        // Botão para salvar
        JButton btnSalvar = new JButton("Salvar");
        dialog.add(btnSalvar);

        // Ação do botão de salvar
       // Ação do botão de salvar
btnSalvar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Atualizar os dados da manutenção
        manutencao.setTipo(txtTipo.getText());
        manutencao.setPecasTrocadas(txtPecasTrocadas.getText());

        // Conversão e validação do tempo de parada
        try {
            int tempoParada = Integer.parseInt(txtTempoParada.getText());
            manutencao.setTempoDeParada(tempoParada);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "O tempo de parada deve ser um número!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Definir técnico ID e observações
        manutencao.setTecnicoID(txtTecnicoID.getText());
        manutencao.setObservacoes(txtObservacoes.getText());

        // Atualizar a manutenção usando o controlador
        try {
            manutencaoController.updateManutencao(manutencao.getId(), manutencao);
            // Atualiza a tabela com os novos dados
            int selectedRow = manutencaoTable.getSelectedRow();
            tableModel.setValueAt(manutencao.getTipo(), selectedRow, 2);
            tableModel.setValueAt(manutencao.getPecasTrocadas(), selectedRow, 3);
            tableModel.setValueAt(manutencao.getTempoDeParada(), selectedRow, 4);
            tableModel.setValueAt(manutencao.getTecnicoID(), selectedRow, 5);
            tableModel.setValueAt(manutencao.getObservacoes(), selectedRow, 6);

            dialog.dispose(); // Fecha o diálogo
            JOptionPane.showMessageDialog(ManutencaoPanel.this, "Manutenção atualizada com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dialog, "Erro ao atualizar a manutenção: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
});


        dialog.setVisible(true); // Mostrar o diálogo
    }
}
