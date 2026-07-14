package com.example.whaterapi.weather.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Weather data for a single day")
public record Day(
        @Schema(description = "Calendar date of this forecast entry", example = "2024-07-14") LocalDate datetime,
        @Schema(description = "Short conditions label", example = "Partially cloudy") String conditions,
        @Schema(description = "Longer human-readable description of the day's weather") String description,
        @Schema(description = "Icon key representing the weather condition", example = "partly-cloudy-day") String icon
) {}
