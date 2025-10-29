package no.hiof.setgroup7.ticketsys.service;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.model.Leg;
import no.hiof.setgroup7.ticketsys.TicketPrice;


import java.util.ArrayList;
import java.util.List;

public class TicketService {

    // Beregning av distanse (uten gangavstand)
    public int getResponse(TripResponse tripResponse){
        List<Leg> listOfLegs = tripResponse.getTrips().getLegs();
        int distance = 0;
        for (Leg leg : listOfLegs) {
            if(leg.getLine() != null) {
                distance += leg.getDistance();
            }
        } int distanceKm = distance/1000;
        return distanceKm;
    }

    public double calculateDistancePrice(TripResponse tripResponse, TicketPrice customer) {
        int distanceKm = getResponse(tripResponse);

        // Hent basePrice via interfacet
        double basePrice = customer.basePrice();

        double pricePerKm = 0.95;
        int thresholdKm = 20;

        if (distanceKm < thresholdKm) {
            return basePrice;
        } else {
            double distancePrice = pricePerKm * distanceKm;
            return basePrice + distancePrice;
        }
    }

}


