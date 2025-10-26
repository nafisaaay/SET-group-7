package no.hiof.setgroup7.service;

import io.javalin.http.Context;
import no.hiof.setgroup7.DTOs.TripRequest;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.controller.TripController;
import no.hiof.setgroup7.integration.EnturClient;

public class TripService {
    private final EnturClient enturClient;

    public TripService(EnturClient enturClient) {
        this.enturClient = enturClient;
    }

    /*
      Mottar TripRequest fra controller og sender det videre til EnturClient for behandling.
    */
    public TripRequest getTrip(TripRequest tripRequest) {
        enturClient.getDataFromService(tripRequest);
        return tripRequest;
    }

    /*
     Henter responsen fra EnturClient og sender den tilbake til controller.
    */
    public TripResponse sendResponseToController() {
        return enturClient.sendRequest();
    }




}
