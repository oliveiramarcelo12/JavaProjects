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
import com.example.controllers.MaquinaController;
import com.example.models.Maquina;

public class MaquinasPanel extends JPanel {
    // ATRIBUTOS
    private MaquinaController maquinaController;
    private JTable maquinasTable;
    private DefaultTableModel tableModel;
    private JButton btnCadastrarMaquina;

    // Construtor
    public MaquinasPanel() {
        super(new BorderLayout());
        maquinaController = new MaquinaController();

        // Definir as colunas da tabela
        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Código", "Nome", "Fabricante", "Modelo", "Detalhes", "Localização", "Tempo Vida"
        }, 0);
        
        // Criação da tabela com a modelagem definida
        maquinasTable = new JTable(tableModel) {
            // Desabilitar edição de células
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Não permite edição em nenhuma célula
            }
        };

        // Estilizar a tabela
        estilizarTabela();

        JScrollPane scrollPane = new JScrollPane(maquinasTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Preencher a tabela com dados das máquinas
        atualizarTabela();

        // Adicionar os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarMaquina = new JButton("Cadastrar");
        
        // Estilizar botões
        estilizarBotao(btnCadastrarMaquina);
        
        painelInferior.add(btnCadastrarMaquina);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Criar o ActionListener para o botão de cadastrar
        btnCadastrarMaquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioCadastro();
            }
        });

        // Adicionar um MouseListener para a tabela para detectar duplo clique
        maquinasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = maquinasTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Pega o ID da máquina selecionada
                        String id = (String) tableModel.getValueAt(selectedRow, 0);
                        // Busca a máquina pelo ID
                        Maquina maquina = maquinaController.readMaquina(id);
                        if (maquina != null) {
                            // Mostra o formulário de atualização
                            mostrarFormularioAtualizacao(maquina);
                        } else {
                            JOptionPane.showMessageDialog(null, "Máquina não encontrada!", "Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    // Método para estilizar a tabela
    private void estilizarTabela() {
        maquinasTable.setFillsViewportHeight(true);
        maquinasTable.setBackground(Color.WHITE);
        maquinasTable.setFont(new Font("Arial", Font.PLAIN, 12));
        maquinasTable.setRowHeight(25);
        maquinasTable.setSelectionBackground(Color.LIGHT_GRAY);
        maquinasTable.setSelectionForeground(Color.BLACK);
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
        List<Maquina> maquinas = maquinaController.readMaquinas();
        // Limpar a tabela antes de adicionar as máquinas
        tableModel.setRowCount(0);
        for (Maquina maquina : maquinas) {
            tableModel.addRow(new Object[]{
                    maquina.getId(),
                    maquina.getCodigo(),
                    maquina.getNome(),
                    maquina.getFabricante(),
                    maquina.getModelo(),
                    maquina.getDetalhes(),
                    maquina.getLocalizacao(),
                    maquina.getTempoVidaEstimado()
            });
        }
    }

    private void mostrarFormularioCadastro() {
        // Criar um JDialog para o formulário de cadastro
        JDialog dialog = new JDialog();
        dialog.setTitle("Cadastrar Máquina");
        dialog.setModal(true);
        dialog.setSize(400, 400); // Aumentar o tamanho para acomodar mais campos
        dialog.setLayout(new GridLayout(10, 2)); // Aumentar linhas para incluir mais campos

        // Campos do formulário
        JTextField txtCodigo = new JTextField();
        JTextField txtNome = new JTextField();
        JTextField txtFabricante = new JTextField();
        JTextField txtModelo = new JTextField();
        JTextField txtDetalhes = new JTextField();
        JTextField txtLocalizacao = new JTextField();
        JTextField txtTempoVida = new JTextField();
        JTextField txtDataAquisicao = new JTextField(); // Campo para data de aquisição
        JTextField txtManual = new JTextField(); // Campo para manual

        dialog.add(new JLabel("Código:"));
        dialog.add(txtCodigo);
        dialog.add(new JLabel("Nome:"));
        dialog.add(txtNome);
        dialog.add(new JLabel("Fabricante:"));
        dialog.add(txtFabricante);
        dialog.add(new JLabel("Modelo:"));
        dialog.add(txtModelo);
        dialog.add(new JLabel("Detalhes:"));
        dialog.add(txtDetalhes);
        dialog.add(new JLabel("Localização:"));
        dialog.add(txtLocalizacao);
        dialog.add(new JLabel("Tempo Vida (anos):"));
        dialog.add(txtTempoVida);
        dialog.add(new JLabel("Data de Aquisição (YYYY-MM-DD):")); // Instrução de formato
        dialog.add(txtDataAquisicao);
        dialog.add(new JLabel("Manual:"));
        dialog.add(txtManual);

        // Botão para salvar
        JButton btnSalvar = new JButton("Salvar");
        dialog.add(btnSalvar);

        // Ação do botão de salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Criar um novo objeto Maquina com os dados do formulário
                Maquina novaMaquina = new Maquina();
                novaMaquina.setCodigo(txtCodigo.getText());
                novaMaquina.setNome(txtNome.getText());
                novaMaquina.setFabricante(txtFabricante.getText());
                novaMaquina.setModelo(txtModelo.getText());
                novaMaquina.setDetalhes(txtDetalhes.getText());
                novaMaquina.setLocalizacao(txtLocalizacao.getText());

                // Converter o tempo de vida para número
                try {
                    int tempoVida = Integer.parseInt(txtTempoVida.getText());
                    novaMaquina.setTempoVidaEstimado(tempoVida);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "O tempo de vida deve ser um número!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Converter a data de aquisição
                try {
                    LocalDate dataAquisicao = LocalDate.parse(txtDataAquisicao.getText());
                    novaMaquina.setDataAquisicao(dataAquisicao);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Data de aquisição inválida! Use o formato YYYY-MM-DD.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Definir o manual se necessário
                novaMaquina.setManual(txtManual.getText());

                // Salvar a nova máquina usando o controlador
                maquinaController.createMaquina(novaMaquina); // Certifique-se de que este método exista no controller

                // Adicionar a nova máquina à tabela
                tableModel.addRow(new Object[]{
                        novaMaquina.getId(), // Supondo que o ID é gerado na API ou em outro lugar
                        novaMaquina.getCodigo(),
                        novaMaquina.getNome(),
                        novaMaquina.getFabricante(),
                        novaMaquina.getModelo(),
                        novaMaquina.getDetalhes(),
                        novaMaquina.getLocalizacao(),
                        novaMaquina.getTempoVidaEstimado()
                });

                // Fechar o diálogo
                dialog.dispose();
                JOptionPane.showMessageDialog(null, "Máquina cadastrada com sucesso!");
            }
        });

        dialog.setVisible(true); // Mostrar o diálogo
    }

    private void mostrarFormularioAtualizacao(Maquina maquina) {
        // Criar um JDialog para o formulário de atualização
        JDialog dialog = new JDialog();
        dialog.setTitle("Atualizar Máquina");
        dialog.setModal(true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(10, 2));

        // Campos do formulário pré-preenchidos
        JTextField txtCodigo = new JTextField(maquina.getCodigo());
        JTextField txtNome = new JTextField(maquina.getNome());
        JTextField txtFabricante = new JTextField(maquina.getFabricante());
        JTextField txtModelo = new JTextField(maquina.getModelo());
        JTextField txtDetalhes = new JTextField(maquina.getDetalhes());
        JTextField txtLocalizacao = new JTextField(maquina.getLocalizacao());
        JTextField txtTempoVida = new JTextField(String.valueOf(maquina.getTempoVidaEstimado()));
        JTextField txtDataAquisicao = new JTextField(maquina.getDataAquisicao().toString());
        JTextField txtManual = new JTextField(maquina.getManual());

        dialog.add(new JLabel("Código:"));
        dialog.add(txtCodigo);
        dialog.add(new JLabel("Nome:"));
        dialog.add(txtNome);
        dialog.add(new JLabel("Fabricante:"));
        dialog.add(txtFabricante);
        dialog.add(new JLabel("Modelo:"));
        dialog.add(txtModelo);
        dialog.add(new JLabel("Detalhes:"));
        dialog.add(txtDetalhes);
        dialog.add(new JLabel("Localização:"));
        dialog.add(txtLocalizacao);
        dialog.add(new JLabel("Tempo Vida (anos):"));
        dialog.add(txtTempoVida);
        dialog.add(new JLabel("Data de Aquisição (YYYY-MM-DD):"));
        dialog.add(txtDataAquisicao);
        dialog.add(new JLabel("Manual:"));
        dialog.add(txtManual);

        // Botão para salvar
        JButton btnSalvar = new JButton("Salvar");
        dialog.add(btnSalvar);

        // Ação do botão de salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirmação antes da atualização
                int confirm = JOptionPane.showConfirmDialog(dialog,
                        "Você tem certeza que deseja atualizar os dados da máquina?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Atualiza os dados da máquina com os dados do formulário
                    maquina.setCodigo(txtCodigo.getText());
                    maquina.setNome(txtNome.getText());
                    maquina.setFabricante(txtFabricante.getText());
                    maquina.setModelo(txtModelo.getText());
                    maquina.setDetalhes(txtDetalhes.getText());
                    maquina.setLocalizacao(txtLocalizacao.getText());

                    // Converter o tempo de vida
                    try {
                        int tempoVida = Integer.parseInt(txtTempoVida.getText());
                        maquina.setTempoVidaEstimado(tempoVida);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialog, "O tempo de vida deve ser um número!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Converter a data de aquisição
                    try {
                        LocalDate dataAquisicao = LocalDate.parse(txtDataAquisicao.getText());
                        maquina.setDataAquisicao(dataAquisicao);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dialog, "Data de aquisição inválida! Use o formato YYYY-MM-DD.",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Atualizar o manual
                    maquina.setManual(txtManual.getText());

                    // Atualizar a máquina usando o controlador
                    maquinaController.updateMaquina(maquina); // Certifique-se de que este método exista no controller

                    // Atualiza a linha correspondente na tabela
                    int selectedRow = maquinasTable.getSelectedRow();
                    tableModel.setValueAt(maquina.getCodigo(), selectedRow, 1);
                    tableModel.setValueAt(maquina.getNome(), selectedRow, 2);
                    tableModel.setValueAt(maquina.getFabricante(), selectedRow, 3);
                    tableModel.setValueAt(maquina.getModelo(), selectedRow, 4);
                    tableModel.setValueAt(maquina.getDetalhes(), selectedRow, 5);
                    tableModel.setValueAt(maquina.getLocalizacao(), selectedRow, 6);
                    tableModel.setValueAt(maquina.getTempoVidaEstimado(), selectedRow, 7);

                    // Fechar o diálogo
                    dialog.dispose();
                    JOptionPane.showMessageDialog(null, "Máquina atualizada com sucesso!");
                }
            }
        });

        dialog.setVisible(true); // Mostrar o diálogo
    }
}
