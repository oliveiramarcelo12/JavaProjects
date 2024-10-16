package com.example.view;

import javax.swing.*;
import java.awt.*;

public class SistemaManutencaoGUI extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel painelMaquinas;
    private JPanel painelManutencao;
    private JPanel painelFalhas;
    private JPanel painelTecnicos;

    public SistemaManutencaoGUI() {
        // Configurações iniciais do Frame
        super("Sistema de Manutenção");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Definir cor de fundo da janela como branca
        this.getContentPane().setBackground(Color.WHITE);

        // Estilizar abas
        estilizarAbas();

        // Inicialização dos painéis
        inicializarPainéis();

        // Criar meu TabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Máquinas", painelMaquinas);
        tabbedPane.addTab("Manutenções", painelManutencao);
        tabbedPane.addTab("Falhas", painelFalhas);
        tabbedPane.addTab("Técnicos", painelTecnicos);

        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private void estilizarAbas() {
        // Definindo as cores das abas
        UIManager.put("TabbedPane.selectedBackground", new Color(0, 102, 204)); // Azul para a aba selecionada
        UIManager.put("TabbedPane.selectedForeground", Color.BLUE); // Cor do texto da aba selecionada
        UIManager.put("TabbedPane.background", Color.WHITE); // Cor de fundo das abas não selecionadas
        UIManager.put("TabbedPane.foreground", new Color(30, 30, 30)); // Letras bem escuras (cinza escuro)
        UIManager.put("TabbedPane.font", new Font("Arial", Font.BOLD, 14)); // Fonte das abas
    }

    private void inicializarPainéis() {
        painelMaquinas = new MaquinasPanel();
        painelManutencao = new ManutencaoPanel();
        painelFalhas = new FalhasPanel();
        painelTecnicos = new TecnicosPanel();
    }


}
