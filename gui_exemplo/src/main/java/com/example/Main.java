package com.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Aqui você pode inicializar sua interface gráfica
                MinhaJanela janela = new MinhaJanela();
                janela.setVisible(true);
            }
        });
    }
}
