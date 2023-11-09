package com.brmo.clients.rest;

import com.brmo.clients.domain.Sensor;
import com.brmo.clients.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientRest {

    private final ClientService service;

    @GetMapping("/ok")
    public ResponseEntity<String> ok(){
        return ResponseEntity.ok("Ok");
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors(){
        List<Sensor> sensorList = service.findAll();
        return new ResponseEntity<>(sensorList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Sensor sensor){
        service.create(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
