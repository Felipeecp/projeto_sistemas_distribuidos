package com.brmo.sensorsave.infra.mqueue;

import com.brmo.sensorsave.domain.SensorDetail;
import com.brmo.sensorsave.repository.SensorDetailRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DadosSensorSubscriber {

    private final SensorDetailRepository repository;
    private final ObjectMapper mapper;

    @RabbitListener(queues = "${mq.queues.dados-sensores}")
    public void receiveDataSensor(@Payload String payload){
        try {
            log.info("Dados recebidos da fila.");
            SensorDetail sensorDetail = mapper.readValue(payload, SensorDetail.class);
            repository.save(sensorDetail);
            log.info("Processo finalizado!");
        } catch (JsonProcessingException e){
            log.info("Ocorreu um erro no processamento do sensor save");
        }
    }

}
