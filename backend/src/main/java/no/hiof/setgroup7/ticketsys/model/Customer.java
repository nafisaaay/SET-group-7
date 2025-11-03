package no.hiof.setgroup7.ticketsys.model;

import no.hiof.setgroup7.ticketsys.service.TicketService;

public class Customer {
    private Adult adult;
    private Child child;
    private Senior senior;
    private int basePrice = 0;
    private String ageGroup;

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

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

    public int getBasePrice() {
        if (ageGroup.equals("voksen")){
             basePrice = adult.getPrice();
        }
        else if(ageGroup.equals("barn")){
            basePrice = child.getPrice();
        }
        else if (ageGroup.equals("honnør")) {
            basePrice = senior.getPrice();
        }

        return basePrice;
    }



}
