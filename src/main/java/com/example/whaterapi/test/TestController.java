package com.example.whaterapi.test;

import com.example.whaterapi.test.weather_entity.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/v1/weather")
public class TestController {


    @GetMapping("today")
    public WeatherObject getWeather(){
        List<ForecastDay> forecast = new ArrayList(Collections.singleton(new ForecastDay(new Date(), new Day(26.1, 16.0, 20))));

        return new WeatherObject(new Location("Chicago", "Illinois", "USA", "41.8781", "-87.6298", "America/Chicago"), new Current("1718224200", "22.5", "72.5", new Condition("Partly Cloudy", "//://weatherapi.com", 1003),10, "ENE", 52, 22.5,5.0 ), new Forecast(forecast));
    }
}
