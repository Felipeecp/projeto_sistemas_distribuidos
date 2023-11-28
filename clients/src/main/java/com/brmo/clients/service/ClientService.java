package com.brmo.clients.service;

import com.brmo.clients.domain.Sensor;
import com.brmo.clients.repository.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class ClientService {

    private final SensorRepository sensorRepository;

    public ClientService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void create(Sensor sensor){
        // Busca o maior valor de WMO entre os sensores existentes
        String maxWmo = sensorRepository.findAll()
                .stream()
                .map(Sensor::getWmo)
                .filter(wmo -> wmo.startsWith("REAL_"))
                .max(Comparator.naturalOrder())
                .orElse("REAL_000000");

        // Extrai a parte numérica do WMO e incrementa
        int numericPart = Integer.parseInt(maxWmo.substring(5));
        String nextId = String.format("REAL_%06d", numericPart + 1);

        // Define o novo WMO e salva o sensor
        sensor.setWmo(nextId);
        sensorRepository.save(sensor);
    }

    public List<Sensor> findSensorOrderByWmo(){
        return sensorRepository.findTop10ByOrderByWmoDesc();
    }

    public List<Sensor> findAll(){
        List<Sensor> allSensors = sensorRepository.findAll();
        return allSensors;
    }



    public void delete(String sensorId) {
        sensorRepository.deleteById(sensorId);
    }
}
