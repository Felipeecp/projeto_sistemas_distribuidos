package com.ufma.wmo.application.beans;

import com.ufma.wmo.application.exceptions.BusinessExceptions;
import com.ufma.wmo.domain.rawData.RowState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.ufma.wmo.application.utils.ReaderCsvUtils.positionRowIndex;

@Slf4j
@Configuration
public class StateMapConfiguration {
    private static final String ESTADOS = "classpath:/data/estados/*.csv";
    private int positionRowIndex;

    @Bean
    public Map<String, RowState> stateMap(ApplicationContext context){

        Map<String, RowState> rowStateMap = new HashMap<>();
        this.positionRowIndex = positionRowIndex();

        long init = System.currentTimeMillis();

        log.info("Init readCsv for index: " + positionRowIndex);

        try {
            Resource[] resources = context.getResources(ESTADOS);

            for(Resource resource: resources){
                Reader reader = getReader(resource);
                CSVRecord csvRecord = getCsvRecord(reader);
                RowState rowState = RowState.of(csvRecord);
                rowStateMap.put(rowState.getUf(), rowState);
            }
        } catch (IOException e){
            log.info("Error ao ler dados dos estados!");
            throw new BusinessExceptions("Erro ao ler dados dos estados! " + e.getMessage());
        }

        log.info("Time total: "  + (System.currentTimeMillis() - init) +"ms");
        return rowStateMap;
    }

    private CSVRecord getCsvRecord(Reader reader) throws IOException {
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(','), 0L, positionRowIndex);
        return csvParser.getRecords().get(positionRowIndex);
    }

    private Reader getReader(Resource resource) throws IOException {
        InputStream is = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        return new BufferedReader(inputStreamReader);
    }

}
