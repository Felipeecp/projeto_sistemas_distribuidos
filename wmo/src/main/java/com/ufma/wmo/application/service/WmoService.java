package com.ufma.wmo.application.service;

import com.ufma.wmo.infrastructure.SendData;
import com.ufma.wmo.application.exceptions.BusinessExceptions;
import com.ufma.wmo.domain.rawData.RowState;
import com.ufma.wmo.domain.threads.SensorDetailThreads;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class WmoService {

    @Value("classpath:/data/cidades/cidades.csv")
    private Resource resource;
    private final SendData sendData;
    private final Map<String, RowState> rowStateMap;

    public void run() {
        long init = System.currentTimeMillis();
        List<CSVRecord> records = getRecords();

        int cores = getCores();
        int range = records.size() / cores;

        List<SensorDetailThreads> sensorDetailThreads = new ArrayList<>();

        for (int i = 0; i < cores; i++) {
            int interval = i * range;
            int maxLength = i == cores - 1 ? records.size() : (i + 1) * range;

            List<CSVRecord> subRecords = records.subList(interval, maxLength);

            SensorDetailThreads threads  = new SensorDetailThreads(sendData, subRecords, rowStateMap);
            sensorDetailThreads.add(threads);
        }

        sensorDetailThreads.forEach(Thread::start);

        for(Thread thread: sensorDetailThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        log.info("Tempo total: " + (System.currentTimeMillis() - init) +" ms");
    }

    private List<CSVRecord> getRecords(){
        List<CSVRecord> records = new ArrayList<>();

        try {
            CSVParser parser = getCsvReader();
            records = parser.getRecords();

            if(!records.isEmpty()){
                records.remove(0);
            }

        } catch (IOException e) {
            throw new BusinessExceptions("Erro ao ler os dados da cidade");
        }

        return records;
    }

    private CSVParser getCsvReader() throws IOException{
        InputStream is = resource.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(is);
        Reader reader = new BufferedReader(streamReader);

        return new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(','));
    }

    private int getCores(){
        int cores = Runtime.getRuntime().availableProcessors();
        cores = cores == 0 ? 1 : cores;
        return cores;
    }
}
