package com.example.whaterapi.weather.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Active weather alert issued for the queried area")
public record Alert(
        @Schema(description = "Alert event type", example = "Thunderstorm Warning") String event,
        @Schema(description = "Short headline of the alert", example = "Thunderstorm Warning issued July 14 at 2:00PM EDT") String headline,
        @Schema(description = "Full alert text with details and instructions") String description
) {}
