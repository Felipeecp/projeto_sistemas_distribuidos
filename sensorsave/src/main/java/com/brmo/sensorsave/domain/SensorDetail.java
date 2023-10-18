package com.brmo.sensorsave.domain;

import java.time.LocalDateTime;

public record SensorDetail( String codWMO, LocalDateTime registrationDate, Data data ){
}
