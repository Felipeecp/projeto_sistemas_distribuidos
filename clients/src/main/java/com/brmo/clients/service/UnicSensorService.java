package com.brmo.clients.service;

import com.brmo.clients.domain.Sensor;
import com.brmo.clients.domain.SensorDetail;
import com.brmo.clients.domain.SensorInfo;
import com.brmo.clients.domain.UnicSensorData;
import com.brmo.clients.repository.SensorDetailRepository;
import com.brmo.clients.repository.SensorRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnicSensorService {

    private final SensorDetailRepository sensorDetailRepository;
    private final SensorRepository sensorRepository;


    public UnicSensorService(SensorDetailRepository sensorDetailRepository, SensorRepository sensorRepository) {
        this.sensorDetailRepository = sensorDetailRepository;
        this.sensorRepository = sensorRepository;
    }


    public UnicSensorData getSensorInfoAndLastData(String sensorId){
        Sensor sensor = sensorRepository.findById(sensorId).orElse(null);

        if(sensor!=null){
            List<SensorDetail> lastsSensorData = sensorDetailRepository.findOnePerDay(sensor.getWmo());

            UnicSensorData unicSensorData = new UnicSensorData();
            SensorInfo sensorInfo= new SensorInfo(sensor.getWmo(),sensor.getNameCity(),sensor.getNameState(),sensor.getLongitude(),sensor.getLatitude(),sensor.getAltitude());
            unicSensorData.setSensorInfo(sensorInfo);
            unicSensorData.setSensorDetail(lastsSensorData);

            return unicSensorData;
        }else {
            throw new NotFoundException();
        }
    }
}
