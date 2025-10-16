package ser.mil.openweatherapp.infrastructure.repository.weather;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.OffsetDateTime;

@Entity
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String cityName;
    private OffsetDateTime readAt=OffsetDateTime.now();
    private Double temp;

    private Double presure;
    private Double windSpeed;

    public WeatherEntity(String id, String cityName, OffsetDateTime readAt, Double temp, Double presure, Double windSpeed) {
        this.id = id;
        this.cityName = cityName;
        this.readAt = readAt;
        this.temp = temp;
        this.presure = presure;
        this.windSpeed = windSpeed;
    }

    public WeatherEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public OffsetDateTime getReadAt() {
        return readAt;
    }

    public void setReadAt(OffsetDateTime readAt) {
        this.readAt = readAt;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPresure() {
        return presure;
    }

    public void setPresure(Double presure) {
        this.presure = presure;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
