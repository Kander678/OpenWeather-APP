package ser.mil.openweatherapp.repositories;

import org.springframework.stereotype.Component;
import ser.mil.openweatherapp.models.Weather;
import ser.mil.openweatherapp.models.WeatherEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WeatherRepositorySQL implements WeatherRepository {
    private final WeatherRepositorySpringData weatherRepositorySpringData;

    public WeatherRepositorySQL(WeatherRepositorySpringData weatherRepositorySpringData) {
        this.weatherRepositorySpringData = weatherRepositorySpringData;
    }

    @Override
    public Weather save(Weather weather) {
       WeatherEntity weatherEntity = new WeatherEntity();

       weatherEntity.setCityName(weather.cityName());
       weatherEntity.setReadAt(weather.readAt());
       weatherEntity.setTemp(weather.temp());
       weatherEntity.setPresure(weather.presure());
       weatherEntity.setWindSpeed(weather.windSpeed());
       weatherRepositorySpringData.save(weatherEntity);
       return weather;
    }

    @Override
    public boolean existsByCityName(String cityName, OffsetDateTime since) {
        return weatherRepositorySpringData.existsByCityNameIgnoreCaseAndReadAtAfter(cityName, since);
    }

    @Override
    public Optional<Weather> findLastForCity(String cityName) {
        return weatherRepositorySpringData.findTopByCityNameIgnoreCaseOrderByReadAtDesc(cityName)
                .map(e -> new Weather(
                        e.getId(),
                        e.getCityName(),
                        e.getReadAt(),
                        e.getTemp(),
                        e.getPresure(),
                        e.getWindSpeed()
                ));
    }

    @Override
    public List<Weather> findAll() {
        return weatherRepositorySpringData.findAll().stream().map(e -> new Weather(
                e.getId(),
                e.getCityName(),
                e.getReadAt(),
                e.getTemp(),
                e.getPresure(),
                e.getWindSpeed()
        )).collect(Collectors.toList());
    }
}
