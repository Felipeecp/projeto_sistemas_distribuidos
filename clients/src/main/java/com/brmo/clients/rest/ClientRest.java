package com.brmo.clients.rest;

import com.brmo.clients.domain.Sensor;
import com.brmo.clients.dto.SensorRecord;
import com.brmo.clients.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientRest {

    private final ClientService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Sensor sensor){
        service.create(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
