package com.brmo.clients.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DadosSemana {
    private String nome;
    private double temperatura;
    private double umidade;
}
