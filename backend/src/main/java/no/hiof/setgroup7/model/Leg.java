package no.hiof.setgroup7.model;

import java.time.ZonedDateTime;
import java.util.List;

public class Leg {

    private String mode;
    private double distance;
    private String expectedEndTime;
    private String expectedStartTime;
    private FromPlace fromPlace;
    private ToPlace toPlace;
    private Line line;
    private List<Step> steps;


    public Leg() {

    }

    public Leg(String mode, double distance, String expectedEndTime, String expectedStartTime, FromPlace fromPlace, ToPlace toPlace, Line line, List<Step> steps) {
        this.mode = mode;
        this.distance = distance;
        this.expectedEndTime = expectedEndTime;
        this.expectedStartTime = expectedStartTime;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.line = line;
        this.steps = steps;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getExpectedEndTime() {
        return expectedEndTime;
    }

    public void setExpectedEndTime(String expectedEndTime) {
        this.expectedEndTime = expectedEndTime;
    }

    public String getExpectedStartTime() {
        return expectedStartTime;
    }

    public void setExpectedStartTime(String expectedStartTime) {
        this.expectedStartTime = expectedStartTime;
    }

    public FromPlace getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(FromPlace fromPlace) {
        this.fromPlace = fromPlace;
    }

    public ToPlace getToPlace() {
        return toPlace;
    }

    public void setToPlace(ToPlace toPlace) {
        this.toPlace = toPlace;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
