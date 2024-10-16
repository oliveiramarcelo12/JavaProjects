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

    private void inicializarPainéis() {
        painelMaquinas = new MaquinasPanel();
        painelManutencao = new ManutencaoPanel();
        painelFalhas = new FalhasPanel();
        painelTecnicos = new TecnicosPanel();
    }
}
