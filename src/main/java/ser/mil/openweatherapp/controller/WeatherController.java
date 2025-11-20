package ser.mil.openweatherapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ser.mil.openweatherapp.response.ApiResponse;
import ser.mil.openweatherapp.models.Weather;
import ser.mil.openweatherapp.services.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public Weather getWeather(@PathVariable String city) {
        return weatherService.fetchAndStore(city);
    }


    @PostMapping
    public ResponseEntity<ApiResponse> createWeather(@RequestBody Weather weather) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(weatherService.createWeather(weather)));
    }


}
