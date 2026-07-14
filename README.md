# Weather API Wrapper

A REST API built with Spring Boot that wraps the [Visual Crossing Weather](https://www.visualcrossing.com/) service, providing current conditions and a 15-day forecast for any city, with Redis caching and per-IP rate limiting.

Solution for the [roadmap.sh Weather API Wrapper Service](https://roadmap.sh/projects/weather-api-wrapper-service) project.

---

## Features

- **Single endpoint** — query weather by city name
- **Redis caching** — responses are cached for 1 hour to avoid redundant upstream calls
- **Rate limiting** — 10 requests per minute per IP address, enforced via Bucket4j
- **Structured error responses** — 404 for unknown cities, 502 for upstream failures
- **OpenAPI / Swagger UI** — interactive docs available at `/swagger-ui.html`

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4 |
| HTTP client | Spring `RestClient` |
| Cache | Redis + Spring Cache |
| Rate limiting | Bucket4j |
| API docs | springdoc-openapi 3 |
| Build | Maven |

---

## Prerequisites

- Java 21+
- Maven 3.9+
- Redis running on `localhost:6379`
- A [Visual Crossing API key](https://www.visualcrossing.com/weather-api) (free tier available)

---

## Running locally

1. **Clone the repository**

```bash
git clone <repo-url>
cd Whater-API
```

2. **Set your API key**

```bash
export API_KEY=your_visual_crossing_api_key
```

3. **Start Redis** (Docker example)

```bash
docker run -d -p 6379:6379 redis:alpine
```

4. **Run the application**

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

The server starts on `http://localhost:8080`.

---

## API

### Get weather by city

```
GET /api/v1/{cityName}
```

| Parameter | Type | Description |
|---|---|---|
| `cityName` | path | City name, e.g. `London` or `São Paulo` |

**Example request**

```bash
curl http://localhost:8080/api/v1/London
```

**Example response (200 OK)**

```json
{
  "queryCost": 1,
  "latitude": 51.5074,
  "longitude": -0.1278,
  "resolvedAdress": "London, England, United Kingdom",
  "adress": "London",
  "timezone": "Europe/London",
  "tzoffset": 1.0,
  "description": "Similar temperatures continuing with a chance of rain Wednesday & Thursday.",
  "days": [
    {
      "datetime": "2024-07-14",
      "conditions": "Partially cloudy",
      "description": "Partly cloudy throughout the day.",
      "icon": "partly-cloudy-day"
    }
  ],
  "alerts": [],
  "currentConditions": {
    "datetime": "14:30:00",
    "conditions": "Clear",
    "icon": "clear-day",
    "sunset": "20:45:00"
  }
}
```

**Error responses**

| Status | Reason |
|---|---|
| `404 Not Found` | City could not be resolved by the upstream API |
| `429 Too Many Requests` | More than 10 requests in the last minute from the same IP |
| `502 Bad Gateway` | Unexpected error from the Visual Crossing API |

---

## Swagger UI

With the application running, open:

```
http://localhost:8080/swagger-ui.html
```

The OpenAPI spec (JSON) is available at:

```
http://localhost:8080/v3/api-docs
```

---

## Project structure

```
src/main/java/com/example/whaterapi/
├── config/
│   ├── OpenApiConfig.java        # OpenAPI metadata
│   ├── RedisConfig.java          # Redis cache setup (1-hour TTL)
│   └── RestClientConfig.java     # RestClient pointed at Visual Crossing
├── filter/
│   └── RateLimitFilter.java      # 10 req/min per IP via Bucket4j
└── weather/
    ├── WeatherController.java
    ├── WeatherService.java        # Calls upstream API; result cached in Redis
    ├── dtos/
    │   ├── WeatherResponse.java
    │   ├── Day.java
    │   ├── CurrentConditions.java
    │   └── Alert.java
    └── exceptions/
        ├── CityNotFoundException.java
        ├── WeatherApiException.java
        └── GlobalExceptionHandler.java
```
