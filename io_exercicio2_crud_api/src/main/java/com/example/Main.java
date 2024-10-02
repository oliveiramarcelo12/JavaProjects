package com.example;

public class Main {
    public static void main(String[] args) {
        UsuarioController uc = new UsuarioController();
        
        // Criando dois novos usuários
        // uc.createUser(new Usuario("", "Maria Das Dores", 30, "Rua 4"));
        // uc.createUser(new Usuario("", "maria@example.c", 25, "Rua 5"));
        uc.read();

         uc.deleteUser("e1c6");

        
       
    }
}
