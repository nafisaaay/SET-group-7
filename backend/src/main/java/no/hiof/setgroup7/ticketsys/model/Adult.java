package no.hiof.setgroup7.ticketsys.model;
import no.hiof.setgroup7.ticketsys.TicketPrice;

public class Adult extends Person implements TicketPrice{

    public Adult(int age, int price) {
        super(age, price);
    }

    public Adult(int age) {
        super(age);
    }

    @Override
    public int basePrice(){
        if (age < 66 && age > 18) {
            price = 45;
        }
        else {
            System.err.println("Error! Age is not within range of 18- 66.");
        }
        return  price;
    }
}
