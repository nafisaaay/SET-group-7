package no.hiof.setgroup7;

import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.model.Leg;
import no.hiof.setgroup7.model.Line;
import no.hiof.setgroup7.model.TripPattern;
import no.hiof.setgroup7.ticketsys.service.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    TripResponse mockTripResponse;

    @Mock
    Leg mockLeg1;
    @Mock
    Line mockLine1;

    @Mock
    Leg mockLeg2;


    @Mock
    Leg mockLeg3;
    @Mock
    Line mockLine2;

    @Mock
    TripPattern mockTripPattern;


    @Test
    public void calculateDistanceSucsessfully(){
        TicketService ticketService = new TicketService();
        when(mockLeg1.getLine()).thenReturn(mockLine1);
        when(mockLeg1.getDistance()).thenReturn(5000.0);

        when(mockLeg2.getLine()).thenReturn(null); // skal ikke regnes med
        when(mockLeg2.getDistance()).thenReturn(2000.0);

        when(mockLeg3.getLine()).thenReturn(mockLine2);
        when(mockLeg3.getDistance()).thenReturn(3000.0);



        when(mockTripPattern.getLegs()).thenReturn(List.of(mockLeg1, mockLeg2, mockLeg3));
        when(mockTripResponse.getTrips()).thenReturn(mockTripPattern);

        ticketService.setTripResponse(mockTripResponse);

        int distanceKm = ticketService.calculateDistance();


        Assertions.assertEquals(8.0, distanceKm);
    }

   /** @Test
    public void calculateFinalPriceSucsessfully(){
        TicketService ticketService = new TicketService();

    }
   **/


}
