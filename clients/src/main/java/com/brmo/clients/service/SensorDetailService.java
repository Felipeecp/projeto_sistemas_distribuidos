package com.brmo.clients.service;

import com.brmo.clients.domain.*;
import com.brmo.clients.repository.SensorDetailRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.*;

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


    public UltimosDadosSensor getUltimos7Dados(String codWMO){
        List<SensorDetail> ultimosDados = sensorDetailRepository.findOnePerDay(codWMO);
        return convertToUltimosDadosSensores(codWMO, ultimosDados);
    }

    public List<ClimateClassification> classifyWeather(){
        List<ClimateClassification> climateClassifications = sensorDetailRepository.countByTemperatureAndHumidityCategories();
        return  climateClassifications;
    }

    public List<UltimosDadosSensor> getLastsTemperatures(String sensorACode, String sensorBCode) {
        if(sensorACode == null && sensorBCode ==null ){
            throw new NotFoundException();
        }
        List<SensorDetail> sensorAData = sensorDetailRepository.findOnePerDay(sensorACode);
        List<SensorDetail> sensorBData = sensorDetailRepository.findOnePerDay(sensorBCode);

        List<UltimosDadosSensor> result = new ArrayList<>();
        UltimosDadosSensor ultimosDadosSensorA = convertToUltimosDadosSensores(sensorACode, sensorAData);
        UltimosDadosSensor ultimosDadosSensorB = convertToUltimosDadosSensores(sensorBCode, sensorBData);

        result.add(ultimosDadosSensorA);
        result.add(ultimosDadosSensorB);

        return result;
    }

    public UltimosDadosSensor convertToUltimosDadosSensores(String sensorCode, List<SensorDetail> sensorDetail){
        UltimosDadosSensor retorno = new UltimosDadosSensor();
        retorno.setCodWMO(sensorCode);
        List<DadosSemana> dadosSemana = new ArrayList<>();

        if(!sensorDetail.isEmpty()){
            SensorDetail ultimoDadoA = sensorDetail.get(0);
            retorno.setUltimaTemperatura(ultimoDadoA.getData().temperatura());
            retorno.setUltimaUmidade(ultimoDadoA.getData().umidade());
        }

        for (SensorDetail dado:
                sensorDetail) {
            DadosSemana dadoSemanal = new DadosSemana();
            Instant registrationDate = dado.getRegistrationDate();
            ZonedDateTime zonedDateTime = registrationDate.atZone(ZoneId.of("America/Sao_Paulo"));
            String diaDaSemana = zonedDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
            dadoSemanal.setNome(diaDaSemana);
            dadoSemanal.setTemperatura(dado.getData().temperatura());
            dadoSemanal.setUmidade(dado.getData().umidade());
            dadosSemana.add(dadoSemanal);
        }

        retorno.setDadosSemana(dadosSemana);
        return retorno;
    }

}
