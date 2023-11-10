package com.brmo.clients.service;

import com.brmo.clients.domain.Sensor;
import com.brmo.clients.repository.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    private final SensorRepository sensorRepository;

    public ClientService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void create(Sensor sensor){
        List<Sensor> sensorList = sensorRepository.findAllByOrderByWmoDesc();

        String nextId = sensorList.stream().findFirst().map(item ->{
            String idAtual = item.getWmo();

            int numericPart = Integer.parseInt(idAtual.substring(1));
            return "A" + String.format("%03d",numericPart+1);
        }).orElse("A001");

        sensor.setWmo(nextId);

        sensorRepository.save(sensor);
    }

    public List<Sensor> findAll(){
        List<Sensor> allSensors = sensorRepository.findAll();
        return allSensors;
    }

}
