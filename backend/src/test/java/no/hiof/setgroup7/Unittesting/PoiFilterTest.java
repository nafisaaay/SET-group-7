package no.hiof.setgroup7.Unittesting;

import no.hiof.setgroup7.DTOs.PoiDTO;
import no.hiof.setgroup7.repository.PoIFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PoiFilterTest {



    @Test
    public void testPoiFilterMatching(){
        ArrayList<PoiDTO> fromList = new ArrayList<>();
        fromList.add(new PoiDTO(59.5555f, 59.6666f, "Narnia1", "Area1", "Capital of Narnia", "Train station of Narnia"));
        fromList.add(new PoiDTO(59.4444f, 59.3333f, "Narnia2", "Area2", "Capital of Narnia", "Train station of Narnia"));
        fromList.add(new PoiDTO(59.2222f, 59.1111f, "Narnia3", "Area3", "Capital of Narnia", "Train station of Narnia"));

        ArrayList<PoiDTO> filteredList = PoIFilter.poiFilter(fromList, "Area1", "Area2", "Area3");

        Assertions.assertEquals(3, filteredList.size(), "If list has all three, it works.");

        Assertions.assertEquals("Narnia1", filteredList.get(0).getPlaceName());
        Assertions.assertEquals("Narnia2", filteredList.get(1).getPlaceName());
        Assertions.assertEquals("Narnia3", filteredList.get(2).getPlaceName());
}

    @Test
    public void testPoiFilterNonMatching(){
        ArrayList<PoiDTO> fromList = new ArrayList<>();
        fromList.add(new PoiDTO(59.5555f, 59.6666f, "Narnia1", "Area1", "Capital of Narnia", "Train station of Narnia"));
        fromList.add(new PoiDTO(59.4444f, 59.3333f, "Narnia2", "Area2", "Capital of Narnia", "Train station of Narnia"));
        fromList.add(new PoiDTO(59.2222f, 59.1111f, "Narnia3", "Area3", "Capital of Narnia", "Train station of Narnia"));

        ArrayList<PoiDTO> filteredList = PoIFilter.poiFilter(fromList, "NotArea1", "NotArea2", "NotArea3");

        Assertions.assertEquals(0, filteredList.size());
    }

    @Test
    public void testPoiFilterPartialMatching(){
        ArrayList<PoiDTO> fromList = new ArrayList<>();
        fromList.add(new PoiDTO(59.5555f, 59.6666f, "Narnia1", "Area1", "Capital of Narnia", "Train station of Narnia"));
        fromList.add(new PoiDTO(59.4444f, 59.3333f, "Narnia2", "Area2", "Capital of Narnia", "Train station of Narnia"));
        fromList.add(new PoiDTO(59.2222f, 59.1111f, "Narnia3", "Area3", "Capital of Narnia", "Train station of Narnia"));

        ArrayList<PoiDTO> filteredList = PoIFilter.poiFilter(fromList, "Area1", "NotArea2", "NotArea3");

        Assertions.assertEquals(1, filteredList.size());
        Assertions.assertEquals("Narnia1", filteredList.get(0).getPlaceName());

    }

}

