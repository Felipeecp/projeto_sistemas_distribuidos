package com.brmo.clients.service;

import com.brmo.clients.domain.SensorDetail;
import com.brmo.clients.repository.SensorDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDetailService {

    private final SensorDetailRepository sensorDetailRepository;

    @Autowired
    public SensorDetailService(SensorDetailRepository sensorDetailRepository) {
        this.sensorDetailRepository = sensorDetailRepository;
    }

    public List<String> findDistinctCodWMO(){
        return sensorDetailRepository.findDistinctCodWMO();
    }

    public List<SensorDetail> findAllByCodWMO(String codWMO){
        return sensorDetailRepository.findAllByCodWMO(codWMO);
    }

    public List<SensorDetail> findAll(){
        return sensorDetailRepository.findAll();
    }

}
