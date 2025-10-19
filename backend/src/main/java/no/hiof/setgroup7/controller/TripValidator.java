package no.hiof.setgroup7.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import no.hiof.setgroup7.DTOs.TripInputDTO;
import no.hiof.setgroup7.DTOs.TripRequest;
import no.hiof.setgroup7.model.From;
import no.hiof.setgroup7.model.To;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TripValidator {

    TripInputDTO tripInputDTO;

    public boolean isValid(Context context) {

        tripInputDTO = context.bodyAsClass(TripInputDTO.class);


        if (tripInputDTO.getFrom().isEmpty() || tripInputDTO.getFromPlace().isEmpty() ||
        tripInputDTO.getTo().isEmpty() || tripInputDTO.getToPlace().isEmpty() ||
        tripInputDTO.getDate().isEmpty() || tripInputDTO.getTime().isEmpty()) {
            context.status(HttpStatus.BAD_REQUEST)
                    .result("Error: missing required parameters (eg. from, fromPlace etc...)");
            return false;
        }

        else {
            System.out.println("Trip request is valid and ready to process!");
            return true;
        }
    }

    public TripInputDTO valid(Context context) {
        if (isValid(context)) {
            tripInputDTO = context.bodyAsClass(TripInputDTO.class);
            return tripInputDTO;
        }
        return null;
    }


}
