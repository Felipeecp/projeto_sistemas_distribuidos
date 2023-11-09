package com.brmo.clients.service;

import com.brmo.clients.domain.Sensor;
import com.brmo.clients.dto.SensorRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientService {

    public void create(Sensor sensor){
        log.info("Data received");
    }

}
