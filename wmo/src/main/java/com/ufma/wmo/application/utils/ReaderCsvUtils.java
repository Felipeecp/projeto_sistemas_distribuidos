package com.ufma.wmo.application.utils;

import org.apache.commons.csv.CSVRecord;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReaderCsvUtils {

    public static int positionRowIndex(){
        LocalDate dateNow = LocalDate.now();
        LocalDate firstDayOfTheYear = LocalDate.of(dateNow.getYear(), 1, 1);
        Duration duration = Duration.between(firstDayOfTheYear.atStartOfDay(), dateNow.atStartOfDay());

        int currentTime = LocalDateTime.now().getHour();
        return ((int) duration.toDays()) * 24 + 1 + currentTime;
    }
    public static Double toDouble(CSVRecord record, int column){
        return Double.parseDouble(record.get(column));
    }

    public static Integer toInteger(CSVRecord record, int column){
        return Integer.parseInt(record.get(column));
    }

    public static String getString(CSVRecord record, int column){
        return record.get(column);
    }

}
