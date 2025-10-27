package ser.mil.openweatherapp.repositories;

import ser.mil.openweatherapp.models.Weather;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface WeatherRepository {
    Weather save(Weather weather);
    boolean existsByCityName(String cityName, OffsetDateTime since);

    Optional<Weather> findLastForCity(String cityName);
}
