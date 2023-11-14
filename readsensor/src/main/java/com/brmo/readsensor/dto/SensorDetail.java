package com.brmo.readsensor.dto;

import java.time.Instant;

public record SensorDetail(String codWMO, Instant registrationDate, Data data) {
}