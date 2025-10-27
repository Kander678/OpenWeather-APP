package ser.mil.openweatherapp.models;

import java.time.OffsetDateTime;

public record Weather(String id,
                      String cityName,
                      OffsetDateTime readAt,
                      Double temp,
                      Double presure,
                      Double windSpeed) {
    @Override
    public String id() {
        return id;
    }

    @Override
    public String cityName() {
        return cityName;
    }

    @Override
    public OffsetDateTime readAt() {
        return readAt;
    }

    @Override
    public Double temp() {
        return temp;
    }

    @Override
    public Double presure() {
        return presure;
    }

    @Override
    public Double windSpeed() {
        return windSpeed;
    }
}
