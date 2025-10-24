package no.hiof.setgroup7.model;

public class FromPlace {
    private String name;
    private Double latitude;
    private Double longitude;
    private String vertexType;

    public FromPlace(String name, Double latitude, Double longitude, String vertexType) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vertexType = vertexType;
    }

    public FromPlace() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getVertexType() {
        return vertexType;
    }

    public void setVertexType(String vertexType) {
        this.vertexType = vertexType;
    }
}
