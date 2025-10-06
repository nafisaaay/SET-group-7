package no.hiof.setgroup7.model;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public class TripPattern {
    private Duration duration;
    private List<Leg> legs;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    public TripPattern() {

    }

    public TripPattern(Duration duration, List<Leg> legs, ZonedDateTime startTime, ZonedDateTime endTime) {
        this.duration = duration;
        this.legs = legs;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
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
}
