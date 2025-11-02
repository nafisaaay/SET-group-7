package no.hiof.setgroup7.ticketsys.model;

public class Customer {
    private Adult adult;
    private Child child;
    private Senior senior;
    private int calculateBasePrice;

    public Customer() {
        this.adult = new Adult();
        this.child = new Child();
        this.senior = new Senior();
    }

    public Adult getAdult() {
        return adult;
    }

    public Child getChild() {
        return child;
    }

    public Senior getSenior() {
        return senior;
    }

    public void getBasePrice(String ageGroup) {
        int basePrice = 0;
        if(ageGroup.equals("voksen")){
             basePrice = adult.getPrice();
        }
        if(ageGroup.equals("barn")){
            basePrice = child.getPrice();
        }
        if(ageGroup.equals("honnør")) {
            basePrice = senior.getPrice();
        }
        calculateBasePrice = basePrice;
    }

    public int calculateBasePrice(){
        return calculateBasePrice;
    }

}
