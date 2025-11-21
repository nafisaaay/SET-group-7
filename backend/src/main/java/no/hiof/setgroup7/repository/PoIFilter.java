package no.hiof.setgroup7.repository;

import no.hiof.setgroup7.DTOs.PoiDTO;

import java.util.ArrayList;


public class PoIFilter {


    // Takes arguments are list to be read (fromList), and then three filter conditions (filterCondition1, filterCondition2, filterCondition3)
    public static ArrayList<PoiDTO> poiFilter(ArrayList<PoiDTO> fromList,  String filterCondition1, String filterCondition2, String filterCondition3) {
        // Creating a new temporary array list
        ArrayList<PoiDTO> toList = new ArrayList<>();
        // using for loop to recursively loop through a list
        for (PoiDTO pointOfInterest : fromList) {
            // adding an item from a list to the temporary list if it matches any of the conditions using if-condition
            if (pointOfInterest.getPlaceType().equals(filterCondition1) || pointOfInterest.getPlaceType().equals(filterCondition2) || pointOfInterest.getPlaceType().equals(filterCondition3)) {
                toList.add(pointOfInterest);
            }
        }
        // returning the temporary list so it can be assinged to another array
        return toList;
    }
}







