package com.ufma.wmo.domain.dto;

import java.time.LocalDateTime;

public record SensorDetail(String codWMO, LocalDateTime registrationDate, Data data) {
}
