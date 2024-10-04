package com.example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MinhaJanela extends JFrame {
    public MinhaJanela() {
        super("Minha Janela");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 300);

        JPanel painel = new JPanel();
        this.add(painel);

        // adicionando um botão
        JButton botao = new JButton("Clique Aqui");
        painel.add(botao);

        // //adicionar um evento no botão
        // botao.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // System.out.println("Botão clicado!");
        // }
        // });

        botao.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    null,
                    "Botão Clicado"

            );
        });

        this.setVisible(true);

    }

}
