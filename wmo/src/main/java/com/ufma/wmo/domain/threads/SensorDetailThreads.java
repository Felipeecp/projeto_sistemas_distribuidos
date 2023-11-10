package com.ufma.wmo.domain.threads;

import com.ufma.wmo.domain.dto.Data;
import com.ufma.wmo.domain.dto.SensorDetail;
import com.ufma.wmo.domain.rawData.RowCities;
import com.ufma.wmo.domain.rawData.RowState;
import com.ufma.wmo.infrastructure.SendData;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SensorDetailThreads extends Thread{

    private List<CSVRecord> records;
    private SendData sendData;
    private Map<String, RowState> rowStateMap;
    private Random random;

    public SensorDetailThreads(SendData sendData, List<CSVRecord> records, Map<String, RowState> rowStateMap){
        this.sendData = sendData;
        this.records = records;
        this.rowStateMap = rowStateMap;
        this.random = new Random();
    }

    @Override
    public void run() {
        for(CSVRecord record: records){
            RowCities rowCity = RowCities.of(record);
            //SensorDetail sensorDetail = getSensorDetail(record);
            //sendData.sendData(sensorDetail);
            sendData.save(rowCity);
        }
    }

    private SensorDetail getSensorDetail(CSVRecord record){
        RowCities rowCity = RowCities.of(record);
        RowState state = rowStateMap.get(rowCity.getUf());
        Data data = getData(state);

        return new SensorDetail(rowCity.getWmo(), LocalDateTime.now(), data);
    }

    private Data getData(RowState rowState){
        double minTemp = getMin(rowState.getTemperature(), rowState.getTemperatureStd());
        double maxTemp = getMax(rowState.getTemperature(), rowState.getTemperatureStd());
        double temp = random.nextDouble() * (maxTemp - minTemp) + minTemp;

        double minUmidade = getMin(rowState.getUmidade(), rowState.getUmidadeStd());
        double maxUmidade = getMax(rowState.getUmidade(), rowState.getUmidadeStd());
        double umidade = random.nextDouble() * (maxUmidade - minUmidade) + minUmidade;

        return new Data(temp, umidade);
    }

    private double getMin(double value, double std){
        return value - std;
    }

    private double getMax(double value, double std){
        return value + std;
    }
}
