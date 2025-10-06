package no.hiof.setgroup7.service;

import no.hiof.setgroup7.DTO.TripRequest;
import no.hiof.setgroup7.DTO.TripResponse;

public interface TripService {
    TripResponse getTrip(TripRequest request);
}
