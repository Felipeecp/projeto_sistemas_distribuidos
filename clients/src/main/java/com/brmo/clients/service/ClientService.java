package com.brmo.clients.service;

import com.brmo.clients.domain.Sensor;
import com.brmo.clients.domain.SensorDetail;
import com.brmo.clients.dto.MapInfo;
import com.brmo.clients.repository.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClientService {

    private final SensorRepository sensorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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

    public void delete(String sensorId) {
        sensorRepository.deleteById(sensorId);
    }

    public List<MapInfo> findAverageMaps(){
        return sensorRepository.findTest();
    }
}
