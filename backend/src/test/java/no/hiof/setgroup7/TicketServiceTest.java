package no.hiof.setgroup7;

import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.model.Leg;
import no.hiof.setgroup7.model.Line;
import no.hiof.setgroup7.model.TripPattern;
import no.hiof.setgroup7.ticketsys.model.Customer;
import no.hiof.setgroup7.ticketsys.service.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    TripResponse mockTripResponse;

    @Mock
    TripResponse mockTripResponse2;

    @Mock
    Leg mockLeg1;
    @Mock
    Line mockLine1;

    @Mock
    Leg mockLeg2;
    @Mock
    Line mockLine2;

    @Mock
    Leg mockLeg3;

    @Mock
    TripPattern mockTripPattern;

    @Mock
    Customer mockCustomer;


    @Test
    public void calculateDistanceSucsessfully(){
        TicketService ticketService = new TicketService();
        when(mockLeg1.getLine()).thenReturn(mockLine1);
        when(mockLeg1.getDistance()).thenReturn(5000.0);

        lenient().when(mockLeg2.getDistance()).thenReturn(2000.0);

        when(mockLeg3.getLine()).thenReturn(mockLine2);
        when(mockLeg3.getDistance()).thenReturn(3000.0);



        when(mockTripPattern.getLegs()).thenReturn(List.of(mockLeg1, mockLeg2, mockLeg3));
        when(mockTripResponse.getTrips()).thenReturn(mockTripPattern);

        ticketService.setTripResponse(mockTripResponse);

        int distanceKm = ticketService.calculateDistance();


        Assertions.assertEquals(8.0, distanceKm);
    }

@Test
public void calculateFinalPriceUnderAndOverThreshold() {
    TicketService ticketService = new TicketService();

    //Under 20 km
    when(mockLeg1.getLine()).thenReturn(mockLine1);
    when(mockLeg1.getDistance()).thenReturn(5000.0);
    when(mockTripPattern.getLegs()).thenReturn(List.of(mockLeg1));
    when(mockTripResponse.getTrips()).thenReturn(mockTripPattern);
    when(mockCustomer.getBasePrice()).thenReturn(20);

    ticketService.setTripResponse(mockTripResponse);
    ticketService.setCustomer(mockCustomer);

    double finalPriceUnder = ticketService.calculateDistancePrice();
    Assertions.assertEquals(20.0, finalPriceUnder);

    //Over 20 km
    when(mockLeg2.getLine()).thenReturn(mockLine2);
    when(mockLeg2.getDistance()).thenReturn(21000.0);
    when(mockTripPattern.getLegs()).thenReturn(List.of(mockLeg2));
    when(mockTripResponse.getTrips()).thenReturn(mockTripPattern);

    double finalPriceOver = ticketService.calculateDistancePrice();
    Assertions.assertEquals(51.5, finalPriceOver);
}

}
