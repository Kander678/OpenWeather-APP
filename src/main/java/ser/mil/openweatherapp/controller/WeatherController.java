package ser.mil.openweatherapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
