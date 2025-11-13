package ser.mil.openweatherapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClient;
import ser.mil.openweatherapp.repositories.WeatherRepository;
import ser.mil.openweatherapp.services.WeatherService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

class WeatherControllerIntegrationTest extends PostgresIntegrationTestBase {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherRepository weatherRepository;

    @MockitoBean
    RestClient.Builder builder;

    @MockitoBean
    RestClient restClient;

    @MockitoBean
    RestClient.RequestHeadersUriSpec<?> uriSpec;

    @MockitoBean
    RestClient.RequestHeadersSpec<?> headersSpec;

    @MockitoBean
    RestClient.ResponseSpec responseSpec;

    @BeforeEach
    void configureWeatherService() {
        ReflectionTestUtils.setField(weatherService, "apiKey", "key123");
        ReflectionTestUtils.setField(weatherService, "baseUrl", "https://api.test");
        ReflectionTestUtils.setField(weatherService, "units", "metric");


        when(builder.build()).thenReturn(restClient);
        when(restClient.get()).thenReturn((RestClient.RequestHeadersUriSpec) uriSpec);

        when(((RestClient.RequestHeadersUriSpec) uriSpec)
                .uri(anyString()))
                .thenReturn((RestClient.RequestHeadersSpec) headersSpec);

        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(String.class)).thenReturn("""
                {"main":{"temp":15.5,"pressure":1012},"wind":{"speed":4.5}}
                """);
    }

    @Test
    void shouldFetchFromApiAndSaveToPostgresWhenNoRecentData() {
        // given
        when(weatherRepository.findLastForCity("Gdansk")).thenReturn(Optional.empty());

        // when
        var result = weatherService.fetchAndStore("Gdansk");

        // then
        assertEquals("Gdansk", result.cityName());
        assertEquals(15.5, result.temp());
        assertEquals(1012.0, result.presure());
        assertEquals(4.5, result.windSpeed());

        var saved = weatherRepository.findLastForCity("Gdansk");
        assertTrue(saved.isPresent());
        assertEquals("Gdansk", saved.get().cityName());
    }
}
