package no.hiof.setgroup7.Unittesting;

import no.hiof.setgroup7.DTOs.PoiDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PoiDTOTest {

    private PoiDTO poiDTO;

    @BeforeEach
    public void mockArray() {
        poiDTO = new PoiDTO(
                59.2859f,
                11.0985f,
                "Sarpsborg Stadion",
                "Stadion",
                "Sarpsborg",
                "Sarpsborg"
        );
    }

    @Test
    public void testPOIConstructor() {
        Assertions.assertEquals(59.2859f, poiDTO.getLatitude(), 0.0001);
        Assertions.assertEquals(11.0985f, poiDTO.getLongitude(), 0.0001);
        Assertions.assertEquals("Sarpsborg Stadion", poiDTO.getPlaceName());
        Assertions.assertEquals("Stadion", poiDTO.getPlaceType());
        Assertions.assertEquals("Sarpsborg", poiDTO.getCity());
        Assertions.assertEquals("Sarpsborg", poiDTO.getArea());
    }

    @Test
    public void testSetLatitude() {
        poiDTO.setLatitude(60.1020f);
        Assertions.assertEquals(60.0f, poiDTO.getLatitude(), 0.0001);
    }

    @Test
    public void testSetLongitude() {
        poiDTO.setLongitude(11.0452f);
        Assertions.assertEquals(11.0f, poiDTO.getLongitude(), 0.0001);
    }

    @Test
    public void testSetPlaceName() {
        poiDTO.setPlaceName("New Place");
        Assertions.assertEquals("New Place", poiDTO.getPlaceName());
    }

    @Test
    public void testSetPlaceType() {
        poiDTO.setPlaceType("Historical");
        Assertions.assertEquals("Historical", poiDTO.getPlaceType());
    }

    @Test
    public void testSetCity() {
        poiDTO.setCity("Bergen");
        Assertions.assertEquals("Bergen", poiDTO.getCity());
    }

    @Test
    public void testSetArea() {
        poiDTO.setArea("Suburbs");
        Assertions.assertEquals("Suburbs", poiDTO.getArea());
    }
}

