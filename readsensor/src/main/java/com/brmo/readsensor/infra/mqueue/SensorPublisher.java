package com.brmo.readsensor.infra.mqueue;

import com.brmo.readsensor.dto.SensorDetail;
import com.brmo.readsensor.exception.BussinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class SensorPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final ObjectMapper mapper;

    public void publicarDados(SensorDetail sensorDetail) {
        log.info("Inicializando escrita na fila!");
        var json = toString(sensorDetail);
        rabbitTemplate.convertAndSend(queue.getName(), json);
        log.info("Finalizado escrita na fila");
    }

    private String toString(SensorDetail sensorDetail){
        try {
            return mapper.writeValueAsString(sensorDetail);
        } catch (JsonProcessingException e){
            log.info("Erro ao fazer o parse!");
            throw new BussinessException("Erro ao converter o dado para string");
        }
    }

}
