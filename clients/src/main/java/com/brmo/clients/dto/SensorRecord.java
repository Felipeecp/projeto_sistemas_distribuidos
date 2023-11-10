package com.brmo.clients.dto;

import com.brmo.clients.domain.enumerated.Region;
import com.brmo.clients.domain.enumerated.UF;

import java.time.LocalDateTime;

public record SensorRecord(
        String ibgeCod,
        String nameState,
        String nameCity,
        Double longitude,
        Double latitude,
        Integer altitude,
        String wmo,
        String uf,
        Region region
) {
}
