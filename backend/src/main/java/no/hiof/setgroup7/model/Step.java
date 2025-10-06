package no.hiof.setgroup7.model;

public class Step {
    private double distance;
    private String heading;
    private String streetName;
    private boolean stayOn;
    private String relativeDirection;

    public Step() {

    }

    public Step(double distance, String heading, String streetName, boolean stayOn, String relativeDirection) {
        this.distance = distance;
        this.heading = heading;
        this.streetName = streetName;
        this.stayOn = stayOn;
        this.relativeDirection = relativeDirection;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public boolean isStayOn() {
        return stayOn;
    }

    public void setStayOn(boolean stayOn) {
        this.stayOn = stayOn;
    }

    public String getRelativeDirection() {
        return relativeDirection;
    }

    public void setRelativeDirection(String relativeDirection) {
        this.relativeDirection = relativeDirection;
    }
}
