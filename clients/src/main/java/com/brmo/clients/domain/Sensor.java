package com.brmo.clients.domain;

import com.brmo.clients.domain.enumerated.Region;
import com.brmo.clients.domain.enumerated.UF;
import com.brmo.clients.dto.SensorRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document("sensor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sensor {

    @Id
    private String id;

    private String ibgeCod;
    private String nameState;
    private String nameCity;
    private Double longitude;
    private Double latitude;
    private Integer altitude;
    @Indexed
    private String wmo;
    private String uf;
    private Region region;

    public static Sensor of(SensorRecord record){
        return new ObjectMapper().convertValue(record, Sensor.class);
    }


}
