package ser.mil.openweatherapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClient;
import ser.mil.openweatherapp.repositories.WeatherRepository;

@ExtendWith(MockitoExtension.class)
abstract class WeatherServiceTestBase {

    protected static final String API_KEY = "key123";
    protected static final String BASE_URL = "https://api.test";
    protected static final String UNITS = "metric";

    @Mock
    protected WeatherRepository weatherRepository;

    @Mock
    protected RestClient restClient;

    @InjectMocks
    protected WeatherService weatherService;

    @BeforeEach
    void configureWeatherService() {
        ReflectionTestUtils.setField(weatherService, "apiKey", API_KEY);
        ReflectionTestUtils.setField(weatherService, "baseUrl", BASE_URL);
        ReflectionTestUtils.setField(weatherService, "units", UNITS);
    }
}