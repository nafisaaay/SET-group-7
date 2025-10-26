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

    /*
     - Validerer data mottatt fra frontend.
     - Mapper body til TripInputDTO og sjekker at alle påkrevde felter er utfylt.
    */
    public boolean isValid(Context context) {

        tripInputDTO = context.bodyAsClass(TripInputDTO.class);  // Vi mapper dataen fra frontend til tripInputDTO klassen for validering

        // Så sjekker om input-feltene er tomme og returnerer enten false eller true avhengig av verdiene av input-feltene

        if(isNullOrEmpty(tripInputDTO.getFromPlace()) || isNullOrEmpty(tripInputDTO.getToPlace()) ||
                isNullOrEmpty(tripInputDTO.getDate()) || isNullOrEmpty(tripInputDTO.getTime())) {
            return false;
        }


        else {
            return true;
        }
    }


    public Boolean isNullOrEmpty(String value) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return false;
    }

}
