package com.brmo.clients.domain;

import com.brmo.clients.domain.enumerated.Region;
import com.brmo.clients.domain.enumerated.UF;
import com.brmo.clients.dto.SensorRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDate;

@Document("sensor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {
    @Id
    private String codWMO;
    private String cidade;
    private Region regiao;
    private String dataCriacao;
    private UF uf;
    private String latitude;
    private String longitude;
    private double altitude;

    public static Sensor of(SensorRecord record){
        return new ObjectMapper().convertValue(record, Sensor.class);
    }
}
