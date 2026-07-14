package com.example.whaterapi.weather.entity;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;

public record WeatherResponse(int queryCost, double latitude, double longitude, String resolvedAdress, String adress, String timezone, double tzoffset, String description, ArrayList<Day> days, ArrayList<Alert> alerts,CurrentConditions currentConditions) {}
