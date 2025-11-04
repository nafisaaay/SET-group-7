package no.hiof.setgroup7;
import no.hiof.setgroup7.ticketsys.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {


    @Test
    public void testCustomer()
    throws Exception {
        Customer adult = new Customer();
        Customer child = new Customer();
        Customer senior = new Customer();
        Customer student = new Customer();

        adult.setAgeGroup("voksen");
        child.setAgeGroup("barn");
        senior.setAgeGroup("honnør");
        student.setAgeGroup("student");


        Assertions.assertEquals(45, adult.getBasePrice());
        Assertions.assertEquals(10, child.getBasePrice());
        Assertions.assertEquals(20, senior.getBasePrice());
        Assertions.assertEquals(25, student.getBasePrice());

    }
}
