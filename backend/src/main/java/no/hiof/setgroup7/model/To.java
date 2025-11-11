package no.hiof.setgroup7.model;

public class To {
    private String place;
    private String name;

    To () {

    }

    public To(String place, String name) {
        this.place = place;
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
