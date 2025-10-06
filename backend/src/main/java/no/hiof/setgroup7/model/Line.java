package no.hiof.setgroup7.model;

public class Line {
    private String transportMode;
    private String name;

    public Line() {

    }

    public Line(String transportMode, String name) {
        this.transportMode = transportMode;
        this.name = name;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
