package no.hiof.setgroup7.ticketsys.service;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.model.Leg;
import no.hiof.setgroup7.ticketsys.model.Customer;


import java.util.List;

public class TicketService {

    private TripResponse tripResponse;
    private Customer customer;

    public void setTripResponse(TripResponse tripResponse){
        this.tripResponse = tripResponse;
    }

    // Beregning av distanse (uten gangavstand)
    public int calculateDistance(){
        List<Leg> listOfLegs = tripResponse.getTrips().getLegs();
        int distance = 0;
        for (Leg leg : listOfLegs) {
            if(leg.getLine() != null) {
                distance += leg.getDistance();
            }
        } int distanceKm = distance/1000;
        return distanceKm;
    }

    public double calculateDistancePrice() {
        int distanceKm = calculateDistance();
        double finalPrice;

        double basePrice = customer.calculateBasePrice();

        double pricePerKm = 0.95;
        int thresholdKm = 20;

        if (distanceKm < thresholdKm) {
            finalPrice = basePrice;
        } else {
            double distancePrice = pricePerKm * distanceKm;
            finalPrice =  basePrice + distancePrice;
        }
        tripResponse.setCustomerPrice(finalPrice);

        return finalPrice;
    }

}


