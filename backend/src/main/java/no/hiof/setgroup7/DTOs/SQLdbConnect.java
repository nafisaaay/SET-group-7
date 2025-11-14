package no.hiof.setgroup7.DTOs;

import no.hiof.setgroup7.model.SQLConnector;
import java.util.ArrayList;

import static no.hiof.setgroup7.model.PoIFilter.poiFilter;


import static no.hiof.setgroup7.model.sqlProcedures.*;



public class SQLdbConnect {

    private SQLConnector sqlConnector;

    public SQLdbConnect() {
        this.sqlConnector = new SQLConnector();
    }

    public void run(){

        ArrayList<PoiDTO> poiFredrikstad = (ArrayList<PoiDTO>) sqlConnector.getAllPois(SQLGetFredrikstad);
        ArrayList<PoiDTO> poiSarpsborg = (ArrayList<PoiDTO>) sqlConnector.getAllPois(SQLGetSarpsborg);
        ArrayList<PoiDTO> poiMoss = (ArrayList<PoiDTO>) sqlConnector.getAllPois(SQLGetMoss);
        ArrayList<PoiDTO> poiHalden = (ArrayList<PoiDTO>) sqlConnector.getAllPois(SQLGetHalden);
        ArrayList<PoiDTO> poiOslo = (ArrayList<PoiDTO>) sqlConnector.getAllPois(SQLGetOslo);

        ArrayList<PoiDTO> SarpFoodPoI = poiFilter(poiSarpsborg, "café", "restaurant", "tavern");;
        ArrayList<PoiDTO> FredFoodPoI = poiFilter(poiFredrikstad, "café", "restaurant", "tavern");
        ArrayList<PoiDTO> MossFoodPoI = poiFilter(poiMoss, "café", "restaurant", "tavern");
        ArrayList<PoiDTO> HaldenFoodPoI = poiFilter(poiHalden, "café", "restaurant", "tavern");
        ArrayList<PoiDTO> OsloFoodPoI = poiFilter(poiOslo, "café", "restaurant", "tavern");

        ArrayList<PoiDTO> SarpCulturePoI = poiFilter(poiSarpsborg, "museum", "art gallery", "*landmark");
        ArrayList<PoiDTO> FredCulturePoI = poiFilter(poiFredrikstad, "museum", "art gallery", "*landmark");
        ArrayList<PoiDTO> MossCulturePoI = poiFilter(poiMoss, "museum", "art gallery", "*landmark");
        ArrayList<PoiDTO> HaldenCulturePoi = poiFilter(poiHalden, "museum", "art gallery", "*landmark");
        ArrayList<PoiDTO> OsloCulturePoI = poiFilter(poiOslo, "museum", "art gallery", "*landmark");


    }
}