package ser.mil.openweatherapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ser.mil.openweatherapp.models.Weather;
import ser.mil.openweatherapp.repositories.WeatherRepository;

import java.time.OffsetDateTime;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
public class WeatherServiceIntegrationTest extends PostgresIntegrationTestBase {

    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    void shouldSaveWeatherWhenRequestIsValid() throws Exception {
        Weather weather = new Weather(
                "1",
                "Krk",
                OffsetDateTime.now(),
                10.5,
                1013.0,
                5.2
        );

        mockMvc.perform(post("/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(weather)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.weather.cityName", equalTo("Krk"))
                );

        List<Weather> saved = weatherRepository.findAll();

        assertEquals(1, saved.size());
        assertEquals("Krk", saved.getFirst().cityName());
    }


}