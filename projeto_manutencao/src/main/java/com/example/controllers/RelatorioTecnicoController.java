package com.example.controllers;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.example.models.Tecnico;

public class RelatorioTecnicoController {
    private TecnicoController tecnicoController;

    public RelatorioTecnicoController() {
        this.tecnicoController = new TecnicoController();
    }

    public void gerarRelatorio() {
        List<Tecnico> tecnicos = tecnicoController.readTecnicos(); // Recupera a lista de técnicos

        // Exemplo simples de geração de relatório em arquivo TXT
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio_tecnicos.txt"))) {
            writer.write("Relatório de Técnicos\n");
            writer.write("=====================\n");

            for (Tecnico tecnico : tecnicos) {
                writer.write("ID: " + tecnico.getId() + "\n");
                writer.write("Nome: " + tecnico.getNome() + "\n");
                writer.write("Especialidade: " + tecnico.getEspecialidade() + "\n");
                writer.write("Disponibilidade: " + tecnico.getDisponibilidade() + "\n");
                writer.write("---------------------\n");
            }
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
