package no.hiof.setgroup7.DTO;

import no.hiof.setgroup7.model.Leg;
import no.hiof.setgroup7.model.TripPattern;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public class TripResponse {
    private List<TripPattern> tripPatterns;
    private Duration duration;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private List<Leg> legs;

    public TripResponse(List<TripPattern> tripPatterns, Duration duration, ZonedDateTime startTime, ZonedDateTime endTime, List<Leg> legs) {
        this.tripPatterns = tripPatterns;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.legs = legs;
    }

    public List<TripPattern> getTripPatterns() {
        return tripPatterns;
    }

    public void setTripPatterns(List<TripPattern> tripPatterns) {
        this.tripPatterns = tripPatterns;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }
}
