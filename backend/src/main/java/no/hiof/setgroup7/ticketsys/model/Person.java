package no.hiof.setgroup7.ticketsys.model;
import no.hiof.setgroup7.controller.TripController;
import no.hiof.setgroup7.ticketsys.TicketPrice;

public abstract class Person implements TicketPrice {
    protected final Integer age;
    public TripController tripController;

protected Person(Integer age) {
    this.age = age;
}

    public Integer getAge() {
        return age;
    }

    public TripController getTripController() {
        return tripController;
    }
}
