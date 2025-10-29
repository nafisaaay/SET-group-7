package no.hiof.setgroup7.ticketsys.model;
import no.hiof.setgroup7.controller.TripController;

public class Person {
    protected int age;
    protected int price;
    public TripController tripController;

    public Person(int age, int price) {
        this.age = age;
        this.price = price;
    }

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public TripController getTripController() {
        return tripController;
    }
}
