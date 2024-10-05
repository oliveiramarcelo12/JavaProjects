package com.example;

public class ManipuladorString {
     // Método que inverte a string
     public String invertString(String original) {
        if (original == null || original.isEmpty()) {
            return original;
        }
        return new StringBuilder(original).reverse().toString();
    }

    // Método que conta o número de vogais em uma string
    public int contarVogais(String texto) {
        if (texto == null || texto.isEmpty()) {
            return 0;
        }
        int count = 0;
        String vogais = "AEIOUaeiou";
        for (char c : texto.toCharArray()) {
            if (vogais.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    // Método que conta o número de palavras em uma string
    public int contarPalavras(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return 0;
        }
        return texto.trim().split("\\s+").length;
    }
}
