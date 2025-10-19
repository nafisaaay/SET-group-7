package no.hiof.setgroup7.controller;

import io.javalin.http.Context;
import no.hiof.setgroup7.DTOs.TripRequest;
import no.hiof.setgroup7.DTOs.TripInputDTO;
import no.hiof.setgroup7.model.From;
import no.hiof.setgroup7.model.To;
import no.hiof.setgroup7.repository.TripRepository;
import no.hiof.setgroup7.service.TripService;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;



public class TripController {
    TripRepository tripRepository;
    TripRequest tripRequest;
    TripInputDTO tripInputDTO;
    TripValidator tripValidator = new TripValidator();
    TripService tripService;

    public TripController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }





    public void getTripFormData(Context context) {
        context.result("Success, form data er mottatt");
        if(!tripValidator.isValid(context)) {
            return;
        }

        if (tripValidator.valid(context) != null) {
            tripInputDTO = tripValidator.valid(context);

            try {
                From fromObj = new From(tripInputDTO.getFromPlace(), tripInputDTO.getFrom());
                // System.out.println(fromObj.getPlace() + " " + fromObj.getName());
                To toObj = new To(tripInputDTO.getToPlace(), tripInputDTO.getTo());

                LocalDate localDate = LocalDate.parse(tripInputDTO.getDate());
                ZoneId zoneId = ZoneId.of("Europe/Oslo");
                LocalTime localTime = LocalTime.parse(tripInputDTO.getTime());
                ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, zoneId);
                String isoDateTime = zonedDateTime.toOffsetDateTime().toString(); // Den skal vi bruke i
                TripRequest tripRequest = new TripRequest(fromObj, toObj, isoDateTime);

                tripService.getTrip(tripRequest);
                //System.out.println(tripService.getTrip(tripRequest));
                //System.out.println(tripRequest.getFrom().getPlace());
                //System.out.println(tripRequest.getNumTripPatterns());
                //System.out.println(tripRequest.getDateTime());


            } catch (Exception e) {
                System.out.println(e);
            }
        }





    }





}
