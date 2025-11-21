package no.hiof.setgroup7.Unittesting;

import no.hiof.setgroup7.DTOs.TripRequest;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.integration.EnturClient;
import no.hiof.setgroup7.model.From;
import no.hiof.setgroup7.model.To;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnturClientTest {

    @Mock
    TripRequest mockTripRequest;

    @Mock
    From mockFrom;

    @Mock
    To mockTo;

    EnturClient enturClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enturClient = new EnturClient();

        when(mockTripRequest.getFrom()).thenReturn(mockFrom);
        when(mockTripRequest.getTo()).thenReturn(mockTo);
    }

    @Test
    public void testEnturClientNoResponse() {
        when(mockFrom.getPlace()).thenReturn(null);
        when(mockFrom.getName()).thenReturn(null);
        when(mockTo.getPlace()).thenReturn(null);
        when(mockTo.getName()).thenReturn(null);

        enturClient.getDataFromService(mockTripRequest);
        TripResponse result = enturClient.sendRequest();

        assertNull(result, "TripResponse skal være null når Entur ikke returnerer data");
    }

    @Test
    public void testEnturClientResponse() throws Exception {
        when(mockFrom.getPlace()).thenReturn("Remmen Høgskole");
        when(mockTo.getPlace()).thenReturn("Oslo s");
        when(mockTripRequest.getFrom()).thenReturn(mockFrom);
        when(mockTripRequest.getTo()).thenReturn(mockTo);
        when(mockTripRequest.getNumTripPatterns()).thenReturn(1);
        when(mockTripRequest.getDateTime()).thenReturn("2025-11-10T08:00:00");


        String fakeJson = """
    {
      "data": {
        "trip": {
          "tripPatterns": [
            { "duration": 3600, "legs": [] }
          ]
        }
      }
    }
    """;

        HttpClient mockClient = mock(HttpClient.class);
        HttpResponse mockResponse = mock(HttpResponse.class);
        when(mockResponse.body()).thenReturn(fakeJson);
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);


        try (MockedStatic<HttpClient> httpClientStaticMock = mockStatic(HttpClient.class)) {
            httpClientStaticMock.when(HttpClient::newHttpClient).thenReturn(mockClient);

            EnturClient enturClient = new EnturClient();
            enturClient.getDataFromService(mockTripRequest);

            TripResponse result = enturClient.sendRequest();

            assertNotNull(result);
            Assertions.assertEquals(3600, result.getTripPatterns().get(0).getDuration());
        }
    }
}
