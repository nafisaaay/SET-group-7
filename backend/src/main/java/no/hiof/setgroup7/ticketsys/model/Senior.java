package no.hiof.setgroup7.ticketsys.model;
import no.hiof.setgroup7.ticketsys.TicketPrice;

public class Senior extends Person implements TicketPrice {

    public Senior(int age, int price) {
        super(age);
        this.price = price;
    }

    @Override
    public int calculatePrice() {
        if(age > 67){
            price = 18;
        }
        return price;
    }
}
