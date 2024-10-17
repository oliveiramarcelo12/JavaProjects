package com.example.controllers;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.example.models.Manutencao;
import com.example.models.Maquina;
import com.example.models.Falha;
import com.example.models.Tecnico;

public class RelatorioCompletoController {

    private ManutencaoController manutencaoController;
    private MaquinaController maquinaController;
    private FalhaController falhaController;
    private TecnicoController tecnicoController;

    public RelatorioCompletoController() {
        this.manutencaoController = new ManutencaoController();
        this.maquinaController = new MaquinaController();
        this.falhaController = new FalhaController();
        this.tecnicoController = new TecnicoController();
    }

    public void gerarRelatorioCompleto() {
        try {
            // Caminho do arquivo de relatório
            String filePath = "relatorio_completo.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            // Cabeçalho do Relatório
            writer.write("Relatório Completo do Sistema\n");
            writer.write("================================\n\n");

            // Seção de Máquinas
            writer.write("Máquinas\n");
            writer.write("-------------------------------\n");
            List<Maquina> maquinas = maquinaController.readMaquinas();
            writer.write("ID\tCódigo\tNome\tFabricante\tModelo\tData de Aquisição\tLocalização\tDetalhes\n");
            writer.write("-----------------------------------------------------------------------------------\n");
            for (Maquina maquina : maquinas) {
                writer.write(
                    maquina.getId() + "\t" +
                    maquina.getCodigo() + "\t" +
                    maquina.getNome() + "\t" +
                    maquina.getFabricante() + "\t" +
                    maquina.getModelo() + "\t" +
                    maquina.getDataAquisicao().toString() + "\t" +
                    maquina.getLocalizacao() + "\t" +
                    maquina.getDetalhes() + "\n"
                );
            }
            writer.write("\nTotal de Máquinas: " + maquinas.size() + "\n\n");

            // Seção de Manutenções
            writer.write("Manutenções\n");
            writer.write("-------------------------------\n");
            List<Manutencao> manutencoes = manutencaoController.readManutencao();
            writer.write("ID\tData\tTipo\tPeças Trocadas\tTempo de Parada\tTécnico ID\tObservações\n");
            writer.write("-----------------------------------------------------------------------------------\n");
            int totalTempoParada = 0;
            for (Manutencao manutencao : manutencoes) {
                writer.write(
                    manutencao.getId() + "\t" +
                    manutencao.getData().toString() + "\t" +
                    manutencao.getTipo() + "\t" +
                    manutencao.getPecasTrocadas() + "\t" +
                    manutencao.getTempoDeParada() + " horas\t" +
                    manutencao.getTecnicoID() + "\t" +
                    manutencao.getObservacoes() + "\n"
                );
                totalTempoParada += manutencao.getTempoDeParada();
            }
            writer.write("\nTotal de Manutenções: " + manutencoes.size() + "\n");
            writer.write("Tempo Total de Parada: " + totalTempoParada + " horas\n\n");

            // Seção de Falhas
            writer.write("Falhas\n");
            writer.write("-------------------------------\n");
            List<Falha> falhas = falhaController.readFalhas();
            writer.write("ID\tID Máquina\tData\tProblema\tPrioridade\tOperador\n");
            writer.write("---------------------------------------------------------------\n");
            for (Falha falha : falhas) {
                writer.write(
                    falha.getId() + "\t" +
                    falha.getMaquinaID() + "\t" +
                    falha.getData().toString() + "\t" +
                    falha.getProblema() + "\t" +
                    falha.getPrioridade() + "\t" +
                    falha.getOperador() + "\n"
                );
            }
            writer.write("\nTotal de Falhas: " + falhas.size() + "\n\n");

            // Seção de Técnicos
            writer.write("Técnicos\n");
            writer.write("-------------------------------\n");
            List<Tecnico> tecnicos = tecnicoController.readTecnicos();
            writer.write("ID\tNome\tEspecialidade\tDisponibilidade\n");
            writer.write("----------------------------------------------\n");
            for (Tecnico tecnico : tecnicos) {
                writer.write(
                    tecnico.getId() + "\t" +
                    tecnico.getNome() + "\t" +
                    tecnico.getEspecialidade() + "\t" +
                    tecnico.getDisponibilidade() + "\n"
                );
            }
            writer.write("\nTotal de Técnicos: " + tecnicos.size() + "\n\n");

            // Fechar o writer
            writer.close();

            // Informar que o relatório foi gerado
            JOptionPane.showMessageDialog(null, "Relatório completo gerado com sucesso!\nArquivo salvo em: " + filePath);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório completo!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
