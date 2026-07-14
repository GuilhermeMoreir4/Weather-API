package com.example.whaterapi.weather;

import com.example.whaterapi.weather.entity.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@RestController
@RequestMapping("api/v1")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    private final Logger logger = LoggerFactory.getLogger(WeatherService.class);


    @GetMapping("{cityName}")
    public ResponseEntity<WeatherResponse> getWeatherFrom(@PathVariable String cityName){
        try {
            WeatherResponse response = weatherService.getWeatherFrom(cityName);
            return ResponseEntity.ok(response);
        } catch (RestClientResponseException e) {
            logger.error("API error to search for weather for {}: Status {}", cityName, e.getStatusCode(), e);
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            logger.error("Internal error to search for weather for {}", cityName, e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
