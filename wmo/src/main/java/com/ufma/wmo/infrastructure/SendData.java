package com.ufma.wmo.infrastructure;

import com.ufma.wmo.domain.dto.SensorDetail;
import com.ufma.wmo.domain.rawData.RowCities;

public interface SendData {

    void sendData(SensorDetail sensorDetail);

    public void save(RowCities rowCities);

}
