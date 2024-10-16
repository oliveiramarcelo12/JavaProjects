package com.example.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Falha {
     private String id;
    private long maquinaID;
    private LocalDate data;
    private String problema;
    private String prioridade;
    private String operador;

}
