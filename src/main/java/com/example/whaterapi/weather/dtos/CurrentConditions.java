package com.example.whaterapi.weather.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;

@Schema(description = "Current weather conditions at the queried location")
public record CurrentConditions(
        @Schema(description = "Local time of the observation", example = "14:30:00") LocalTime datetime,
        @Schema(description = "Short conditions label", example = "Clear") String conditions,
        @Schema(description = "Icon key representing the current weather condition", example = "clear-day") String icon,
        @Schema(description = "Local sunset time", example = "20:45:00") String sunset
) {}
