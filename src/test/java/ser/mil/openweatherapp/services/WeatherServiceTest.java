package ser.mil.openweatherapp.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import ser.mil.openweatherapp.models.Weather;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest extends WeatherServiceTestBase {

    @Test
    void shouldReturnCachedWeatherIfRecent() {
        // given
        var recent = new Weather("1", "Warsaw",
                OffsetDateTime.now(ZoneOffset.UTC).minusMinutes(30),
                20.0, 1000.0, 3.0);
        when(weatherRepository.findLastForCity("Warsaw")).thenReturn(Optional.of(recent));

        // when
        var result = weatherService.fetchAndStore("Warsaw");

        // then
        assertEquals(recent, result);
        verify(weatherRepository, never()).save(any());
        verifyNoInteractions(restClient);
    }

//    @Test
//    void shouldFetchAndSaveWhenNoRecentData() {
//        // given
//        when(weatherRepository.findLastForCity("Gdansk")).thenReturn(Optional.empty());
//        when(builder.build()).thenReturn(restClient);
//        when(headersSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.body(String.class)).thenReturn("""
//                {"main":{"temp":15.5,"pressure":1012},"wind":{"speed":4.5}}
//                """);
//        when(weatherRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
//        setupConfig();
//
//        // when
//        var result = weatherService.fetchAndStore("Gdansk");
//
//        // then
//        assertEquals("Gdansk", result.cityName());
//        assertEquals(15.5, result.temp());
//        assertEquals(1012.0, result.presure());
//        assertEquals(4.5, result.windSpeed());
//        verify(weatherRepository, times(1)).save(any());
//    }
//
//    @Test
//    void shouldThrowWhenApiReturnsInvalidJson() {
//        // given
//        when(weatherRepository.findLastForCity("Lublin")).thenReturn(Optional.empty());
//        when(builder.build()).thenReturn(restClient);
//        when(headersSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.body(String.class)).thenReturn("invalid-json");
//        setupConfig();
//
//        // when / then
//        assertThrows(IllegalStateException.class,
//                () -> weatherService.fetchAndStore("Lublin"));
//        verify(weatherRepository, never()).save(any());
//    }
}
