package com.example.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.example.controllers.TecnicoController;
import com.example.models.Tecnico;

public class TecnicosPanel extends JPanel {
    // ATRIBUTOS
    private TecnicoController tecnicoController;
    private JTable tecnicosTable;
    private DefaultTableModel tableModel;
    private JButton btnCadastrarTecnico;
    private JButton btnDeletarTecnico;

    // Construtor
    public TecnicosPanel() {
        super(new BorderLayout());
        tecnicoController = new TecnicoController();

        // Definir as colunas da tabela
        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Nome", "Especialidade", "Disponibilidade"
        }, 0);
        tecnicosTable = new JTable(tableModel) {
            // Desabilitar edição de células
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Não permite edição em nenhuma célula
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

        // Criar os ActionListeners para os botões
        btnCadastrarTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioCadastro(null); // Passa null para criar novo técnico
            }
        });

        btnDeletarTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tecnicosTable.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Você realmente deseja deletar este técnico?",
                            "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Deletar o técnico
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

        // Adicionar um MouseListener para detectar duplo clique na tabela
        tecnicosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verifica se foi um duplo clique
                    int selectedRow = tecnicosTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Tecnico tecnicoSelecionado = new Tecnico();
                        tecnicoSelecionado.setId((String) tableModel.getValueAt(selectedRow, 0));
                        tecnicoSelecionado.setNome((String) tableModel.getValueAt(selectedRow, 1));
                        tecnicoSelecionado.setEspecialidade((String) tableModel.getValueAt(selectedRow, 2));
                        tecnicoSelecionado.setDisponibilidade((String) tableModel.getValueAt(selectedRow, 3));
                        mostrarFormularioCadastro(tecnicoSelecionado); // Passa o técnico selecionado para o formulário
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
        List<Tecnico> tecnicos = tecnicoController.readTecnicos();
        for (Tecnico tecnico : tecnicos) {
            tableModel.addRow(new Object[]{
                    tecnico.getId(),
                    tecnico.getNome(),
                    tecnico.getEspecialidade(),
                    tecnico.getDisponibilidade() // Atualizado para usar a disponibilidade
            });
        }
    }

    private void mostrarFormularioCadastro(Tecnico tecnico) {
        // Criar um JDialog para o formulário de cadastro
        JDialog dialog = new JDialog();
        dialog.setTitle(tecnico == null ? "Cadastrar Técnico" : "Atualizar Técnico");
        dialog.setModal(true);
        dialog.setSize(300, 300);
        dialog.setLayout(new GridLayout(5, 2));

        // Campos do formulário
        JTextField txtId = new JTextField(tecnico != null ? tecnico.getId() : ""); // Carrega dados
        JTextField txtNome = new JTextField(tecnico != null ? tecnico.getNome() : ""); // Carrega dados
        JTextField txtEspecialidade = new JTextField(tecnico != null ? tecnico.getEspecialidade() : ""); // Carrega dados
        JTextField txtDisponibilidade = new JTextField(tecnico != null ? tecnico.getDisponibilidade() : ""); // Carrega dados

        dialog.add(new JLabel("ID:"));
        dialog.add(txtId);
        dialog.add(new JLabel("Nome:"));
        dialog.add(txtNome);
        dialog.add(new JLabel("Especialidade:"));
        dialog.add(txtEspecialidade);
        dialog.add(new JLabel("Disponibilidade:"));
        dialog.add(txtDisponibilidade);

        // Botão para salvar
        JButton btnSalvar = new JButton("Salvar");
        dialog.add(btnSalvar);

        // Estilizar o botão
        estilizarBotao(btnSalvar);

        // Ação do botão de salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tecnico novoTecnico = tecnico != null ? tecnico : new Tecnico(); // Usa o técnico existente para atualização
                novoTecnico.setId(txtId.getText());
                novoTecnico.setNome(txtNome.getText());
                novoTecnico.setEspecialidade(txtEspecialidade.getText());
                novoTecnico.setDisponibilidade(txtDisponibilidade.getText());

                if (tecnico == null) {
                    tecnicoController.createTecnico(novoTecnico); // Chamada para o método que adiciona o técnico
                    tableModel.addRow(new Object[]{
                            novoTecnico.getId(),
                            novoTecnico.getNome(),
                            novoTecnico.getEspecialidade(),
                            novoTecnico.getDisponibilidade() // Adicionar o novo técnico à tabela
                    });
                    JOptionPane.showMessageDialog(dialog, "Técnico cadastrado com sucesso!");
                } else {
                    tecnicoController.updateTecnico(novoTecnico); // Chamada para o método que atualiza o técnico
                    int selectedRow = tecnicosTable.getSelectedRow();
                    tableModel.setValueAt(novoTecnico.getNome(), selectedRow, 1);
                    tableModel.setValueAt(novoTecnico.getEspecialidade(), selectedRow, 2);
                    tableModel.setValueAt(novoTecnico.getDisponibilidade(), selectedRow, 3);
                    JOptionPane.showMessageDialog(dialog, "Técnico atualizado com sucesso!");
                }
                dialog.dispose(); // Fecha o diálogo
                atualizarTabela(); // Atualiza a tabela após a operação
            }
        });

        dialog.setVisible(true); // Mostrar o diálogo
    }
}
