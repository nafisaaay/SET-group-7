package no.hiof.setgroup7.controller;

import io.javalin.http.Context;
import no.hiof.setgroup7.DTO.TripRequest;
import no.hiof.setgroup7.repository.TripRepository;

public class TripController {
    TripRepository tripRepository;

    public TripController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public TripController() {

    }

    public void getTrip(Context context) {
        context.result("Success, form data er mottatt");
        context.status(200).result("HEI, FRA JAVALIN SERVER");

    }


}
