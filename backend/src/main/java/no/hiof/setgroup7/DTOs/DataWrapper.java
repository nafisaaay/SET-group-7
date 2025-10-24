package no.hiof.setgroup7.DTOs;

public class DataWrapper {
    private TripResponse trip;

    public DataWrapper(TripResponse trip) {
        this.trip = trip;
    }

    public DataWrapper() {

    }

    public TripResponse getTrip() {
        return trip;
    }


}
