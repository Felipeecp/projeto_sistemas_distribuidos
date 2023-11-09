package com.brmo.clients.dto;

import com.brmo.clients.domain.enumerated.Region;
import com.brmo.clients.domain.enumerated.UF;

public record SensorRecord(Region region, UF uf, String latitude, String longitude, double altitude
) {
}
