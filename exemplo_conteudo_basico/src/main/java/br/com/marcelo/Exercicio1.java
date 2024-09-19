package br.com.marcelo;

import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] notas = new double[4];  // Array para armazenar as notas das 4 disciplinas
        double soma = 0.0;
        boolean elegivelBonus = true;  // Variável para verificar se o aluno está elegível ao bônus

        // Loop para capturar as notas das 4 disciplinas
        for (int i = 0; i < 4; i++) {
            System.out.print("Digite a nota da disciplina " + (i + 1) + ": ");
            notas[i] = scanner.nextDouble();

            // Verifica se a nota é menor ou igual a 9 para determinar se o aluno está elegível ao bônus
            if (notas[i] <= 9) {
                elegivelBonus = false;
            }

            soma += notas[i];
        }

        // Calcula a média
        double media = soma / 4;

        // Aplica o bônus se o aluno for elegível (nota maior que 9 em todas as disciplinas)
        if (elegivelBonus) {
            media *= 1.10;  // Aplica 10% de bônus
        }

        // Exibe a média final
        System.out.printf("Média final: %.2f\n", media);

        // Exibe o status do aluno com base na média
        if (media >= 7) {
            System.out.println("Status: Aprovado");
        } else if (media >= 5 && media < 7) {
            System.out.println("Status: Recuperação");
        } else {
            System.out.println("Status: Reprovado");
        }

        scanner.close();  // Fechar o scanner
    }
}
