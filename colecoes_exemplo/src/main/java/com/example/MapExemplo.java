package com.example;

import java.util.HashMap;
import java.util.Map;

public class MapExemplo {
   private Map<String, Integer> nomeIdade; 

   public MapExemplo() {
    nomeIdade = new HashMap<>();
   }
   public void adicionarNomeIdade(String nome, int idade) {
    nomeIdade.put(nome,idade);
   }

   public void listarNomesIdades() {
    for (String nome : nomeIdade.keySet()){
        int value = nomeIdade.get(nome);
        System.out.println(nome+ " "+ value);
        
    }
   }
   
    //remove
    public void deletarNomeIdade(String key){
       nomeIdade.remove(key); 
    }

    //update
    public void update(String key, int value){
       nomeIdade.replace(key, value);
    }
 
}
