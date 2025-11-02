package no.hiof.setgroup7.ticketsys.service;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.controller.TripController;
import no.hiof.setgroup7.model.Leg;
import no.hiof.setgroup7.ticketsys.model.Customer;


import java.util.List;

public class TicketService {

    private TripResponse tripResponse;
    private Customer customer;
    private double finalPrice;

    public void setTripResponse(TripResponse tripResponse){
        this.tripResponse = tripResponse;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    // Beregning av distanse (uten gangavstand)
    public int calculateDistance(){
        List<Leg> listOfLegs = tripResponse.getTrips().getLegs();
        int distance = 0;
        for (Leg leg : listOfLegs) {
            if(leg.getLine() != null) {
                distance += leg.getDistance();
            }
        }

        int distanceKm = distance/1000;
        return distanceKm;
    }

    public double calculateDistancePrice() {
        int distanceKm = calculateDistance();
        double basePrice = customer.getBasePrice();

        double pricePerKm = 0.95;
        int thresholdKm = 20;

        if (distanceKm < thresholdKm) {
            finalPrice = basePrice;
            tripResponse.setCustomerPrice(finalPrice);
        } else {
            double distancePrice = pricePerKm * distanceKm;
            finalPrice =  basePrice + distancePrice;
            tripResponse.setCustomerPrice(finalPrice);
        }
        return finalPrice;
    }


}


