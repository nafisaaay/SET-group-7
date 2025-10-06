package no.hiof.setgroup7.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class Trip {
    private List<Map<String, String>> from;
    private List<Map<String, String>> to;
    private final int numTripPatterns = 3;
    private ZonedDateTime dateTime;

    public Trip(List<Map<String, String>> from, List<Map<String, String>> to, ZonedDateTime dateTime) {
        this.from = from;
        this.to = to;
        this.dateTime = dateTime;
    }

    public List<Map<String, String>> getFrom() {
        return from;
    }

    public void setFrom(List<Map<String, String>> from) {
        this.from = from;
    }

    public List<Map<String, String>> getTo() {
        return to;
    }

    public void setTo(List<Map<String, String>> to) {
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
