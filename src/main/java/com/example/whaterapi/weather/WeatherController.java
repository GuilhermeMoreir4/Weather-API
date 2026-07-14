package com.example.whaterapi.weather;

import com.example.whaterapi.weather.dtos.WeatherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Weather", description = "Retrieve current conditions and multi-day forecast for a city")
@RestController
@RequestMapping("api/v1")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(
            summary = "Get weather by city name",
            description = "Returns current conditions and a 15-day forecast for the requested city. " +
                    "Results are cached in Redis for 1 hour. Requests are rate-limited to 10 per minute per IP."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Weather data retrieved successfully",
                    content = @Content(schema = @Schema(implementation = WeatherResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "City not found",
                    content = @Content(schema = @Schema(example = "{\"error\": \"City not found: xyz\"}"))
            ),
            @ApiResponse(
                    responseCode = "429",
                    description = "Rate limit exceeded — maximum 10 requests per minute per IP",
                    content = @Content(schema = @Schema(example = "Too many requests"))
            ),
            @ApiResponse(
                    responseCode = "502",
                    description = "Upstream Visual Crossing API returned an unexpected error",
                    content = @Content(schema = @Schema(example = "{\"error\": \"Error querying weather API.\"}"))
            )
    })
    @GetMapping("{cityName}")
    public ResponseEntity<WeatherResponse> getWeatherFrom(
            @Parameter(description = "City name to query, e.g. \"London\" or \"São Paulo\"", example = "London")
            @PathVariable String cityName) {
        return ResponseEntity.ok(weatherService.getWeatherFrom(cityName));
    }

}
