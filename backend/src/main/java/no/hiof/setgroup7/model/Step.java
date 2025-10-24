package no.hiof.setgroup7.model;

public class Step {
    private Double distance;
    private String heading;
    private String streetName;
    private Boolean stayOn;
    private String relativeDirection;
    private Double latitude;
    private Double longitude;

    public Step() {

    }

    public Step(Double distance, String heading, String streetName, Boolean stayOn, String relativeDirection, Double latitude, Double longitude) {
        this.distance = distance;
        this.heading = heading;
        this.streetName = streetName;
        this.stayOn = stayOn;
        this.relativeDirection = relativeDirection;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
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

    public Boolean getStayOn() {
        return stayOn;
    }

    public void setStayOn(Boolean stayOn) {
        this.stayOn = stayOn;
    }

    public String getRelativeDirection() {
        return relativeDirection;
    }

    public void setRelativeDirection(String relativeDirection) {
        this.relativeDirection = relativeDirection;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
