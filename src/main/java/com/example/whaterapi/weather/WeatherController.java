package com.example.whaterapi.weather;

import com.example.whaterapi.weather.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("api/v1")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }


    @GetMapping("{cityName}")
    public ResponseEntity<WeatherResponse> getWeatherFrom(@PathVariable String cityName){
        return service.getWeatherFrom(cityName);
    }

}
