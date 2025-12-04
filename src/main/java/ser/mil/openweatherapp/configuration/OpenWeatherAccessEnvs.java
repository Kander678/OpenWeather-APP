package ser.mil.openweatherapp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openweather")
public class OpenWeatherAccessEnvs {

    private String apiKey;

    private String baseUrl;

    private String units = "metric";

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "OpenWeatherAccessEnvs{" +
                "apiKey='" + apiKey + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", units='" + units + '\'' +
                '}';
    }
}