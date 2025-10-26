package no.hiof.setgroup7.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class Trip {
    private From from;
    private To to;
    private final int numTripPatterns = 3;
    private ZonedDateTime dateTime;

    public Trip(From from, To to, ZonedDateTime dateTime) {
        this.from = from;
        this.to = to;
        this.dateTime = dateTime;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public To getTo() {
        return to;
    }

    public void setTo(To to) {
        this.to = to;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getNumTripPatterns() {
        return numTripPatterns;
    }
}
