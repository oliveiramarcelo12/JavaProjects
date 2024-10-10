package com.example;

import javax.swing.SwingUtilities;

import com.example.view.SistemaManutecaoGUI;



public class Main {
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
        new SistemaManutecaoGUI().setVisible(true);
       });
    }
}