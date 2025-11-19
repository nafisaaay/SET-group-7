package no.hiof.setgroup7.Unittesting;

import io.javalin.http.Context;
import no.hiof.setgroup7.DTOs.TripInputDTO;
import no.hiof.setgroup7.DTOs.TripRequest;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.controller.TripController;
import no.hiof.setgroup7.controller.TripValidator;
import no.hiof.setgroup7.repository.TripRepository;
import no.hiof.setgroup7.service.TripService;
import no.hiof.setgroup7.ticketsys.model.Customer;
import no.hiof.setgroup7.ticketsys.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripControllerTest {

    @Mock
    TripRepository mockTripRepository;

    @Mock
    TripValidator mockTripValidator;

    @Mock
    TripService mockTripService;

    @Mock
    TicketService mockTicketService;

    @Mock
    Customer mockCustomer;

    @Mock
    Context mockContext;

    @Mock
    TripInputDTO mockTripInputDTO;

    @Mock
    TripResponse mockTripResponse;

    @InjectMocks
    TripController tripController;

    @BeforeEach
    void setup() {
        tripController = new TripController(mockTripService, mockCustomer, mockTicketService);
        tripController.tripValidator = mockTripValidator;
    }

    //test dersom brukeren ikke legger inn nødvendig informasjon
    @Test
    public void getTripFormDataInvalidData() {
        when(mockTripValidator.isValid(mockContext)).thenReturn(false);
        when(mockContext.status(400)).thenReturn(mockContext);

        tripController.getTripFormData(mockContext);

        verify(mockContext).status(400);
        verify(mockContext).result(contains("Vennligst fyll ut skjemaet først: (eg. from, to, date osv...)"));
        verifyNoInteractions(mockTripService);
    }

    //test dersom brukeren legger inn nødvendig informasjon
    @Test
    public void getTripFormDataValidData() {
        when(mockTripValidator.isValid(mockContext)).thenReturn(true);
        when(mockContext.bodyAsClass(TripInputDTO.class)).thenReturn(mockTripInputDTO);
        when(mockTripService.sendResponseToController()).thenReturn(mockTripResponse);


        when(mockTripInputDTO.getFromPlace()).thenReturn("Oslo s");
        when(mockTripInputDTO.getFrom()).thenReturn("1");
        when(mockTripInputDTO.getToPlace()).thenReturn("Halden Remmen");
        when(mockTripInputDTO.getTo()).thenReturn("2");
        when(mockTripInputDTO.getPerson()).thenReturn("voksen");
        when(mockTripInputDTO.getDate()).thenReturn("2025-11-08");
        when(mockTripInputDTO.getTime()).thenReturn("12:00");

        tripController.getTripFormData(mockContext);

        verify(mockTripValidator).isValid(mockContext);
        verify(mockTripService).getTrip(any(TripRequest.class));
        verify(mockTripService).sendResponseToController();
        verify(mockTicketService).setTripResponse(mockTripResponse);
        verify(mockContext).status(200);
        verify(mockContext).json(mockTripResponse);
    }
    //tester når brukerinput er riktig, men man ikke finner none som passer 
    @Test
    public void getTripFormDataResponseIsNull() {
        when(mockTripValidator.isValid(mockContext)).thenReturn(true);
        when(mockContext.bodyAsClass(TripInputDTO.class)).thenReturn(mockTripInputDTO);
        when(mockTripService.sendResponseToController()).thenReturn(null);
        when(mockContext.status(404)).thenReturn(mockContext);


        when(mockTripInputDTO.getFromPlace()).thenReturn("Oslo");
        when(mockTripInputDTO.getFrom()).thenReturn("1");
        when(mockTripInputDTO.getToPlace()).thenReturn("Halden");
        when(mockTripInputDTO.getTo()).thenReturn("2");
        when(mockTripInputDTO.getPerson()).thenReturn("voksen");
        when(mockTripInputDTO.getDate()).thenReturn("2025-11-08");
        when(mockTripInputDTO.getTime()).thenReturn("12:00");

        tripController.getTripFormData(mockContext);

        verify(mockContext).status(404);
        verify(mockContext).result(contains("Ingen data returnert fra Entur API'et!"));
    }
}