package ser.mil.openweatherapp.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ser.mil.openweatherapp.models.Weather;
import ser.mil.openweatherapp.repositories.WeatherRepository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final RestClient rest;
    private final ObjectMapper om = new ObjectMapper();

    @Value("${openweather.apiKey}")
    private String apiKey;
    @Value("${openweather.baseUrl}")
    private String baseUrl;
    @Value("${openweather.units:metric}")
    private String units;

    public WeatherService(WeatherRepository weatherRepository, RestClient.Builder builder) {
        this.weatherRepository = weatherRepository;
        this.rest = builder.build();
    }

    public Weather fetchAndStore(String city) {
        var timeNow = OffsetDateTime.now(ZoneOffset.UTC);
        var since = timeNow.minusHours(1);

        var lastRead = weatherRepository.findLastForCity(city);
        if (lastRead.isPresent() && lastRead.get().readAt().isAfter(since)) {
            return lastRead.get();
        }

        String url = "%s/weather?q=%s&units=%s&appid=%s".formatted(baseUrl, city, units, apiKey);
        String body = rest.get().uri(url).retrieve().body(String.class);

        try {
            JsonNode j = om.readTree(body);

            JsonNode tempNode = j.at("/main/temp");
            JsonNode presNode = j.at("/main/pressure");
            JsonNode windNode = j.at("/wind/speed");

            Double tempVal = tempNode.asDouble();
            Double presVal = presNode.asDouble();
            Double windVal = windNode.asDouble();

            Weather weather = new Weather(
                    UUID.randomUUID().toString(),
                    city,
                    OffsetDateTime.now(ZoneOffset.UTC),
                    tempVal,
                    presVal,
                    windVal
            );

            return weatherRepository.save(weather);
        } catch (Exception e) {
            throw new IllegalStateException("Błąd pobierania danych pogodowych dla: " + city, e);
        }
    }
    public Weather createWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

}
