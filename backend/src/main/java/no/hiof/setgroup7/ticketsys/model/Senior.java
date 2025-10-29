package no.hiof.setgroup7.ticketsys.model;
import no.hiof.setgroup7.ticketsys.TicketPrice;

public class Senior extends Person implements TicketPrice {

    public Senior(int age, int price) {
        super(age, price);
    }

    public Senior(int age) {
        super(age);
    }

    @Override
    public int basePrice() {
        if(age > 67){
            price = 18;
        } else {
            System.err.println("Error! Age is not over 66.");
        }
        return price;
    }
}
