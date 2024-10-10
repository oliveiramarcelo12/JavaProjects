package com.example.Models;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tecnico {
    private String id;
    private String nome;
    private String especialidade;
    private String disponibilidade;

}
