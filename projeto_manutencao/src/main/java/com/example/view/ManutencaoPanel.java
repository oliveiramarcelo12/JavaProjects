package com.example.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    // Construtor
    public ManutencaoPanel() {
        super(new BorderLayout());
        manutencaoController = new ManutencaoController();

        // Definir as colunas da tabela
        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Data", "Tipo", "Peças Trocadas", "Tempo de Parada", "Técnico ID", "Observações"
        }, 0);
        manutencaoTable = new JTable(tableModel) {
            // Desabilitar edição de células
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Não permite edição em nenhuma célula
            }
        };

        // Estilizar a tabela
        manutencaoTable.setFillsViewportHeight(true);
        manutencaoTable.setBackground(Color.WHITE);
        manutencaoTable.setFont(new Font("Arial", Font.PLAIN, 12));
        manutencaoTable.setRowHeight(25);
        manutencaoTable.setSelectionBackground(Color.LIGHT_GRAY);
        manutencaoTable.setSelectionForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(manutencaoTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Preencher a tabela com dados das manutenções
        atualizarTabela();

        // Adicionar o botão de cadastrar
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarManutencao = new JButton("Cadastrar");

        // Estilizar o botão
        estilizarBotao(btnCadastrarManutencao);

        painelInferior.add(btnCadastrarManutencao);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Criar o ActionListener para o botão de cadastrar
        btnCadastrarManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioCadastro();
            }
        });

        // Adicionar um MouseListener para a tabela para detectar duplo clique
        manutencaoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = manutencaoTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Pegar o ID da manutenção selecionada
                        String id = (String) tableModel.getValueAt(selectedRow, 0);
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

    private void estilizarBotao(JButton botao) {
        botao.setBackground(new Color(70, 130, 180)); // Cor de fundo
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte
        botao.setFocusPainted(false); // Remove o contorno ao clicar
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Bordas com espaçamento
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mão
    }

    private void atualizarTabela() {
        List<Manutencao> manutencoes = manutencaoController.readManutencao();
        // Limpar a tabela antes de adicionar as manutenções
        tableModel.setRowCount(0);
        for (Manutencao manutencao : manutencoes) {
            tableModel.addRow(new Object[]{
                    manutencao.getId(),
                    manutencao.getData(),
                    manutencao.getTipo(),
                    manutencao.getPecasTrocadas(),
                    manutencao.getTempoDeParada(),
                    manutencao.getTecnicoID(),
                    manutencao.getObservacoes()
            });
        }
    }

    private void mostrarFormularioCadastro() {
        // Criar um JDialog para o formulário de cadastro
        JDialog dialog = new JDialog();
        dialog.setTitle("Cadastrar Manutenção");
        dialog.setModal(true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(8, 2)); // Aumentar linhas para incluir mais campos

        // Campos do formulário
        JTextField txtData = new JTextField(); // Novo campo para a data
        JTextField txtTipo = new JTextField();
        JTextField txtPecasTrocadas = new JTextField();
        JTextField txtTempoParada = new JTextField();
        JTextField txtTecnicoID = new JTextField();
        JTextField txtObservacoes = new JTextField();

        dialog.add(new JLabel("Data (yyyy-MM-dd):"));
        dialog.add(txtData); // Campo para a data
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
        
        // Estilizar o botão
        estilizarBotao(btnSalvar);

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

                // Definir a data da manutenção
                try {
                    LocalDate data = LocalDate.parse(txtData.getText()); // Conversão da data
                    novaManutencao.setData(data);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Formato de data inválido! Use yyyy-MM-dd.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Definir o técnico ID e observações
                novaManutencao.setTecnicoID(txtTecnicoID.getText());
                novaManutencao.setObservacoes(txtObservacoes.getText());

                // Salvar a nova manutenção usando o controlador
                manutencaoController.createManutencao(novaManutencao);

                // Adicionar a nova manutenção à tabela
                tableModel.addRow(new Object[]{
                        novaManutencao.getId(), // Supondo que o ID é gerado na API ou em outro lugar
                        novaManutencao.getData(),
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
    
        // Campos do formulário pré-preenchidos
        JTextField txtData = new JTextField(manutencao.getData().toString());
        JTextField txtTipo = new JTextField(manutencao.getTipo());
        JTextField txtPecasTrocadas = new JTextField(manutencao.getPecasTrocadas());
        JTextField txtTempoParada = new JTextField(String.valueOf(manutencao.getTempoDeParada()));
        JTextField txtTecnicoID = new JTextField(manutencao.getTecnicoID());
        JTextField txtObservacoes = new JTextField(manutencao.getObservacoes());
    
        dialog.add(new JLabel("Data (yyyy-MM-dd):"));
        dialog.add(txtData);
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
    
        // Botão para atualizar
        JButton btnAtualizar = new JButton("Atualizar");
        dialog.add(btnAtualizar);
        
        // Estilizar o botão
        estilizarBotao(btnAtualizar);
    
        // Ação do botão de atualizar
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Atualizar os dados da manutenção
                manutencao.setTipo(txtTipo.getText());
                manutencao.setPecasTrocadas(txtPecasTrocadas.getText());
    
                // Converter tempo de parada para número
                try {
                    int tempoParada = Integer.parseInt(txtTempoParada.getText());
                    manutencao.setTempoDeParada(tempoParada);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "O tempo de parada deve ser um número!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                // Definir a data da manutenção
                try {
                    LocalDate data = LocalDate.parse(txtData.getText());
                    manutencao.setData(data);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Formato de data inválido! Use yyyy-MM-dd.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                // Definir o técnico ID e observações
                manutencao.setTecnicoID(txtTecnicoID.getText());
                manutencao.setObservacoes(txtObservacoes.getText());
    
                // Atualizar a manutenção usando o controlador
                manutencaoController.updateManutencao(manutencao.getId(), manutencao); // Atualize aqui com o ID correto
    
                // Atualizar a tabela
                int selectedRow = manutencaoTable.getSelectedRow();
                tableModel.setValueAt(manutencao.getData(), selectedRow, 1);
                tableModel.setValueAt(manutencao.getTipo(), selectedRow, 2);
                tableModel.setValueAt(manutencao.getPecasTrocadas(), selectedRow, 3);
                tableModel.setValueAt(manutencao.getTempoDeParada(), selectedRow, 4);
                tableModel.setValueAt(manutencao.getTecnicoID(), selectedRow, 5);
                tableModel.setValueAt(manutencao.getObservacoes(), selectedRow, 6);
    
                dialog.dispose(); // Fecha o diálogo
                JOptionPane.showMessageDialog(ManutencaoPanel.this, "Manutenção atualizada com sucesso!");
            }
        });
    
        dialog.setVisible(true); // Mostrar o diálogo
    }
    
}
