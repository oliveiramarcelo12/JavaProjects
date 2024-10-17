package com.example.controllers;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.example.models.Manutencao;

public class RelatorioTecnicoController {

    private ManutencaoController manutencaoController;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RelatorioTecnicoController() {
        this.manutencaoController = new ManutencaoController();
    }

    public void gerarRelatorioManutencao() {
        String filePath = "relatorio_manutencoes.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            // Cabeçalho do relatório
            writer.write("Relatório de Manutenções\n");
            writer.write("=========================\n\n");

            // Cabeçalho da tabela
            writer.write("ID\tData\t\tTipo\tPeças Trocadas\tTempo de Parada\tTécnico ID\tObservações\n");
            writer.write("-----------------------------------------------------------------------------------\n");

            List<Manutencao> manutencoes = manutencaoController.readManutencao();
            int totalTempoParada = 0; // Para cálculo do total de tempo de parada

            // Escrever os dados de cada manutenção
            for (Manutencao manutencao : manutencoes) {
                writer.write(
                    manutencao.getId() + "\t" +
                    manutencao.getData().format(DATE_FORMATTER) + "\t" +
                    manutencao.getTipo() + "\t" +
                    manutencao.getPecasTrocadas() + "\t" +
                    manutencao.getTempoDeParada() + " horas\t" +
                    manutencao.getTecnicoID() + "\t" +
                    manutencao.getObservacoes() + "\n"
                );

                // Acumular tempo de parada
                totalTempoParada += manutencao.getTempoDeParada();
            }

            // Estatísticas adicionais
            writer.write("\n\nEstatísticas:\n");
            writer.write("-------------\n");
            writer.write("Total de Manutenções: " + manutencoes.size() + "\n");
            writer.write("Tempo Total de Parada: " + totalTempoParada + " horas\n");

            // Informar que o relatório foi gerado
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!\nArquivo salvo em: " + filePath);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
