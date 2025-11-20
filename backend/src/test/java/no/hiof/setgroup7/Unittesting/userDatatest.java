package no.hiof.setgroup7.Unittesting;

import no.hiof.setgroup7.DTOs.userData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class userDatatest {

    private userData userData;

    @BeforeEach
    public void mockUser() {
        userData = new userData("User123", "password123"
        );
    }

    @Test
    public void testUserConstructor() {
        Assertions.assertEquals("User123", userData.getUsername());
        Assertions.assertEquals("password123", userData.getPassword());
    }

    @Test
    public void testSetEmail() {
        userData.setEmail("HeckedUser@hakmail.com");
        Assertions.assertEquals("HeckedUser@hakmail.com", userData.getUsername());
    }

    @Test
    public void testSetPassword() {
        userData.setEmail("b3stpazzw0rd");
        Assertions.assertEquals("b3stpazzw0rd", userData.getPassword());
    }
}
