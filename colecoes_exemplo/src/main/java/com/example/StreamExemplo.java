package com.example;

import java.util.Arrays;

import java.util.List;

import java.util.stream.Collectors;

public class StreamExemplo {
    List <String> words = Arrays.asList("banana","abacaxi","amora","laranja","uva");
// Crie uma nova list Resultado,
//filtre as words que comecem com a letra 'a',
// to uppercase
// crie um método
public void resultadoStream(){
    List<String> resultado = words.stream()
    
    .map(String::toUpperCase)
    .filter(word -> word.startsWith("A"))
    .collect(Collectors.toList());
    //resultado
    System.out.println(resultado);
    

}

}
