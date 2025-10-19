package no.hiof.setgroup7.service;

import no.hiof.setgroup7.DTOs.TripRequest;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.controller.TripController;
import no.hiof.setgroup7.integration.EnturClient;

public class TripService {
    private final EnturClient enturClient;

    public TripService(EnturClient enturClient) {
        this.enturClient = enturClient;
    }

    public TripRequest getTrip(TripRequest tripRequest) {
        enturClient.getDataFromService(tripRequest);
        return tripRequest;
    }


}
