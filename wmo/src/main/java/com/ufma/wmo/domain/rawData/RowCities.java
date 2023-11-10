package com.ufma.wmo.domain.rawData;

import com.ufma.wmo.domain.enumerated.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVRecord;

import static com.ufma.wmo.application.utils.ReaderCsvUtils.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RowCities {

        private String ibgeCod;
        private String nameState;
        private String nameCity;
        private Double longitude;
        private Double latitude;
        private Integer altitude;
        private String wmo;
        private String uf;
        private Region region;

        public static RowCities of(CSVRecord record){
                return RowCities
                        .builder()
                        .ibgeCod(getString(record, 0))
                        .nameState(getString(record, 1))
                        .nameCity(getString(record, 2))
                        .longitude(toDouble(record, 3))
                        .latitude(toDouble(record, 4))
                        .altitude(toInteger(record, 5))
                        .wmo(getString(record, 6))
                        .uf(getString(record, 7))
                        .region(Region.valueOf(getString(record, 8)))
                        .build();
        }

}
