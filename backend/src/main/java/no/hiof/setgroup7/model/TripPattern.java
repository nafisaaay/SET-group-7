package no.hiof.setgroup7.model;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public class TripPattern {
    private int duration;
    private List<Leg> legs;
    private String endTime;
    private String startTime;

    public TripPattern() {

    }

    public TripPattern(int duration, List<Leg> legs, String startTime, String endTime) {
        this.duration = duration;
        this.legs = legs;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public Leg getLeg() {
        for (Leg leg : getLegs()) {
            if (getLegs().size() >= 0) {
                return leg;
            }
        }
        return null;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "TripPattern{" +
                "duration=" + duration +
                ", legs=" + legs +
                ", endTime='" + endTime + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
