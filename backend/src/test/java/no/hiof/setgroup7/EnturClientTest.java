package no.hiof.setgroup7;

import no.hiof.setgroup7.DTOs.EnturResponse;
import no.hiof.setgroup7.DTOs.TripRequest;
import no.hiof.setgroup7.integration.EnturClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EnturClientTest {

    @Mock
    TripRequest mockTripRequest;

    @Mock
    EnturResponse mockEnturResponse;

    @Mock
    EnturClient mockEnturClient;

    @Test
    public void testEnturClientNoResponse(){

    }
    @Test
    public void testEnturClientResponse(){

    }
}
