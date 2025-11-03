package no.hiof.setgroup7.DTOs;

import no.hiof.setgroup7.model.Leg;
import no.hiof.setgroup7.model.TripPattern;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public class TripResponse {
    private List<TripPattern> tripPatterns;
    private double customerPrice;

    public void setCustomerPrice(double customerPrice) {
        this.customerPrice = customerPrice;
    }

    public double getCustomerPrice() {
        return customerPrice;
    }

    public TripResponse(List<TripPattern> tripPatterns) {
        this.tripPatterns = tripPatterns;
    }

    public List<TripPattern> getTripPatterns() {
        return tripPatterns;
    }

    public TripPattern getTrips() {
        for (TripPattern trip : tripPatterns) {
            if (tripPatterns.size() > 0) {
                return trip;
            }
        }

        return null;
    }


    @Override
    public String toString() {
        return "TripResponse{" +
                "tripPatterns=" + tripPatterns + "Price= " + customerPrice + "kr" +
                '}';
    }
}
