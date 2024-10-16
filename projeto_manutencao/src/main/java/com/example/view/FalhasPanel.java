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
import com.example.controllers.FalhaController;
import com.example.models.Falha;

public class FalhasPanel extends JPanel {
    // ATRIBUTOS
    private FalhaController falhaController;
    private JTable falhasTable;
    private DefaultTableModel tableModel;
    private JButton btnCadastrarFalha; // Botão para cadastrar falha

    // Construtor
    public FalhasPanel() {
        super(new BorderLayout());
        falhaController = new FalhaController();

        // Definir as colunas da tabela
        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Máquina ID", "Data", "Problema", "Prioridade", "Operador"
        }, 0);
        falhasTable = new JTable(tableModel) {
            // Desabilitar edição de células
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Não permite edição em nenhuma célula
            }
        };

        // Estilizar a tabela
        falhasTable.setFillsViewportHeight(true);
        falhasTable.setBackground(Color.WHITE);
        falhasTable.setFont(new Font("Arial", Font.PLAIN, 12));
        falhasTable.setRowHeight(25);
        falhasTable.setSelectionBackground(Color.LIGHT_GRAY);
        falhasTable.setSelectionForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(falhasTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Preencher a tabela com dados das falhas
        atualizarTabela();

        // Adicionar o botão de cadastrar
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarFalha = new JButton("Cadastrar");
        
        // Estilizar o botão
        estilizarBotao(btnCadastrarFalha);
        
        painelInferior.add(btnCadastrarFalha);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Criar o ActionListener para o botão de cadastrar
        btnCadastrarFalha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioCadastro(null); // Passa null para criar nova falha
            }
        });

        // Adicionar um MouseListener para detectar duplo clique na tabela
        falhasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verifica se foi um duplo clique
                    int selectedRow = falhasTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String id = tableModel.getValueAt(selectedRow, 0).toString(); // Pega o ID da falha selecionada
                        Falha falhaSelecionada = falhaController.readFalha(id);
                        mostrarFormularioCadastro(falhaSelecionada); // Passa a falha selecionada para o formulário
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
        tableModel.setRowCount(0); // Limpa a tabela antes de atualizar
        List<Falha> falhas = falhaController.readFalhas();
        for (Falha falha : falhas) {
            tableModel.addRow(new Object[]{
                    falha.getId(),
                    falha.getMaquinaID(),
                    falha.getData(),
                    falha.getProblema(),
                    falha.getPrioridade(),
                    falha.getOperador()
            });
        }
    }

    private void mostrarFormularioCadastro(Falha falha) {
        // Criar um JDialog para o formulário de cadastro ou atualização
        JDialog dialog = new JDialog();
        dialog.setTitle(falha == null ? "Cadastrar Falha" : "Atualizar Falha");
        dialog.setModal(true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(7, 2)); // Grid para os campos

        // Campos do formulário
        JTextField txtMaquinaId = new JTextField(falha != null ? String.valueOf(falha.getMaquinaID()) : ""); // Carrega dados
        JTextField txtData = new JTextField(falha != null ? falha.getData().toString() : ""); // Carrega dados
        JTextField txtProblema = new JTextField(falha != null ? falha.getProblema() : ""); // Carrega dados
        JTextField txtPrioridade = new JTextField(falha != null ? falha.getPrioridade() : ""); // Carrega dados
        JTextField txtOperador = new JTextField(falha != null ? falha.getOperador() : ""); // Carrega dados

        dialog.add(new JLabel("Máquina ID:"));
        dialog.add(txtMaquinaId);
        dialog.add(new JLabel("Data (yyyy-MM-dd):"));
        dialog.add(txtData); // Campo para a data
        dialog.add(new JLabel("Problema:"));
        dialog.add(txtProblema);
        dialog.add(new JLabel("Prioridade:"));
        dialog.add(txtPrioridade);
        dialog.add(new JLabel("Operador:"));
        dialog.add(txtOperador);

        // Botão para salvar
        JButton btnSalvar = new JButton("Salvar");
        dialog.add(btnSalvar);
        
        // Estilizar o botão
        estilizarBotao(btnSalvar);

        // Ação do botão de salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Criar ou atualizar a falha com os dados do formulário
                Falha falhaSalvar = falha != null ? falha : new Falha();
                try {
                    falhaSalvar.setMaquinaID(Integer.parseInt(txtMaquinaId.getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Máquina ID deve ser um número!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                falhaSalvar.setProblema(txtProblema.getText());
                falhaSalvar.setPrioridade(txtPrioridade.getText());
                falhaSalvar.setOperador(txtOperador.getText());

                // Definir a data da falha
                try {
                    LocalDate data = LocalDate.parse(txtData.getText()); // Conversão da data
                    falhaSalvar.setData(data);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Formato de data inválido! Use yyyy-MM-dd.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Salvar a falha usando o controlador
                if (falha == null) {
                    falhaController.createFalha(falhaSalvar); // Criar nova falha
                    // Adicionar a nova falha à tabela
                    tableModel.addRow(new Object[]{
                            falhaSalvar.getId(), // Supondo que o ID é gerado na API ou em outro lugar
                            falhaSalvar.getMaquinaID(),
                            falhaSalvar.getData(),
                            falhaSalvar.getProblema(),
                            falhaSalvar.getPrioridade(),
                            falhaSalvar.getOperador()
                    });
                    JOptionPane.showMessageDialog(FalhasPanel.this, "Falha cadastrada com sucesso!");
                } else {
                    falhaController.updateFalha(falhaSalvar); // Atualizar falha existente
                    // Atualiza a linha da tabela correspondente
                    int selectedRow = falhasTable.getSelectedRow();
                    tableModel.setValueAt(falhaSalvar.getMaquinaID(), selectedRow, 1);
                    tableModel.setValueAt(falhaSalvar.getData(), selectedRow, 2);
                    tableModel.setValueAt(falhaSalvar.getProblema(), selectedRow, 3);
                    tableModel.setValueAt(falhaSalvar.getPrioridade(), selectedRow, 4);
                    tableModel.setValueAt(falhaSalvar.getOperador(), selectedRow, 5);
                    JOptionPane.showMessageDialog(FalhasPanel.this, "Falha atualizada com sucesso!");
                }

                dialog.dispose(); // Fecha o diálogo
                atualizarTabela(); // Atualiza a tabela após a operação
            }
        });

        dialog.setVisible(true); // Mostrar o diálogo
    }
}
