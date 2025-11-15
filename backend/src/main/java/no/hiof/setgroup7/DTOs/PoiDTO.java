package no.hiof.setgroup7.DTOs;


// DTO to be able to work with POIs. Contains constructors, getters and setters.
public class PoiDTO {
    private float latitude;
    private float longitude;
    private String placeName;
    private String placeType;
    private String city;
    private String area;

    public PoiDTO(float latitude, float longitude, String placeName, String placeType, String city, String area) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeName = placeName;
        this.placeType = placeType;
        this.city = city;
        this.area = area;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
