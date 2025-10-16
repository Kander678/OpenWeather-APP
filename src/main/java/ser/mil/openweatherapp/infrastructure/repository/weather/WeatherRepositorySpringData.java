package ser.mil.openweatherapp.infrastructure.repository.weather;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface WeatherRepositorySpringData extends JpaRepository<WeatherEntity,String> {
    boolean existsByCityNameIgnoreCaseAndReadAtAfter(String cityName, OffsetDateTime since);
    Optional<WeatherEntity> findTopByCityNameIgnoreCaseOrderByReadAtDesc(String cityName);
}
