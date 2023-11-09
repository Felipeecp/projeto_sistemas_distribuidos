package com.brmo.sensorsave.infra.mqueue;

import com.brmo.sensorsave.domain.SensorDetail;
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
    private final ObjectMapper mapper;

    @RabbitListener(queues = "${mq.queues.dados-sensores}")
    public void receiveDataSensor(@Payload String payload){
        try {
            SensorDetail sensorDetail = mapper.readValue(payload, SensorDetail.class);
            log.info("Dados lidos.");
            log.info(sensorDetail.toString());
        } catch (JsonProcessingException e){
            log.info("Error ao converter os dados para SensorData.");
        }
    }

}
