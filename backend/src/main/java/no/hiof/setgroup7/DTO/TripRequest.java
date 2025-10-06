package no.hiof.setgroup7.DTO;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class TripRequest {
    private final List<Map<String, String>> from;
    private final List<Map<String, String>> to;
    private final int numTripPatterns = 3;
    private final ZonedDateTime dateTime;

    public TripRequest(List<Map<String, String>> from, List<Map<String, String>> to, ZonedDateTime dateTime) {
        this.from = from;
        this.to = to;
        this.dateTime = dateTime;
    }

    public List<Map<String, String>> getFrom() {
        return from;
    }

    public List<Map<String, String>> getTo() {
        return to;
    }

    public int getNumTripPatterns() {
        return numTripPatterns;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }
}
