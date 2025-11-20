package ser.mil.openweatherapp.response;

import ser.mil.openweatherapp.models.Weather;

public class ApiResponse {
    private Weather weather;

    public ApiResponse(Weather weather) {
        this.weather = weather;
    }

    public ApiResponse() {
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
