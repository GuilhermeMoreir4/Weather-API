package com.example.whaterapi.weather;


import com.example.whaterapi.weather.dtos.WeatherResponse;
import com.example.whaterapi.weather.exceptions.CityNotFoundException;
import com.example.whaterapi.weather.exceptions.WeatherApiException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Slf4j
@Service
public class WeatherService {

    private final RestClient restClient;


    @Value("${api.key}")
    private String apiKey;

    public WeatherService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Cacheable(value = "cities", key = "#cityName")
    public WeatherResponse getWeatherFrom(String cityName) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/VisualCrossingWebServices/rest/services/timeline/{location}")
                            .queryParam("unitGroup", "us")
                            .queryParam("key", apiKey)
                            .queryParam("contentType", "json")
                            .build(cityName))
                    .retrieve()
                    .body(WeatherResponse.class);
        }catch (HttpClientErrorException.NotFound e) {

            throw new CityNotFoundException("Cidade não encontrada: " + cityName);

        } catch (RestClientResponseException e) {

            throw new WeatherApiException("Erro ao consultar API de clima.", e);
        }

    }
}
