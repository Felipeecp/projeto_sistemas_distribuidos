package com.brmo.readsensor.rest;

import com.brmo.readsensor.dto.SensorDetail;
import com.brmo.readsensor.infra.mqueue.SensorPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sensor")
public class SensorResource {
    private final SensorPublisher sensorPublisher;

    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("OK");
    }

    @PostMapping
    public ResponseEntity<Void> saveData(@RequestBody SensorDetail sensorDetail)  {
        sensorPublisher.publicarDados(sensorDetail);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
