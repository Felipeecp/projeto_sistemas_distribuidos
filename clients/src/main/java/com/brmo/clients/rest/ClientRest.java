package com.brmo.clients.rest;

import com.brmo.clients.domain.Sensor;
import com.brmo.clients.domain.SensorDetail;
import com.brmo.clients.domain.UnicSensorData;
import com.brmo.clients.dto.MapInfo;
import com.brmo.clients.service.ClientService;
import com.brmo.clients.service.SensorDetailService;
import com.brmo.clients.service.UnicSensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
@Slf4j
public class ClientRest {

    private final ClientService service;
    private final SensorDetailService sensorDetailService;
    private final UnicSensorService unicSensorService;

    @GetMapping("/ok")
    public ResponseEntity<String> ok(){
        return ResponseEntity.ok("Ok");
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors(){
        List<Sensor> sensorList = service.findAll();
        return new ResponseEntity<>(sensorList, HttpStatus.OK);
    }

    @GetMapping("/sensorData/{sensorId}")
    public ResponseEntity<UnicSensorData> getUnicSensorData(@PathVariable String sensorId){
        UnicSensorData sensorInfoAndLastData = unicSensorService.getSensorInfoAndLastData(sensorId);
        return new ResponseEntity<>(sensorInfoAndLastData,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Sensor sensor){
        service.create(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{sensorId}")
    public ResponseEntity<Void> delete(@PathVariable String sensorId){
        service.delete(sensorId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/sensorDetail/distincCodWMO")
    public ResponseEntity<List<String>> getDistinctCodWMO(){
        List<String> distinctCodWMOList = sensorDetailService.findDistinctCodWMO();
        return new ResponseEntity<>(distinctCodWMOList, HttpStatus.OK);
    }

    @GetMapping("/sensorDetail/{codWMO}")
    public ResponseEntity<List<SensorDetail>> getSensorDetailByCodWMO(@PathVariable String codWMO){
        List<SensorDetail> codWMOList = sensorDetailService.findAllByCodWMO(codWMO);
        return new ResponseEntity<>(codWMOList, HttpStatus.OK);
    }

    @GetMapping("/sensorDetail")
    public ResponseEntity<List<SensorDetail>> getAllSensorDetail(){
        List<SensorDetail> sensorDetailList = sensorDetailService.findAll();
        return new ResponseEntity<>(sensorDetailList, HttpStatus.OK);
    }

    @GetMapping("/findAverageMaps")
    public ResponseEntity<List<MapInfo>> findAverageMaps(){
        return ResponseEntity.ok(service.findAverageMaps());
    }

}
