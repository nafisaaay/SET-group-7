package no.hiof.setgroup7.controller;

import io.javalin.http.Context;
import no.hiof.setgroup7.DTOs.TripRequest;
import no.hiof.setgroup7.DTOs.TripInputDTO;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.model.From;
import no.hiof.setgroup7.model.To;
import no.hiof.setgroup7.repository.TripRepository;
import no.hiof.setgroup7.service.TripService;
import no.hiof.setgroup7.ticketsys.model.Customer;
import no.hiof.setgroup7.ticketsys.service.TicketService;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;



public class TripController {
    TripRepository tripRepository;
    TripRequest tripRequest;
    TripInputDTO tripInputDTO;
    public TripValidator tripValidator = new TripValidator();
    TripService tripService;
    TripResponse tripResponse;
    Customer customer;
    TicketService ticketService;

    public TripController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public TripController(TripService tripService, Customer customer, TicketService ticketService) {
        this.tripService = tripService;
        this.customer = customer;
        this.ticketService = ticketService;
    }

    /*
     - Tar imot og validerer data fra frontend (Trip-form).
     - Hvis dataen er gyldig, mappes den til et TripRequest-objekt og sendes videre til TripService.
    */

    public void getTripFormData(Context context) {

        if (!tripValidator.isValid(context)) {
            System.out.println("Tomme input-felter!");
            context.status(400)
                    .result("Vennligst fyll ut skjemaet først: (eg. from, to, date osv...)");
            return;
        }

        else {
            try {
                tripInputDTO = context.bodyAsClass(TripInputDTO.class);
                From fromObj = new From(tripInputDTO.getFromPlace(), tripInputDTO.getFrom());
                To toObj = new To(tripInputDTO.getToPlace(), tripInputDTO.getTo());
                customer.setAgeGroup(tripInputDTO.getPerson());
                ticketService.setCustomer(customer);

                LocalDate localDate = LocalDate.parse(tripInputDTO.getDate());
                ZoneId zoneId = ZoneId.of("Europe/Oslo");
                LocalTime localTime = LocalTime.parse(tripInputDTO.getTime());
                ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, zoneId);
                String isoDateTime = zonedDateTime.toOffsetDateTime().toString();

                // Sender data videre til service-laget
                TripRequest tripRequest = new TripRequest(fromObj, toObj, isoDateTime);
                tripService.getTrip(tripRequest);


            } catch (Exception e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }

        }

        /*
            Tar imot TripResponse fra service-laget og sender det tilbake til frontend som JSON.
        */

        try {
            tripResponse = tripService.sendResponseToController();

            if (tripResponse == null) {
                context.status(404).result("Ingen data returnert fra Entur API'et!");
                return;
            }

            else {
                context.status(200);

                ticketService.setTripResponse(tripResponse);
                System.out.println(ticketService.calculateDistance());
                System.out.println(ticketService.calculateDistancePrice());
                System.out.println(tripResponse.toString());
                context.json(tripResponse);

                return;
            }
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
    }

    public void getResponse(TripResponse tripResponse) {
        this.tripResponse = tripResponse;
    }

}
