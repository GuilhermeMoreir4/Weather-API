package com.example.whaterapi.weather.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;

@Schema(description = "Full weather response for a queried city, sourced from Visual Crossing")
public record WeatherResponse(
        @Schema(description = "API query cost in credits", example = "1") int queryCost,
        @Schema(description = "Latitude of the resolved location", example = "51.5074") double latitude,
        @Schema(description = "Longitude of the resolved location", example = "-0.1278") double longitude,
        @Schema(description = "Full resolved address as returned by the upstream API", example = "London, England, United Kingdom") String resolvedAdress,
        @Schema(description = "Short address used in the query", example = "London") String adress,
        @Schema(description = "IANA timezone identifier", example = "Europe/London") String timezone,
        @Schema(description = "UTC offset in hours", example = "1.0") double tzoffset,
        @Schema(description = "Human-readable weather summary for the period") String description,
        @Schema(description = "List of daily forecasts (up to 15 days)") ArrayList<Day> days,
        @Schema(description = "Active weather alerts for the area") ArrayList<Alert> alerts,
        @Schema(description = "Current weather conditions at the time of the query") CurrentConditions currentConditions
) {}
