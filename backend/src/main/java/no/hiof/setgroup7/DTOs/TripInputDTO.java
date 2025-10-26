package no.hiof.setgroup7.DTOs;

public class TripInputDTO {
    private String tripType;
    private String person;
    private String from;
    private String fromPlace;
    private String to;
    private String toPlace;
    private String date;
    private String time;

    public TripInputDTO() {
    }

    public TripInputDTO(String tripType, String person, String from, String fromPlace, String to, String toPlace, String date, String time) {
        this.tripType = tripType;
        this.person = person;
        this.from = from;
        this.fromPlace = fromPlace;
        this.to = to;
        this.toPlace = toPlace;
        this.date = date;
        this.time = time;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TripRequestExample{" +
                "tripType='" + tripType + '\'' +
                ", person='" + person + '\'' +
                ", from='" + from + '\'' +
                ", fromPlace='" + fromPlace + '\'' +
                ", to='" + to + '\'' +
                ", toPlace='" + toPlace + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
