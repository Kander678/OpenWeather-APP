package ser.mil.openweatherapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ser.mil.openweatherapp.models.WeatherEntity;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface WeatherRepositorySpringData extends JpaRepository<WeatherEntity,String> {
    boolean existsByCityNameIgnoreCaseAndReadAtAfter(String cityName, OffsetDateTime since);
    Optional<WeatherEntity> findTopByCityNameIgnoreCaseOrderByReadAtDesc(String cityName);
}
