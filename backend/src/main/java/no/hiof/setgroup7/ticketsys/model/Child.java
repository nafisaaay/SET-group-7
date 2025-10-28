package no.hiof.setgroup7.ticketsys.model;
import no.hiof.setgroup7.ticketsys.TicketPrice;

public class Child extends Person implements TicketPrice  {

    public Child(int age, int price) {
        super(age);
        this.price = price;
    }

    @Override
    public int calculatePrice(){
        if (age > 5) {
            price = 18;
        } else {
            price = 0;
        }
        return price;
    }

}
