package com.example;

public class ContatoController {
//Atributos
private Contato[] contatos; 
private int contadorDeContatos;

//construtor
// Construtor
public ContatoController (int maxContato){
    contatos = new Contato[maxContato]; // Usa o valor passado para definir o tamanho do array
    contadorDeContatos = 0;
}

// métodos - adicionar contato

public void addContato(Contato contato) throws AgendaCheiaException {
    if (contadorDeContatos >= contatos.length) {
        throw new AgendaCheiaException("Agenda Cheia");
    }
    try {
        contatos[contadorDeContatos] = contato;
        contadorDeContatos++; // Incrementa o contador após adicionar um contato
    } catch (Exception e) {
        System.out.println(e);
    }
}


    // Métodos - Listar Todos
    public void listarContato() {
        if (contadorDeContatos == 0) {
            System.out.println("Agenda Vazia");
        } else {
            for (int i = 0; i < contadorDeContatos; i++) {
                if (contatos[i] != null) { // Verifica se o contato não é null
                    System.out.println(contatos[i].toString());
                }
            }
        }
    }

// métodos - Buscar Contato pelo nome
public Contato buscarContato(String nome) throws ContatoNaoEncontrado {
    for (int i = 0; i < contadorDeContatos; i++) {
       if (contatos[i].getNome().equalsIgnoreCase(nome)) {
        return contatos[i];
       }
    }
    throw new ContatoNaoEncontrado("Contato não Encontrado");
    
}

//remover contato
public void removerContato(String nome) throws ContatoNaoEncontrado{
    boolean encontrado = false;
    for (int i = 0; i < contatos.length; i++) {
        if (contatos[i].getNome().equalsIgnoreCase(nome)) {
            encontrado= true;
            contatos[i]=contatos[contadorDeContatos-1];
            contatos[contadorDeContatos-1]=null;
        }
    }
    if (!encontrado) {
        throw new ContatoNaoEncontrado("Contato Não Encontrado");
        
    }
}
}
