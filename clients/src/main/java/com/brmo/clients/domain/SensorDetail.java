package com.brmo.clients.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Document("sensorDetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorDetail{

    @Id
    private String id;
    private String codWMO;
    private Instant registrationDate;
    private Data data;

}
