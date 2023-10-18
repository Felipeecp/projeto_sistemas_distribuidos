package com.brmo.clients.domain;

import com.brmo.clients.domain.enumerated.Region;
import com.brmo.clients.domain.enumerated.UF;
import com.brmo.clients.dto.SensorRecord;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Sensor {
    private String codWMO;
    private Region region;
    private UF uf;
    private String latitude;
    private String longitude;
    private double altitude;

    public static Sensor of(SensorRecord record){
        return new ObjectMapper().convertValue(record, Sensor.class);
    }

}
