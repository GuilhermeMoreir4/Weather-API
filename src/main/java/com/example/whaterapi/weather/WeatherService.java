package com.example.whaterapi.weather;


import com.example.whaterapi.weather.entity.WeatherResponse;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {

    private final RestClient restClient;


    @Value("${api.key}")
    private String apiKey;

    public WeatherService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Cacheable(value = "cities", key = "#cityName")
    @JsonView(WeatherResponse.class)
    public WeatherResponse getWeatherFrom(String cityName) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/VisualCrossingWebServices/rest/services/timeline/{location}")
                        .queryParam("unitGroup", "us")
                        .queryParam("key", apiKey)
                        .queryParam("contentType", "json")
                        .build(cityName))
                .retrieve()
                .body(WeatherResponse.class);
    }
}
