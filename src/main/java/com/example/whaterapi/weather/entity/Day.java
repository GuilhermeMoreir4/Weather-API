package com.example.whaterapi.weather.entity;

import java.time.LocalDate;

public record  Day (LocalDate datetime, String conditions, String description, String icon) {}
