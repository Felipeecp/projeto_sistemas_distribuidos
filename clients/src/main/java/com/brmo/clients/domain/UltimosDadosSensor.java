package com.brmo.clients.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UltimosDadosSensor {
    private String codWMO;
    private double ultimaTemperatura;
    private double ultimaUmidade;
    private List<DadosSemana> dadosSemana;
}
