package no.hiof.setgroup7.ticketsys.model;

public class Customer {
    private Adult adult;
    private Child child;
    private Senior senior;
    private int calculateBasePrice;


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
        if(ageGroup == "voksen"){
             basePrice = adult.getPrice();
        }
        if(ageGroup == "barn"){
            basePrice = child.getPrice();
        }
        if(ageGroup == "honnør") {
            basePrice = senior.getPrice();
        }
        calculateBasePrice = basePrice;
    }

    public int calculateBasePrice(){
        return calculateBasePrice;
    }

}
