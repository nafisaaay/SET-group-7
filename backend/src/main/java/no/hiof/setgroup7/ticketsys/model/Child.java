package no.hiof.setgroup7.ticketsys.model;

public final class Child extends Person {
    public static final int MAX_AGE = 17;

    public Child(int age) {
        super(age);
        if (age < 0 || age > MAX_AGE) {
            throw new IllegalArgumentException("Childs age must be less than or equal to "+MAX_AGE);
        }
    }

    @Override
    public double basePrice(){
        if (age < 5) {
            System.out.println("The ticket is free for children under 5 years.");
            return 0;
        } else
            return 15.0;
    }

}
