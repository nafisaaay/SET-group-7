package no.hiof.setgroup7.model;

import java.time.ZonedDateTime;
import java.util.List;

public class Leg {
    private int distance;
    private ZonedDateTime expectedStartTime;
    private ZonedDateTime expectedEndTime;
    private String fromPlace;
    private String toPlace;
    private List<Line> lines;
    private List<Step> steps;

    public Leg() {

    }

    public Leg(int distance, ZonedDateTime expectedStartTime, ZonedDateTime expectedEndTime, String fromPlace, String toPlace, List<Line> lines, List<Step> steps) {
        this.distance = distance;
        this.expectedStartTime = expectedStartTime;
        this.expectedEndTime = expectedEndTime;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.lines = lines;
        this.steps = steps;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public ZonedDateTime getExpectedStartTime() {
        return expectedStartTime;
    }

    public void setExpectedStartTime(ZonedDateTime expectedStartTime) {
        this.expectedStartTime = expectedStartTime;
    }

    public ZonedDateTime getExpectedEndTime() {
        return expectedEndTime;
    }

    public void setExpectedEndTime(ZonedDateTime expectedEndTime) {
        this.expectedEndTime = expectedEndTime;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

}
