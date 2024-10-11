package com.example.view;

import javax.swing.*;

import com.example.controllers.MaquinaController;
import com.example.models.Maquina;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CadastrarMaquinaForm extends JFrame {
    private JTextField txtNome;
    private JTextField txtFabricante;
    private JTextField txtModelo;
    private JTextField txtDetalhes;
    private JTextField txtLocalizacao;
    private JTextField txtTempoVida;
    private JButton btnSalvar;

    public CadastrarMaquinaForm(MaquinaController maquinaController) {
        setTitle("Cadastrar Máquina");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        // Adicionando os campos de entrada
        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Fabricante:"));
        txtFabricante = new JTextField();
        add(txtFabricante);

        add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        add(txtModelo);

        add(new JLabel("Detalhes:"));
        txtDetalhes = new JTextField();
        add(txtDetalhes);

        add(new JLabel("Localização:"));
        txtLocalizacao = new JTextField();
        add(txtLocalizacao);

        add(new JLabel("Tempo de Vida (anos):"));
        txtTempoVida = new JTextField();
        add(txtTempoVida);

        // Botão de salvar
        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        // Adicionando ação ao botão
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Coletar os dados do formulário
                String nome = txtNome.getText();
                String fabricante = txtFabricante.getText();
                String modelo = txtModelo.getText();
                String detalhes = txtDetalhes.getText();
                String localizacao = txtLocalizacao.getText();
                String tempoVidaStr = txtTempoVida.getText();

                // Verificar se todos os campos foram preenchidos
                if (nome.isEmpty() || fabricante.isEmpty() || modelo.isEmpty() || detalhes.isEmpty() ||
                        localizacao.isEmpty() || tempoVidaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(CadastrarMaquinaForm.this,
                            "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar se o tempo de vida é um número válido
                int tempoVida;
                try {
                    tempoVida = Integer.parseInt(tempoVidaStr);
                    if (tempoVida <= 0) {
                        JOptionPane.showMessageDialog(CadastrarMaquinaForm.this,
                                "O tempo de vida deve ser um número positivo!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CadastrarMaquinaForm.this,
                            "O tempo de vida deve ser um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Criar a nova máquina utilizando o builder do Lombok
                Maquina novaMaquina = Maquina.builder()
                        .nome(nome)
                        .fabricante(fabricante)
                        .modelo(modelo)
                        .detalhes(detalhes)
                        .localizacao(localizacao)
                        .tempoVidaEstimado(tempoVida)
                        .dataAquisicao(LocalDate.now()) // Ajustar conforme necessidade
                        .manual("manual.pdf") // Ajuste conforme necessidade
                        .build();

                // Adicionar a nova máquina ao controlador
                maquinaController.createMaquina(novaMaquina);

                // Fechar o formulário
                dispose();

                // Mensagem de sucesso
                JOptionPane.showMessageDialog(CadastrarMaquinaForm.this, "Máquina cadastrada com sucesso!");
            }
        });

        // Exibir o formulário
        setVisible(true);
    }
}
