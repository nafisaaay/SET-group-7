package no.hiof.setgroup7.ticketsys.model;

public class Person {
    protected int age;
    protected int price;

    public Person(int age) {
        this.age = age;
        this.price = 0;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

}
