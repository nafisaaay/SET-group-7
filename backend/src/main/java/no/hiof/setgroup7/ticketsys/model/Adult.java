package no.hiof.setgroup7.ticketsys.model;
import no.hiof.setgroup7.ticketsys.TicketPrice;

public class Adult extends Person implements TicketPrice{

    public Adult(int age, int price) {
        super(age);
        this.price = price;
    }

    @Override
    public int calculatePrice(){
        if (age < 66 && age > 18) {
            price = 44;
        }
        return  price;
    }
}
