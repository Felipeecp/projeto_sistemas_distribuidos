package com.brmo.clients.domain;

import java.time.Instant;

public record SensorInfo(String codWMO, String cidade, String estado, Double longitude,
                         Double latitude, Integer altitude) {
}
