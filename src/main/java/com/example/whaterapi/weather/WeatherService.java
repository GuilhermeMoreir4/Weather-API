package com.example.whaterapi.weather;


import com.example.whaterapi.weather.entity.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {

    private final RestClient restClient;

    private final Logger logger =  LoggerFactory.getLogger(WeatherService.class);

    @Value("${api.key}")
    private String apiKey;

    public WeatherService(RestClient restClient) {
        this.restClient = restClient;
    }


    public ResponseEntity<WeatherResponse> getWeatherFrom(String cityName){
        logger.info("CityName goes like this: " + cityName);

        try{
            WeatherResponse result = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/VisualCrossingWebServices/rest/services/timeline/{location}")
                            .queryParam("unitGroup", "us")
                            .queryParam("key", apiKey)
                            .queryParam("contentType", "json")
                            .build(cityName))
                    .retrieve()
                    .body(WeatherResponse.class);

            return new ResponseEntity(result, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            logger.error("Error on client" + e.getMessage());

        }catch (HttpServerErrorException e){
            logger.error("Error on server" + e.getMessage());
        }
        return null;
    }
}
