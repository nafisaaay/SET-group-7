package no.hiof.setgroup7.ticketsys.model;

public final class Adult extends Person {
    public static final int MIN_AGE = 18;
    public static final int MAX_AGE = 66;

    public Adult() {
        super(null);
    }

    @Override
    public double basePrice() {
        return 45.0;
    }
}
