package ser.mil.openweatherapp.domain.repository;

import ser.mil.openweatherapp.domain.model.Weather;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface WeatherRepository {
    Weather save(Weather weather);
    boolean existsByCityName(String cityName, OffsetDateTime since);

    Optional<Weather> findLastForCity(String cityName);
}
