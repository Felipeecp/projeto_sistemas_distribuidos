package com.brmo.sensorsave.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("sensorData")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SensorDetail{

    @Id
    private String id;

    private String codWMO;
    private Instant registrationDate;
    private Data data;
}