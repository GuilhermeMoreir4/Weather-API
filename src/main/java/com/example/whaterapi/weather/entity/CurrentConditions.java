package com.example.whaterapi.weather.entity;

import java.time.LocalTime;

public record CurrentConditions(LocalTime datetime, String conditions, String icon, String sunset) {}
