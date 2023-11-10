package com.ufma.wmo.domain.rawData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDateTime;

import static com.ufma.wmo.application.utils.ReaderCsvUtils.getString;
import static com.ufma.wmo.application.utils.ReaderCsvUtils.toDouble;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RowState {

    private Double temperature;
    private Double temperatureStd;
    private Double umidade;
    private Double umidadeStd;
    private LocalDateTime registerDate;
    private String uf;

    public static RowState of(CSVRecord record){
        return RowState.builder()
                .temperature(toDouble(record, 1))
                .temperatureStd(toDouble(record, 2))
                .umidade(toDouble(record, 3))
                .umidadeStd(toDouble(record, 4))
                .registerDate(LocalDateTime.now())
                .uf(getString(record, 5))
                .build();
    }

}
