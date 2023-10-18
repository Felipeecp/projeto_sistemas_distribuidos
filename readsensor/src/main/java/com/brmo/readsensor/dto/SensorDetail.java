package com.brmo.readsensor.dto;

import java.time.LocalDateTime;

public record SensorDetail(String codWMO, LocalDateTime registrationDate, Data data) {
}
