package no.hiof.setgroup7.model;

import no.hiof.setgroup7.DTOs.PoiDTO;

import java.util.ArrayList;


public class PoIFilter {

    public static ArrayList poiFilter(ArrayList<PoiDTO> fromList,  String filterCondition1, String filterCondition2, String filterCondition3) {
        ArrayList<PoiDTO> toList = new ArrayList<>();
        for (PoiDTO pointOfInterest : fromList) {
            if (pointOfInterest.getPlaceType().equals(filterCondition1) || pointOfInterest.getPlaceType().equals(filterCondition2) || pointOfInterest.getPlaceType().equals(filterCondition3)) {
                toList.add(pointOfInterest);
            }
        }
        return toList;
    }
}







