package no.hiof.setgroup7.ticketsys.model;
import no.hiof.setgroup7.ticketsys.TicketPrice;

public final class Senior extends Person {
    public static final int MIN_AGE = 67;

    public Senior(int age) {
        super(age);
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Senior age must be greater than or equal to "+ MIN_AGE);
        }
    }

    @Override
    public double basePrice() {
        int price = 20;
        return price;
    }
}
