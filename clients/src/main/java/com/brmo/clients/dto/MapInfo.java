package com.brmo.clients.dto;

import java.util.List;

public record MapInfo(String id, String region, Integer totalSensor, List<DetailMap> detail) {
}
