package br.com.marcelo;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercicio3 {

    // Método para soma
    public void soma(double num1, double num2) {
        System.out.printf("Resultado da soma: %.2f\n", num1 + num2);
    }

    // Método para subtração
    public void subtracao(double num1, double num2) {
        System.out.printf("Resultado da subtração: %.2f\n", num1 - num2);
    }

    // Método para multiplicação
    public void multiplicacao(double num1, double num2) {
        System.out.printf("Resultado da multiplicação: %.2f\n", num1 * num2);
    }

    // Método para divisão com tratamento de exceção
    public void divisao(double num1, double num2) {
        try {
            if (num2 == 0) {
                throw new ArithmeticException("Divisão por zero!");
            }
            System.out.printf("Resultado da divisão: %.2f\n", num1 / num2);
        } catch (ArithmeticException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Método para raiz quadrada com tratamento de exceção
    public void raizQuadrada(double num) {
        try {
            if (num < 0) {
                throw new ArithmeticException("Raiz quadrada de número negativo!");
            }
            System.out.printf("Resultado da raiz quadrada: %.2f\n", Math.sqrt(num));
        } catch (ArithmeticException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Método que exibe o menu e gerencia as operações
    public void iniciarCalculadora() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nEscolha uma operação:");
                System.out.println("1. Soma");
                System.out.println("2. Subtração");
                System.out.println("3. Multiplicação");
                System.out.println("4. Divisão");
                System.out.println("5. Raiz quadrada");
                System.out.println("6. Sair");

                int escolha = scanner.nextInt();

                if (escolha == 6) {
                    System.out.println("Saindo...");
                    break;
                }

                if (escolha >= 1 && escolha <= 4) {
                    System.out.print("Digite o primeiro número: ");
                    double num1 = scanner.nextDouble();
                    System.out.print("Digite o segundo número: ");
                    double num2 = scanner.nextDouble();

                    switch (escolha) {
                        case 1:
                            soma(num1, num2);
                            break;
                        case 2:
                            subtracao(num1, num2);
                            break;
                        case 3:
                            multiplicacao(num1, num2);
                            break;
                        case 4:
                            divisao(num1, num2);
                            break;
                    }
                } else if (escolha == 5) {
                    System.out.print("Digite o número: ");
                    double num = scanner.nextDouble();
                    raizQuadrada(num);
                } else {
                    System.out.println("Escolha inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número.");
                scanner.next();  // Limpa o buffer
            }
        }

        scanner.close();
    }
}
