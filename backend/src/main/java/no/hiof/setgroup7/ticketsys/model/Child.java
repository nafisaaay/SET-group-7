package no.hiof.setgroup7.ticketsys.model;
import no.hiof.setgroup7.ticketsys.TicketPrice;

import java.util.Scanner;

public class Child extends Person implements TicketPrice  {

    public Child(int age, int price) {
        super(age, price);
    }

    public Child(int age) {
        super(age);
    }

    @Override
    public int basePrice(){
        if (age > 5 && age < 18) {
            price = 15;
        } else if (age < 5) {
            price = 0;
        } else {
            System.err.println("Error! Age is not within range of 0 - 17.");
        }
        return price;
    }
}
