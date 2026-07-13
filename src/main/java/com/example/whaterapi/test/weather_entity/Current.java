package com.example.whaterapi.test.weather_entity;

public record Current(String lastUpdatedEpoch, String tempC, String tempF, Condition condition, int windMph, String windDir, int humidity, double feelLikeC, double uv) { }
