package no.hiof.setgroup7.DTOs;

import no.hiof.setgroup7.model.From;
import no.hiof.setgroup7.model.To;

import java.time.ZonedDateTime;

public class TripRequest {
    public final From from;
    public final To to;
    private final int numTripPatterns = 3;
    private final String dateTime;

    public TripRequest(From from, To to, String dateTime) {
        this.from = from;
        this.to = to;
        this.dateTime = dateTime;
    }

    public From getFrom() {
        return from;
    }

    public To getTo() {
        return to;
    }

    public int getNumTripPatterns() {
        return numTripPatterns;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "TripRequest{" +
                "from=" + from +
                ", to=" + to +
                ", numTripPatterns=" + numTripPatterns +
                ", dateTime=" + dateTime +
                '}';
    }
}
