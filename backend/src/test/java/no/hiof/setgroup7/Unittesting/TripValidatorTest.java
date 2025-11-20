package no.hiof.setgroup7.Unittesting;

import io.javalin.http.Context;
import no.hiof.setgroup7.DTOs.TripInputDTO;
import no.hiof.setgroup7.controller.TripValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TripValidatorTest {
    private final TripValidator underTest = new TripValidator();

    // Lager et mock av context fra javalin serveren for å senere kunne sende inn i isValid metoden
    @Mock
    Context mockContext;

    @Test
    void shouldReturnTrueIfInputIsNotEmpty() {
        // arrange

        // Mocker tripInputDTO klassen som er ment for å ta imot data fra frontend
        TripInputDTO mockTripInputDTO = Mockito.mock(TripInputDTO.class);
        mockContext = Mockito.mock(Context.class);
        Mockito.when(mockContext.bodyAsClass(TripInputDTO.class)).thenReturn(mockTripInputDTO);

        // Eks. av input verdier fra bruker
        String fromPlace = "Oslo S, Oslo";
        String toPlace = "Remmen Høgskolen, Halden";
        String date = "2025-10-29";
        String time = "12:00";

        // Her forteller vi mockTripInputDTO å returnere input verdiene ovenfor 👆 hvis for eksempel getFromPlace blir kalt
        Mockito.when(mockTripInputDTO.getFromPlace()).thenReturn(fromPlace);
        Mockito.when(mockTripInputDTO.getToPlace()).thenReturn(toPlace);
        Mockito.when(mockTripInputDTO.getDate()).thenReturn(date);
        Mockito.when(mockTripInputDTO.getTime()).thenReturn(time);

        // act

        Boolean result = underTest.isValid(mockContext);  // lagrer resultatet fra isValid metoden

        // assert

        Assertions.assertTrue(result, "False");
    }


    // Forventer falsen ettersom vi antar at et input verdi er tom.
    @Test
    void shouldReturnFalseIfInputIsEmpty() {
        // arrange
        TripInputDTO mockTripInputDTO = Mockito.mock(TripInputDTO.class);
        mockContext = Mockito.mock(Context.class);
        Mockito.when(mockContext.bodyAsClass(TripInputDTO.class)).thenReturn(mockTripInputDTO);


        String fromPlace = "Oslo S, Oslo";
        String toPlace = "Remmen Høgskolen, Halden";
        String date = "2025-10-29";
        String time = "";

        Mockito.when(mockTripInputDTO.getFromPlace()).thenReturn(fromPlace);
        Mockito.when(mockTripInputDTO.getToPlace()).thenReturn(toPlace);
        Mockito.when(mockTripInputDTO.getDate()).thenReturn(date);
        Mockito.when(mockTripInputDTO.getTime()).thenReturn(time);

        // act

        Boolean result = underTest.isValid(mockContext);

        // assert

        Assertions.assertFalse(result, "False");
    }


    // I denne metoden forventer vi å få false ved å late som et input verdi er null
    @Test
    void shouldReturnFalseIfInputIsNull() {
        // arrange
        TripInputDTO mockTripInputDTO = Mockito.mock(TripInputDTO.class);
        mockContext = Mockito.mock(Context.class);
        Mockito.when(mockContext.bodyAsClass(TripInputDTO.class)).thenReturn(mockTripInputDTO);


        String fromPlace = "Oslo S, Oslo";
        String toPlace = "Remmen Høgskolen, Halden";
        String date = "2025-10-29";
        String time = null;

        Mockito.when(mockTripInputDTO.getFromPlace()).thenReturn(fromPlace);
        Mockito.when(mockTripInputDTO.getToPlace()).thenReturn(toPlace);
        Mockito.when(mockTripInputDTO.getDate()).thenReturn(date);
        Mockito.when(mockTripInputDTO.getTime()).thenReturn(time);

        // act

        Boolean result = underTest.isValid(mockContext);

        // assert

        Assertions.assertFalse(result, "False");
    }
}
